/* scanner for a toy Pascal-like language */
%{
    #include <iostream>
    using namespace std;
%}

%%
[0-9][0-9]+  {return 1;} //reconhecendo números inteiros
[a-zA-Z_][a-zA-Z0-9_]* {return 2;} //reconhecendo identificaores
[+] {return 3;}
[-] {return 4;}
[*] {return 5;}
[=] {return 6;}
[<<EOF>>] {return 7;} // token fim de arquivo 
[/] {return 8;}
[;] {return 9;}
"/*"([^*]|\*+[^*/])*"*"+"/"   {  } /* ignore comentário */
"//".* {} //Ignora comentário inline
[ \t\r\n]+ {}  // Ignora espaços, \t, \r e \n
%%

int yywrap(void){
    return 1;
}

int main(int argc, char **argv)
{
    ++argv, --argc; /* skip over program name */
    if (argc > 0)
        yyin = fopen(argv[0], "r");
    else
        yyin = stdin;

    int token;
    while ((token = yylex()) != 0) {
        cout << "<" << token << " , " << yytext << ">";
    }
    cout << endl;
}
