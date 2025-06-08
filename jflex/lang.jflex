
 /*  Esta seção é copiada antes da declaração da classe do analisador léxico.
  *  É nesta seção que se deve incluir imports e declaração de pacotes.
  *  Neste exemplo não temos nada a incluir nesta seção.
  */
  
%%

%unicode
%line
%column
%class LextTest
%function nextToken
%type Token

%{
    
    /* Código arbitrário pode ser inserido diretamente no analisador dessa forma. 
     * Aqui podemos declarar variáveis e métodos adicionais que julgarmos necessários. 
     */
    private int ntk;
    
    public int readedTokens(){
       return ntk;
    }
    private Token symbol(TOKEN_TYPE t) {
        ntk++;
        return new Token(t,yytext(), yyline+1, yycolumn+1);
        
    }
    private Token symbol(TOKEN_TYPE t, Object value) {
        ntk++;
        return new Token(t, value, yyline+1, yycolumn+1);
    }
%}

%init{
    ntk = 0; // Isto é copiado direto no construtor do lexer. 
%init}

  
  /* Agora vamos definir algumas macros */
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
    "abstract"             { return symbol(TOKEN_TYPE.ABSTRACT); }
    "data"             { return symbol(TOKEN_TYPE.DATA); }
    "Int"             { return symbol(TOKEN_TYPE.INT); }
    "Char"             { return symbol(TOKEN_TYPE.CHAR); }
    "Float"             { return symbol(TOKEN_TYPE.FLOAT); }
    "Bool"             { return symbol(TOKEN_TYPE.BOOL); }
    "if"             { return symbol(TOKEN_TYPE.IF); }
    "else"             { return symbol(TOKEN_TYPE.ELSE); }
    "iterate"             { return symbol(TOKEN_TYPE.ITERATE); }
    "read"             { return symbol(TOKEN_TYPE.READ); }
    "print"             { return symbol(TOKEN_TYPE.PRINT); }
    "return"             { return symbol(TOKEN_TYPE.RETURN); }
    "new"             { return symbol(TOKEN_TYPE.NEW); }

    {int}        { return symbol(TOKEN_TYPE.INT_NUM, Integer.parseInt(yytext()) );  }
    {float}      { return symbol(TOKEN_TYPE.FLOAT_NUM, Float.parseFloat(yytext()) ); }
    {char}       {return symbol(TOKEN_TYPE.CHAR_NUM, yytext());}

    {true}      {return symbol(TOKEN_TYPE.TRUE);}
    {false}     {return symbol(TOKEN_TYPE.FALSE);}
    {null}      {return symbol(TOKEN_TYPE.NULL);}

    {identificador} { return symbol(TOKEN_TYPE.ID);   }
    {identificadorTipo} {return symbol(TOKEN_TYPE.TYID);}

    {LineComment}   {                       }
    "("             { return symbol(TOKEN_TYPE.OPEN_PARENTHESES);}
    ")"             { return symbol(TOKEN_TYPE.CLOSE_PARENTHESES);}
    "["             { return symbol(TOKEN_TYPE.OPEN_BRACKETS);}
    "]"             { return symbol(TOKEN_TYPE.CLOSE_BRACKETS);}
    "{"             { return symbol(TOKEN_TYPE.CLOSE_BRACES);}
    "}"             { return symbol(TOKEN_TYPE.CLOSE_BRACES);}
    ">"             { return symbol(TOKEN_TYPE.GREATER);}
    "<"             { return symbol(TOKEN_TYPE.LESS);}
    ";"             { return symbol(TOKEN_TYPE.SEMICOLON);}
    ":"             { return symbol(TOKEN_TYPE.DOUBLE_POINTS);}
    "::"             { return symbol(TOKEN_TYPE.DOUBLE_DOUBLE_POINTS);}
    "."             { return symbol(TOKEN_TYPE.DOT);}
    ","             { return symbol(TOKEN_TYPE.COLON);}
    "=="             { return symbol(TOKEN_TYPE.EQ_EQ);   }
    "="             { return symbol(TOKEN_TYPE.EQ);   }
    "*"             { return symbol(TOKEN_TYPE.TIMES); }
    "/"             { return symbol(TOKEN_TYPE.DIVIDE); }
    "+"             { return symbol(TOKEN_TYPE.PLUS); }
    "-"             { return symbol(TOKEN_TYPE.MINUS); }
    "%"             { return symbol(TOKEN_TYPE.MOD); }
    "&&"             { return symbol(TOKEN_TYPE.AND); }
    "!="             { return symbol(TOKEN_TYPE.NOT_EQUAL); }
    "!"             { return symbol(TOKEN_TYPE.NOT); }


    "{-"            { yybegin(COMMENT);               }
    {Brancos}       { /* Não faz nada  */             }
}

<COMMENT>{
   "-}"     { yybegin(YYINITIAL); } 
   [^"-}"]* {                     }
}



[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



