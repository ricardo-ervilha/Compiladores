#include <iostream>
#include <tuple>

using namespace std;


void printBoard(char** board, int nl, int nc);
    
tuple< char** > startBoard(char c, int nl, int nc);
    
void set(char** board, int x, int y);
    
void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void printBoard(char** board, int nl, int nc) {
    int i;
    int j;
    i = 0;
    int __aux___expr__save__0 = nl;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        j = 0;
        int __aux___expr__save__1 = nc;
        for(int __while_var_1 = 0 ; __while_var_1 < __aux___expr__save__1; __while_var_1++) {
            cout << board[i][j];
            j = (j + 1);
        }
        cout << '\n';
        i = (i + 1);
    }
}


tuple< char** > startBoard(char c, int nl, int nc) {
    char** board;
    int i;
    int j;
    board = new char*[nl];
    i = 0;
    int __aux___expr__save__0 = nl;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        j = 0;
        board[i] = new char[nc];
        int __aux___expr__save__1 = nc;
        for(int __while_var_1 = 0 ; __while_var_1 < __aux___expr__save__1; __while_var_1++) {
            board[i][j] = c;
            j = (j + 1);
        }
        i = (i + 1);
    }
    return make_tuple(board);
}


void set(char** board, int x, int y) {
    board[x][y] = 'A';
}


void main_aux() {
    char** board;
    board = get< 0 >(startBoard('*', 3, 4));
    printBoard(board,3,4);

    set(board,1,2);

    printBoard(board,3,4);

}