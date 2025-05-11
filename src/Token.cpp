#include "Token.h"
#include <iostream>


Token::Token(int type, string lexeme, int l, int c) {
    // Constructor implementation
    
    this->type = type;
    this->lexeme = lexeme;
    this->line = l;
    this->col = c;
}

Token::~Token() {
    // Destructor implementation
}



