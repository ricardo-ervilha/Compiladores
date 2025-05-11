#pragma once
#include <stack>
#include <string>
#include "Token.h"
#include <fstream>

#define ST_INIT 0

#define ST_1 1
#define ST_2 2
#define ST_3  3
#define ST_4 4
#define ST_VAR 5
#define ST_INT 6
#define ST_PLUS 7
#define ST_MULT 8
#define ST_EQ 9
#define ST_SEMI 10
#define ST_EOF 11
#define ST_SKIP 12
#define ST_ERROR 13

#define ST_BAD -1

#define CAT_EOF 0
#define CAT_LETTER 1
#define CAT_DIGIT 2
#define CAT_PLUS 3
#define CAT_MULT 4
#define CAT_EQ 5
#define CAT_SEMI 6
#define CAT_DIV 7
#define CAT_BKL 8
#define CAT_WS 9
#define CAT_ANY 10

using namespace std;

typedef struct {
    int line, col;
    char ch;
} CharInput;

class Scanner {
    private:
        stack<int> pilha;
        std::stack<CharInput> buf;

        int line, col;
        string lexeme;
        int Type[7] = {VAR, INT, PLUS, MULT, EQ, SEMI, EF};
        int d[13][11];
        bool flag_eof = false;
        ifstream file;

         
                
public:
    Scanner(string path);
    ~Scanner();
    
    bool isFinal(int state);
    void runAFD(int state);
    Token nextToken();
    void rollBack();
    bool isSkip(int state);

    char nextChar();
    char charCat(char ch);
    
};