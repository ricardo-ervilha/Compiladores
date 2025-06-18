package parsers;

import beaver.newToken;
import beaver.Scanner;

%%
%public
%class Lang
%extends Scanner
%function nextToken
%type newToken
%yylexthrow Scanner.Exception
%eofval{
	return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column

%{
	private newToken newToken(short id) {
		return new newToken(id, yyline + 1, yycolumn + 1, yylength());
	}

	private newToken newToken(short id, Object value) {
		return new newToken(id, yyline + 1, yycolumn + 1, yylength(), value);
	}
%}
  
  
FimDeLinha  = \r|\n|\r\n
Brancos     = {FimDeLinha} | [ \t\f]
int      = [:digit:] [:digit:]*
float    = [:digit:]* \. [:digit:]+
char     = \'[^\\']\' | \'\\[ntbr'\\]\' | \'\\[:digit:][:digit:][:digit:]\'
true     = "true"
false    = "false"
null      = "null"
identificador = [:lowercase:]([:lowercase:] | [:uppercase:] | [:digit:] | _)*
identificadorTipo = [:uppercase:]([:lowercase:] | [:uppercase:] | [:digit:] | _)*
LineComment = "--" (.)* {FimDeLinha}?
  
%state COMMENT

%%

<YYINITIAL>{
    "abstract"             { return newToken(Terminals.ABSTRACT); }
    "data"             { return newToken(Terminals.DATA); }
    "Int"             { return newToken(Terminals.INT); }
    "Char"             { return newToken(Terminals.CHAR); }
    "Float"             { return newToken(Terminals.FLOAT); }
    "Bool"             { return newToken(Terminals.BOOL); }
    "if"             { return newToken(Terminals.IF); }
    "else"             { return newToken(Terminals.ELSE); }
    "iterate"             { return newToken(Terminals.ITERATE); }
    "read"             { return newToken(Terminals.READ); }
    "print"             { return newToken(Terminals.PRINT); }
    "return"             { return newToken(Terminals.RETURN); }
    "new"             { return newToken(Terminals.NEW); }

    {int}        { return newToken(Terminals.INT_NUM, Integer.parseInt(yytext()) );  }
    {float}      { return newToken(Terminals.FLOAT_NUM, Float.parseFloat(yytext()) ); }
    {char}       {return newToken(Terminals.CHAR_NUM, yytext());}

    {true}      {return newToken(Terminals.TRUE);}
    {false}     {return newToken(Terminals.FALSE);}
    {null}      {return newToken(Terminals.NULL);}

    {identificador} { return newToken(Terminals.ID);   }
    {identificadorTipo} {return newToken(Terminals.TYID);}

    {LineComment}   {                       }
    "("             { return newToken(Terminals.OPEN_PARENTHESES);}
    ")"             { return newToken(Terminals.CLOSE_PARENTHESES);}
    "["             { return newToken(Terminals.OPEN_BRACKETS);}
    "]"             { return newToken(Terminals.CLOSE_BRACKETS);}
    "{"             { return newToken(Terminals.CLOSE_BRACES);}
    "}"             { return newToken(Terminals.CLOSE_BRACES);}
    ">"             { return newToken(Terminals.GREATER);}
    "<"             { return newToken(Terminals.LESS);}
    ";"             { return newToken(Terminals.SEMICOLON);}
    ":"             { return newToken(Terminals.DOUBLE_POINTS);}
    "::"             { return newToken(Terminals.DOUBLE_DOUBLE_POINTS);}
    "."             { return newToken(Terminals.DOT);}
    ","             { return newToken(Terminals.COLON);}
    "=="             { return newToken(Terminals.EQ_EQ);   }
    "="             { return newToken(Terminals.EQ);   }
    "*"             { return newToken(Terminals.TIMES); }
    "/"             { return newToken(Terminals.DIVIDE); }
    "+"             { return newToken(Terminals.PLUS); }
    "-"             { return newToken(Terminals.MINUS); }
    "%"             { return newToken(Terminals.MOD); }
    "&&"             { return newToken(Terminals.AND); }
    "!="             { return newToken(Terminals.NOT_EQUAL); }
    "!"             { return newToken(Terminals.NOT); }


    "{-"            { yybegin(COMMENT);               }
    {Brancos}       { /* Não faz nada  */             }
}

<COMMENT>{
   "-}"     { yybegin(YYINITIAL); } 
   [^"-}"]* {                     }
}


[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



