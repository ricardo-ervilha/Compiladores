grammar LangCST;

@parser::header {
    package parser;

    import ast.*;
}

@lexer::header {
    package parser;
}

prog: (def)*;

def: data | fun;

data:
	'abstract' 'data' TYID '{' (decl | fun)* '}'
	| 'data' TYID '{' (decl)* '}';

decl: ID '::' type ';';
fun: ID '(' params? ')' (':' type (',' type)*)? cmd;
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
// lvalue: ID | lvalue '[' exp ']' | lvalue '.' ID;

lvalue: ID lvalueLinha;

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