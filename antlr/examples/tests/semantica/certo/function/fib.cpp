#include <iostream>
#include <tuple>

using namespace std;


tuple< int > fibonacci(int n);
    
void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

tuple< int > fibonacci(int n) {
    if((n < 1)) {
        return make_tuple(n);
    } 
    if((n == 1)) {
        return make_tuple(n);
    } 
    return make_tuple((get< 0 >(fibonacci((n - 1))) + get< 0 >(fibonacci((n - 2)))));
}


void main_aux() {
    int v;
    v = get< 0 >(fibonacci(15));
    cout << v;
    cout << '\n';
}