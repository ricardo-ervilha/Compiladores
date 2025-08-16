#include <iostream>
#include <tuple>

using namespace std;


void main_aux();
    
tuple< int, int > divMod(int n, int q);
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    int x;
    int y;
    int quo;
    int res;
    x = 13;
    y = 5;
    auto __return_function_func_call_  = divMod(x,y);
    quo = get< 0 >(__return_function_func_call_);
    res = get< 1 >(__return_function_func_call_);
    cout << quo;
    cout << '\n';
    cout << res;
    cout << '\n';
    cout << x;
    cout << '\n';
}


tuple< int, int > divMod(int n, int q) {
    int x;
    x = 5;
    return make_tuple((n / q), (n % q));
    cout << 'z';
    cout << '\n';
}