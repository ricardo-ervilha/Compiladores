#include <iostream>
#include <tuple>

using namespace std;


tuple< bool > or1(bool a, bool b);
    
void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

tuple< bool > or1(bool a, bool b) {
    return make_tuple(!(!a && !b));
}


void main_aux() {
    bool a;
    bool b;
    a = true;
    b = true;
    cout << get< 0 >(or1(a, b));
    cout << '\n';
    a = true;
    b = false;
    cout << get< 0 >(or1(a, b));
    cout << '\n';
    a = false;
    b = true;
    cout << get< 0 >(or1(a, b));
    cout << '\n';
    a = false;
    b = false;
    cout << get< 0 >(or1(a, b));
    cout << '\n';
}