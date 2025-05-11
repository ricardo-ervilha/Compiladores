#include <iostream>
#include "../include/Scanner.h"

using namespace std;

int main(int argc, char *argv[])
{
    string filename = "../inputs/source2.txt";

    cout <<"Lendo: "<<filename<<endl;

    Scanner *lex = new Scanner(filename);

    Token token = lex->nextToken();
       

    while(token.getType() != EF){
        cout << "Token: "<< token.getType() <<" \t lexeme: "<<token.getLexeme()<<endl;
        token = lex->nextToken();
    }

    return 0;
}