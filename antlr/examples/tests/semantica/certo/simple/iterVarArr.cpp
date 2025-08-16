#include <iostream>
#include <tuple>

using namespace std;


void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    int* v;
    int i;
    v = new int[10];
    int __aux___expr__save__0 = 10;
    for( i = 0 ; i < __aux___expr__save__0; i++) {
        v[i] = (2 * i);
    }
    for(int __while_var_0 = 0; __while_var_0 < 10; __while_var_0++){
        i = v[__while_var_0];
        cout << i;
        cout << ' ';
    }
    cout << '.';
    cout << '\n';
}