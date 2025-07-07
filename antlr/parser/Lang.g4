grammar Lang;

@parser::header {
    package parser;

    import ast.*;
}

@lexer::header {
    package parser;
}

// prog: (def)*;
prog
returns [Program ast]
        @init {
            List<Def> defList = new ArrayList<>();
        }:
        (
            s1 = def { defList.add($s1.ast); }
        )*
        {
            $ast = new Program($start.getLine(), $start.getCharPositionInLine(), defList);
        }
;

// def: data | fun;
def returns [Def ast]
    : d1 = data { $ast = $d1.ast; }
    | f1 = fun  { $ast = $f1.ast; }
    ;

// data: 'abstract' 'data' TYID '{' (decl | fun)* '}' | 'data' TYID '{' (decl)* '}';

data
	returns[Def ast]
	@init {
		List<Node> members = new ArrayList<>();
	}:
	abs = 'abstract' 'data' TYID '{' (
		decl { members.add($decl.ast); }
		| fun_abstract_data { members.add($fun_abstract_data.ast); }
	)* '}' {
        	$ast = new AbstractDataDecl($abs.line, $abs.pos, $TYID.text, members);
    }
	| d = 'data' TYID '{' (decl { members.add($decl.ast); })* '}' {
				$ast = new DataDecl($d.line, $d.pos, $TYID.text, members);
			}
	;

// decl: ID '::' type ';';
decl
	returns[Decl ast]:
	id = ID '::' t = type ';' {
        $ast = new Decl($id.line, $id.pos, $id.text, $t.ast);
    };

// RICARDO: Adicionei isso para poder existir separação entre a Função de um tipo Abstrato e uma função normal.
// Assim, ficará mais fácil tratar esse caso.
fun_abstract_data
    returns[FunAbstractData ast]
	@init {
    	List<Type> members = new ArrayList<>();
  	}:
	fun_def = ID '(' p = params? ')' (
		':' t1 = type { members.add($t1.ast); } (
			',' t = type { members.add($t.ast); }
		)*
	)? cmd {
		$ast = new FunAbstractData($fun_def.line, $fun_def.pos, $fun_def.text, $p.ctx != null ? $p.ast : null, members, $cmd.ast);
	};

// Ricardo: Essas são as funções tradicionais.
fun
	returns[Fun ast]
	@init {
    	List<Type> members = new ArrayList<>();
  	}:
	fun_def = ID '(' p = params? ')' (
		':' t1 = type { members.add($t1.ast); } (
			',' t = type { members.add($t.ast); }
		)*
	)? cmd {
		$ast = new Fun($fun_def.line, $fun_def.pos, $fun_def.text, $p.ctx != null ? $p.ast : null, members, $cmd.ast);
	};

// params: ID '::' type (',' ID '::' type)*;
params 
	returns[Params ast] 
	@init {
	    List<Param> listParam = new ArrayList<>();
	}:
	id1 = ID '::' t1 = type {listParam.add(new Param($id1.text, $t1.ast));}
	( ',' id2 = ID '::' t2 = type {listParam.add(new Param($id2.text, $t2.ast));})* {
		$ast = new Params($id1.line, $id1.pos, listParam);
	}
	;

// type: type '['']' | btype; type: btype typeLinha;
type
	returns[Type ast]:
	t1=type '[' ']' {$ast = new ArrayType($t1.ast.getLine(), $t1.ast.getCol());}
	| b=btype {$ast = $b.ast;}
	;

btype
    returns[BType ast]:
	Int1 = 'Int' {$ast = new TypeInt($Int1.line, $Int1.pos);}
	| Char1 = 'Char' {$ast = new TypeChar($Char1.line, $Char1.pos);}
	| Bool1 = 'Bool' {$ast = new TypeBool($Bool1.line, $Bool1.pos);}
	| Float1 = 'Float' {$ast = new TypeFloat($Float1.line, $Float1.pos);}
	| TYID {$ast = new TYID($TYID.line, $TYID.pos, $TYID.text);}
	;

// block: '{' (cmd)* '}';
block
    returns [Block ast]
    @init { List<Cmd> listCmd = new ArrayList<>(); int line = -1; int col = -1; }
    : '{'
            (c1=cmd {
                if (line == -1) { line = $c1.ast.getLine(); col = $c1.ast.getCol(); }
                listCmd.add($c1.ast);
            })*
        '}'
    {
        $ast = new Block(line, col, listCmd);
    }
    ;

cmd
	returns[Cmd ast]
	@init {
    	List<LValue> membersLValue = new ArrayList<>();
		List<Expr> membersReturn = new ArrayList<>();
  	}:
	block { $ast = $block.ast;}
	| ifCond = 'if' '(' exp ')' cmd { $ast = new CmdIf($ifCond.line, $ifCond.pos, $exp.ast, $cmd.ast);}
	| ifCond = 'if' '(' exp ')' cmd1 = cmd 'else' cmd2 = cmd { $ast = new CmdIf($ifCond.line, $ifCond.pos, $exp.ast, $cmd1.ast, $cmd2.ast );
		}
	| it = 'iterate' '(' itcond ')' cmd { $ast = new CmdIterate($it.line, $it.pos, $itcond.ast, $cmd.ast );
		}
	| rd = 'read' lvalue ';' { $ast = new CmdRead($rd.line, $rd.pos, $lvalue.ast); }
	| prt = 'print' exp ';' { $ast = new CmdPrint($prt.line, $prt.pos, $exp.ast); }
	| rt = 'return' exp { membersReturn.add($exp.ast) ;} (
		',' exp {membersReturn.add($exp.ast);}
	)* { $ast = new CmdReturn($rt.line, $rt.pos, membersReturn);} ';'
	| lvalue '=' exp ';' { $ast = new CmdAssign($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast, $exp.ast);
		}
	// | ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';'
	| ID '(' exps? ')' (
		'<' l1 = lvalue { membersLValue.add($l1.ast) ;} (
			',' l2 = lvalue { membersLValue.add($l2.ast) ;}
		)* '>')? {$ast = new CmdFuncCall($ID.line, $ID.pos,$ID.text, $exps.ctx != null ? $exps.ast : null, membersLValue );} ';';

itcond
	returns[Itcond ast]:
	ID ':' exp { $ast = new IdItCond($ID.line, $ID.pos, $ID.text, $exp.ast);
		}
	| exp { $ast = new ExpItCond($exp.ast.getLine(), $exp.ast.getCol(), $exp.ast);}
	;

// exp: exp op exp | '!' exp | '-' exp | lvalue | '(' exp ')' | 'new' type ('[' exp ']')? | ID '('
// (exps)? ')' '[' exp ']' | 'true' | 'false' | 'null' | INT | FLOAT | CHAR;

exp
	returns[Expr ast]:
	  exp1=exp op = '*' exp2=exp { $ast = new Mul($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '/' exp2=exp { $ast = new Div($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '%' exp2=exp { $ast = new Mod($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '+' exp2=exp { $ast = new Add($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '-' exp2=exp { $ast = new Sub($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '<' exp2=exp { $ast = new Lt($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '==' exp2=exp { $ast = new Eq($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '!=' exp2=exp { $ast = new Diff($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| exp1=exp op = '&&' exp2=exp { $ast = new And($exp1.ast.getLine(), $exp1.ast.getCol(), $exp1.ast, $exp2.ast);}
	| not = '!' exp { $ast = new NotExpr($not.line, $not.pos, $exp.ast);}
	| minus = '-' exp { $ast = new MinusExpr($minus.line, $minus.pos, $exp.ast);}
	| lvalue { $ast = $lvalue.ast;
		}
	| '(' exp ')' { $ast = $exp.ast; }
	| 'new' type ('[' e = exp ']') { $ast = new ArrayExpr($type.ast.getLine(), $type.ast.getCol(), $type.ast, $e.ctx != null ? $e.ast : null);}
	| 'new' btype                  { $ast = new VarExpr($btype.ast.getLine(), $btype.ast.getCol(), $btype.ast);}
	| ID '(' (exps)? ')' '[' exp ']'  { $ast = new CallFunctionAccess($ID.line, $ID.pos, $ID.text, $exps.ctx !=null ? $exps.ast : null , $exp.ast);
		}
	| t = 'true'  { $ast = new TrueValue($t.line, $t.pos);}
	| f = 'false'  { $ast = new FalseValue($f.line, $f.pos);}
	| n = 'null'  { $ast = new NullValue($n.line, $n.pos);}
	| INT { $ast = new IntValue($INT.line, $INT.pos, $INT.text);}
	| FLOAT { $ast = new FloatValue($FLOAT.line, $FLOAT.pos, $FLOAT.text);}
	| CHAR { $ast = new CharValue($CHAR.line, $CHAR.pos, $CHAR.text);};


// lvalue: ID | lvalue '[' exp ']' | lvalue '.' ID | TYID ID;
lvalue returns[LValue ast]:
	ID { $ast = new ID($ID.line, $ID.pos, $ID.text);}
	| l1 = lvalue '[' exp ']' {$ast = new LValueExp($l1.ast.getLine(), $l1.ast.getCol(), $l1.ast, $exp.ast);}
	| l1=lvalue '.' ID {$ast = new IdLValue($l1.ast.getLine(), $l1.ast.getCol(), $l1.ast, $ID.text);}
		;

exps returns[Exps ast]
	@init {
    	List<Expr> expsValues = new ArrayList<>();
  	}:
	e1=exp {expsValues.add($e1.ast);}
	(',' e2=exp {expsValues.add($e2.ast);})*
		{$ast = new Exps($e1.ast.getLine(), $e1.ast.getCol(), expsValues);}
	;

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