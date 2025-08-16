#include <iostream>
#include <tuple>

using namespace std;


void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    int nlines;
    int i;
    nlines = 5;
    i = nlines;
    int __aux___expr__save__0 = nlines;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        int __aux___expr__save__1 = i;
        for(int __while_var_1 = 0 ; __while_var_1 < __aux___expr__save__1; __while_var_1++) {
            cout << '*';
        }
        i = (i - 1);
        cout << '\n';
    }
}