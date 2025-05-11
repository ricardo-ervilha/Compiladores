#pragma once
#include <string>

#define VAR 1
#define INT 2
#define EQ 3
#define SEMI 4
#define PLUS 5
#define MULT 6
#define EF -1

using namespace std;

class Token {
    private:
        int line, col, type;
        string lexeme;
        

public:
    Token(int type, string lexeme, int l, int c);
    ~Token();

    int getLine(){
        return this->line;
    } 
    int getCol(){
        return this->col;
    }
    string getLexeme(){
        return this->lexeme;
    }
    int getType(){
        return this->type;
    }

};