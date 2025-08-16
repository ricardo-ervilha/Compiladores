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
    x = 0;
    cin >> x;
    if((x < 10)) {
        if((5 < x)) {
            cout << 'A';
        } else {
            cout << 'Z';
        }

    } 
    cout << 'D';
    cout << '\n';
}