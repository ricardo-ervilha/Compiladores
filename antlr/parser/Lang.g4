grammar Lang;

@parser::header {
    package parser;

    import ast.*;
}

@lexer::header {
    package parser;
}

prog
	returns[StmtList ast]:
	(
		s1 = def {$ast = new StmtList($s1.ast.getLine(), $s1.ast.getCol(), $s1.ast);}
	)*;

def
	returns[Node ast]:
	d1 = data {$ast = new Data($d1.ast.getLine(), $d1.ast.getCol(), $d1.ast);}
	| f1 = fun {$ast = new Fun($f1.ast.getLine(), $f1.ast.getCol(), $f1.ast);};

// data: 
// 'abstract' 'data' TYID '{' (decl | fun)* '}' 
// | data TYID '{' (decl)* '}';

data returns [Expr ast]
	@init {
		List<Node> members = new ArrayList<>();
	}:
	abs_data = 'abstract' 'data' TYID '{' (
		d = decl { members.add($d.ast); }
		| f = fun { members.add($f.ast); }
	)* '}' {
        $ast = new AbstractData($abs_data.line, $abs_data.pos, new TYDID($abs_data.line, $abs_data.pos, TYDID.text), members);
    }
	|
	d = 'data' TYID  '{'(
		 		d1 = decl { members.add($d1.ast); }
		 		)*
		'}' 
			{
				$ast = new Decl($TYID.ast.line, $TYID.ast.pos, members);
			}
	
	;

// decl: ID '::' type ';';
decl returns [Expr ast]: 
	ID '::' type {$ast = new Decl($ID.line, $ID.pos, new ID($ID.line, $ID.pos, $ID.text), $type.ast);}
	;

// fun: ID '(' params? ')' (':' type (',' type)*)? cmd;
 fun returns [Expr ast]: 
 	fun_def = ID '(' p =params? ')' (':' type (',' 
		t =  type { members.add($t.ast); }
	)*)? cmd 
	{
		$ast = new Fun($ID.line, $ID.pos, new Params($ID.line, $ID.pos, $p.ast), members, cmd.ast)
	};

params: ID '::' type (',' ID '::' type)*;

// type: type '['']' | btype;
type: btype typeLinha;
typeLinha: '[' ']' typeLinha |;
btype: 'Int' | 'Char' | 'Bool' | 'Float' | TYID;
block: '{' (cmd)* '}';
cmd:
	block
	| 'if' '(' exp ')' cmd
	| 'if' '(' exp ')' cmd 'else' cmd
	| 'iterate' '(' itcond ')' cmd
	| 'read' lvalue ';'
	| 'print' exp ';'
	| 'return' exp (',' exp)* ';'
	| lvalue '=' exp ';'
	| ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';';

itcond: ID ':' exp | exp;

// exp: exp op exp | '!' exp | '-' exp | lvalue | '(' exp ')' | 'new' type ('[' exp ']')? | ID '('
// (exps)? ')' '[' exp ']' | 'true' | 'false' | 'null' | INT | FLOAT | CHAR;

exp:
	'!' exp expLinha
	| '-' exp expLinha
	| lvalue expLinha
	| '(' exp ')' expLinha
	| 'new' type ('[' exp ']')? expLinha
	| ID '(' (exps)? ')' '[' exp ']' expLinha
	| 'true' expLinha
	| 'false' expLinha
	| 'null' expLinha
	| INT expLinha
	| FLOAT expLinha
	| CHAR expLinha;

expLinha: op exp expLinha |;

op: '&&' | '<' | '==' | '!=' | '+' | '-' | '*' | '/' | '%';
// lvalue: ID | lvalue '[' exp ']' | lvalue '.' ID | TYID ID;

lvalue:
	ID lvalueLinha
	| TYID ID lvalueLinha; // nos criamos essa regra

lvalueLinha: '[' exp ']' lvalueLinha | '.' ID lvalueLinha |;
exps: exp (',' exp)*;

//REGRAS LÉXICAS LITERAIS
INT: [0-9]+;
FLOAT: [0-9]* '.' [0-9]+;
CHAR:
	'\'' (
		~['\\] // qualquer caractere, exceto aspas simples e barra invertida
		| '\\' [ntbr'\\] // sequência de escape comum
		| '\\' DIGIT DIGIT DIGIT // sequência octal (3 dígitos)
	) '\'';

fragment DIGIT: [0-9];

// IDENTIFICADORES
ID: [a-z] [a-zA-Z0-9_]*;
TYID: [A-Z] [a-zA-Z0-9_]*;

// COMENTÁRIOS
LINE_COMMENT: '--' ~[\r\n]* -> skip;
BLOCK_COMMENT: '{-' .*? '-}' -> skip;

// IGNORAR ESPAÇOS E QUEBRAS DE LINHA
WS: [ \t\f\r\n]+ -> skip;