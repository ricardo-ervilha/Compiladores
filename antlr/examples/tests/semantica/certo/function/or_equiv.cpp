#include <iostream>
#include <tuple>

using namespace std;


tuple< bool > _or(bool a, bool b);
    
void _main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

tuple< bool > _or(bool a, bool b) {
    return make_tuple(!(!a && !b));
}


void _main_aux() {
    bool a;
    bool b;
    a = true;
    b = true;
    cout << get< 0 >(_or(a, b));
    cout << '\n';
    a = true;
    b = false;
    cout << get< 0 >(_or(a, b));
    cout << '\n';
    a = false;
    b = true;
    cout << get< 0 >(_or(a, b));
    cout << '\n';
    a = false;
    b = false;
    cout << get< 0 >(_or(a, b));
    cout << '\n';
}