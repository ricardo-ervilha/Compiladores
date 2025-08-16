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
    cout << x;
    cout << '\n';
}