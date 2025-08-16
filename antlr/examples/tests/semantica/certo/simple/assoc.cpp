#include <iostream>
#include <tuple>

using namespace std;


void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    int x;
    int y;
    x = ((3 - 2) - 1);
    y = (3 - (2 - 1));
    cout << x;
    cout << '\n';
    cout << y;
    cout << '\n';
    cout << (x == y);
    cout << '\n';
}