#include "Scanner.h"
#include <iostream>

Scanner::Scanner(string path)
{
    // Constructor implementation
    this->pilha = std::stack<int>();
    this->buf = std::stack<CharInput>();

    this->file.open(path, ifstream::in);

    this->d[ST_INIT][CAT_EOF] = ST_EOF;
    this->d[ST_INIT][CAT_LETTER] = ST_VAR;
    this->d[ST_INIT][CAT_DIGIT] = ST_INT;
    this->d[ST_INIT][CAT_PLUS] = ST_PLUS;
    this->d[ST_INIT][CAT_MULT] = ST_MULT;
    this->d[ST_INIT][CAT_EQ] = ST_EQ;
    this->d[ST_INIT][CAT_SEMI] = ST_SEMI;
    this->d[ST_INIT][CAT_DIV] = ST_1;
    this->d[ST_INIT][CAT_BKL] = ST_SKIP;
    this->d[ST_INIT][CAT_WS] = ST_SKIP;
    this->d[ST_INIT][CAT_ANY] = ST_ERROR;
    // transitions for state 1
    this->d[ST_1][CAT_EOF] = ST_ERROR;
    this->d[ST_1][CAT_LETTER] = ST_ERROR;
    this->d[ST_1][CAT_DIGIT] = ST_ERROR;
    this->d[ST_1][CAT_PLUS] = ST_ERROR;
    this->d[ST_1][CAT_MULT] = ST_3;
    this->d[ST_1][CAT_EQ] = ST_ERROR;
    this->d[ST_1][CAT_SEMI] = ST_ERROR;
    this->d[ST_1][CAT_DIV] = ST_2;
    this->d[ST_1][CAT_BKL] = ST_ERROR;
    this->d[ST_1][CAT_WS] = ST_ERROR;
    this->d[ST_1][CAT_ANY] = ST_ERROR;
    // transitions for state 2
    this->d[ST_2][CAT_EOF] = ST_SKIP;
    this->d[ST_2][CAT_LETTER] = ST_2;
    this->d[ST_2][CAT_DIGIT] = ST_2;
    this->d[ST_2][CAT_PLUS] = ST_2;
    this->d[ST_2][CAT_MULT] = ST_2;
    this->d[ST_2][CAT_EQ] = ST_2;
    this->d[ST_2][CAT_SEMI] = ST_2;
    this->d[ST_2][CAT_DIV] = ST_2;
    this->d[ST_2][CAT_BKL] = ST_SKIP;
    this->d[ST_2][CAT_WS] = ST_2;
    this->d[ST_2][CAT_ANY] = ST_2;
    // transitions for state 3
    this->d[ST_3][CAT_EOF] = ST_3;
    this->d[ST_3][CAT_LETTER] = ST_3;
    this->d[ST_3][CAT_DIGIT] = ST_3;
    this->d[ST_3][CAT_PLUS] = ST_3;
    this->d[ST_3][CAT_MULT] = ST_4;
    this->d[ST_3][CAT_EQ] = ST_3;
    this->d[ST_3][CAT_SEMI] = ST_3;
    this->d[ST_3][CAT_DIV] = ST_3;
    this->d[ST_3][CAT_BKL] = ST_3;
    this->d[ST_3][CAT_WS] = ST_3;
    this->d[ST_3][CAT_ANY] = ST_3;
    // transitions for state 4
    this->d[ST_4][CAT_EOF] = ST_ERROR;
    this->d[ST_4][CAT_LETTER] = ST_3;
    this->d[ST_4][CAT_DIGIT] = ST_3;
    this->d[ST_4][CAT_PLUS] = ST_3;
    this->d[ST_4][CAT_MULT] = ST_4;
    this->d[ST_4][CAT_EQ] = ST_3;
    this->d[ST_4][CAT_SEMI] = ST_3;
    this->d[ST_4][CAT_DIV] = ST_SKIP;
    this->d[ST_4][CAT_BKL] = ST_3;
    this->d[ST_4][CAT_WS] = ST_3;
    this->d[ST_4][CAT_ANY] = ST_3;
    // transitions for state VAR
    this->d[ST_VAR][CAT_EOF] = ST_ERROR;
    this->d[ST_VAR][CAT_LETTER] = ST_ERROR;
    this->d[ST_VAR][CAT_DIGIT] = ST_ERROR;
    this->d[ST_VAR][CAT_PLUS] = ST_ERROR;
    this->d[ST_VAR][CAT_MULT] = ST_ERROR;
    this->d[ST_VAR][CAT_EQ] = ST_ERROR;
    this->d[ST_VAR][CAT_SEMI] = ST_ERROR;
    this->d[ST_VAR][CAT_DIV] = ST_ERROR;
    this->d[ST_VAR][CAT_BKL] = ST_ERROR;
    this->d[ST_VAR][CAT_WS] = ST_ERROR;
    this->d[ST_VAR][CAT_ANY] = ST_ERROR;
    // transitions for state INT
    this->d[ST_INT][CAT_EOF] = ST_ERROR;
    this->d[ST_INT][CAT_LETTER] = ST_ERROR;
    this->d[ST_INT][CAT_DIGIT] = ST_INT;
    this->d[ST_INT][CAT_PLUS] = ST_ERROR;
    this->d[ST_INT][CAT_MULT] = ST_ERROR;
    this->d[ST_INT][CAT_EQ] = ST_ERROR;
    this->d[ST_INT][CAT_SEMI] = ST_ERROR;
    this->d[ST_INT][CAT_DIV] = ST_ERROR;
    this->d[ST_INT][CAT_BKL] = ST_ERROR;
    this->d[ST_INT][CAT_WS] = ST_ERROR;
    this->d[ST_INT][CAT_ANY] = ST_ERROR;
    // transitions for state PLUS
    this->d[ST_PLUS][CAT_EOF] = ST_ERROR;
    this->d[ST_PLUS][CAT_LETTER] = ST_ERROR;
    this->d[ST_PLUS][CAT_DIGIT] = ST_ERROR;
    this->d[ST_PLUS][CAT_PLUS] = ST_ERROR;
    this->d[ST_PLUS][CAT_MULT] = ST_ERROR;
    this->d[ST_PLUS][CAT_EQ] = ST_ERROR;
    this->d[ST_PLUS][CAT_SEMI] = ST_ERROR;
    this->d[ST_PLUS][CAT_DIV] = ST_ERROR;
    this->d[ST_PLUS][CAT_BKL] = ST_ERROR;
    this->d[ST_PLUS][CAT_WS] = ST_ERROR;
    this->d[ST_PLUS][CAT_ANY] = ST_ERROR;
    // transitions for state MULT
    this->d[ST_MULT][CAT_EOF] = ST_ERROR;
    this->d[ST_MULT][CAT_LETTER] = ST_ERROR;
    this->d[ST_MULT][CAT_DIGIT] = ST_ERROR;
    this->d[ST_MULT][CAT_PLUS] = ST_ERROR;
    this->d[ST_MULT][CAT_MULT] = ST_ERROR;
    this->d[ST_MULT][CAT_EQ] = ST_ERROR;
    this->d[ST_MULT][CAT_SEMI] = ST_ERROR;
    this->d[ST_MULT][CAT_DIV] = ST_ERROR;
    this->d[ST_MULT][CAT_BKL] = ST_ERROR;
    this->d[ST_MULT][CAT_WS] = ST_ERROR;
    this->d[ST_MULT][CAT_ANY] = ST_ERROR;
    // transitions for state EQ
    this->d[ST_EQ][CAT_EOF] = ST_ERROR;
    this->d[ST_EQ][CAT_LETTER] = ST_ERROR;
    this->d[ST_EQ][CAT_DIGIT] = ST_ERROR;
    this->d[ST_EQ][CAT_PLUS] = ST_ERROR;
    this->d[ST_EQ][CAT_MULT] = ST_ERROR;
    this->d[ST_EQ][CAT_EQ] = ST_ERROR;
    this->d[ST_EQ][CAT_SEMI] = ST_ERROR;
    this->d[ST_EQ][CAT_DIV] = ST_ERROR;
    this->d[ST_EQ][CAT_BKL] = ST_ERROR;
    this->d[ST_EQ][CAT_WS] = ST_ERROR;
    this->d[ST_EQ][CAT_ANY] = ST_ERROR;
    // transitions for state SEMI
    this->d[ST_SEMI][CAT_EOF] = ST_ERROR;
    this->d[ST_SEMI][CAT_LETTER] = ST_ERROR;
    this->d[ST_SEMI][CAT_DIGIT] = ST_ERROR;
    this->d[ST_SEMI][CAT_PLUS] = ST_ERROR;
    this->d[ST_SEMI][CAT_MULT] = ST_ERROR;
    this->d[ST_SEMI][CAT_EQ] = ST_ERROR;
    this->d[ST_SEMI][CAT_SEMI] = ST_ERROR;
    this->d[ST_SEMI][CAT_DIV] = ST_ERROR;
    this->d[ST_SEMI][CAT_BKL] = ST_ERROR;
    this->d[ST_SEMI][CAT_WS] = ST_ERROR;
    this->d[ST_SEMI][CAT_ANY] = ST_ERROR;
    // transitions for state SKIP
    this->d[ST_SKIP][CAT_EOF] = ST_ERROR;
    this->d[ST_SKIP][CAT_LETTER] = ST_ERROR;
    this->d[ST_SKIP][CAT_DIGIT] = ST_ERROR;
    this->d[ST_SKIP][CAT_PLUS] = ST_ERROR;
    this->d[ST_SKIP][CAT_MULT] = ST_ERROR;
    this->d[ST_SKIP][CAT_EQ] = ST_ERROR;
    this->d[ST_SKIP][CAT_SEMI] = ST_ERROR;
    this->d[ST_SKIP][CAT_DIV] = ST_ERROR;
    this->d[ST_SKIP][CAT_BKL] = ST_SKIP;
    this->d[ST_SKIP][CAT_WS] = ST_SKIP;
    this->d[ST_SKIP][CAT_ANY] = ST_ERROR;
    // transitions for state EOF
    this->d[ST_EOF][CAT_EOF] = ST_ERROR;
    this->d[ST_EOF][CAT_LETTER] = ST_ERROR;
    this->d[ST_EOF][CAT_DIGIT] = ST_ERROR;
    this->d[ST_EOF][CAT_PLUS] = ST_ERROR;
    this->d[ST_EOF][CAT_MULT] = ST_ERROR;
    this->d[ST_EOF][CAT_EQ] = ST_ERROR;
    this->d[ST_EOF][CAT_SEMI] = ST_ERROR;
    this->d[ST_EOF][CAT_DIV] = ST_ERROR;
    this->d[ST_EOF][CAT_BKL] = ST_ERROR;
    this->d[ST_EOF][CAT_WS] = ST_ERROR;
    this->d[ST_EOF][CAT_ANY] = ST_ERROR;
    // transitions for error state
    // Not needed as we will never access after a error state
}

Scanner::~Scanner()
{
    // Destructor implementation
    this->file.close();
}

bool Scanner::isSkip(int state)
{
    return state == ST_SKIP;
}

bool Scanner::isFinal(int state)
{
    if (state >= ST_VAR && state < ST_SKIP)
    {
        return true;
    }
    return false;
}



void Scanner::rollBack()
{
    if (!buf.empty())
    {
        this->line = this->buf.top().line;
        this->col = this->buf.top().col;
        this->file.unget();
        this->lexeme = this->lexeme.substr(0, this->lexeme.length() - 1);
    }
}

char Scanner::charCat(char ch)
{
    if (flag_eof)
        return CAT_EOF;
    if (ch >= 'a' && ch <= 'z')
        return CAT_LETTER; // a letter
    if (ch >= '0' && ch <= '9')
        return CAT_DIGIT;

    switch (ch)
    {
    case '+':
        return CAT_PLUS;
    case '*':
        return CAT_MULT;
    case '=':
        return CAT_EQ;
    case ';':
        return CAT_SEMI;
    case '/':
        return CAT_DIV;
    case '\n':
        return CAT_BKL;
    case ' ':
    case '\t':
    case '\r':
        return CAT_WS;
    default:
        return CAT_ANY;
    }
}

char Scanner::nextChar()
{
    char ch;
    
    if (!this->file.get(ch)){
        this->flag_eof = true;
        return '\0';
    }


    if (ch == '\n')
    {
        this->line++;
        this->col = 0;
    }
    else
    {
        this->col++;
    }
    
    this->buf.push({this->line, this->col, ch});


    return ch;
}

void Scanner::runAFD(int state)
{
    this->lexeme = "";
    this->pilha = std::stack<int>();
    this->pilha.push(ST_BAD);

    char ch;
    int cat;

    while (state != ST_ERROR)
    {
        ch = nextChar();
        lexeme = lexeme + ch;
        pilha.push(state);
        cat = charCat(ch);
        state = this->d[state][cat];
    }
    this->rollBack();
}

Token Scanner::nextToken()
{
    if (flag_eof)
    {
        return Token(EF, "", this->line, this->col);
    }

    int state = ST_INIT;
    
    do
    {
        this->runAFD(state);
    } while (this->isSkip(pilha.top()));

    state = this->pilha.top();
    this->pilha.pop();

    while (!this->isFinal(state) && state != ST_BAD)
    {
        state = this->pilha.top();

        this->pilha.pop();

        this->rollBack();
    }

    // Emitir o token
    if (isFinal(state))
    {
        return Token(Type[state - ST_VAR], this->lexeme, this->line, this->col);
    }
    else
    {
        cout << "Error: invalid token at line " << line << " column: " << col << endl;
        
        exit(1);
    }

    // return nullptr;
}
