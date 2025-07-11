grammar lang;

@parser::header
{
    /*
      *
      *  * Alunos:
      *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
      *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
      *  *
      *  * Disciplina: DCC045 - Teoria de Compiladores
      *
      *
      *
      */
    package parser;
}

@lexer::header
{
    /*
      *
      *  * Alunos:
      *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
      *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
      *  *
      *  * Disciplina: DCC045 - Teoria de Compiladores
      *
      *
      *
      */
    package parser;
}

/* Regras da gramática */

prog: (stmt ';')+;

stmt :
 ID '=' expr
|
 expr '?' '[' stmt ']' ':' '[' stmt ']'
|
 expr '?' '[' stmt ']'
|
 expr
;

expr:
 term '+' expr
|
 term
;

term:
 factor '*' term
|
 factor
;

factor:
 ID
|
 INT
;

/* Regras léxicas */

ID : [a-z]+;
INT : [0-9]+;

NEWLINE: '\r'? '\n' -> skip;
WS : [ \t]+ -> skip;
LINE_COMMENT : '//' ~('\r' | '\n')* NEWLINE -> skip;
COMMENT: '/*' .*?  '*/' -> skip;

