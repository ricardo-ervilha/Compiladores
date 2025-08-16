#include <iostream>
#include <tuple>

using namespace std;


tuple< bool > even(int n);
    
tuple< bool > odd(int n);
    
void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

tuple< bool > even(int n) {
    if((n == 0)) {
        return make_tuple(true);
    } else {
        return make_tuple(get< 0 >(odd((n - 1))));
    }

}


tuple< bool > odd(int n) {
    if((n == 0)) {
        return make_tuple(false);
    } else {
        return make_tuple(get< 0 >(even((n - 1))));
    }

}


void main_aux() {
    bool r;
    r = get< 0 >(even(3));
    if(r) {
        cout << 'P';
        cout << 'A';
        cout << 'R';
    } else {
        cout << 'I';
        cout << 'M';
        cout << 'P';
        cout << 'A';
        cout << 'R';
    }

}