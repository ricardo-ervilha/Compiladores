#include <iostream>
#include <tuple>

using namespace std;


void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    bool r1;
    bool r2;
    bool r3;
    r1 = ('A' == static_cast<char>(65));
    r2 = ('A' == 'A');
    r3 = (static_cast<char>(66) == 'A');
    cout << r1;
    cout << '\n';
    cout << r2;
    cout << '\n';
    cout << r3;
    cout << '\n';
}