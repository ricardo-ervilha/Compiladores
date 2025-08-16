#include <iostream>
#include <tuple>

using namespace std;


void _readArr(int* v, int sz);
    
void _printArr(int* v, int sz);
    
void _main_aux();
    
void _sort(int* v, int sz);
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

void _readArr(int* v, int sz) {
    int i;
    int x;
    i = 0;
    x = 0;
    int __aux___expr__save__0 = sz;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        cin >> x;
        v[i] = x;
        i = (i + 1);
    }
}


void _printArr(int* v, int sz) {
    int i;
    cout << '{';
    if((0 < sz)) {
        cout << v[0];
        i = 1;
        int __aux___expr__save__0 = (sz - 1);
        for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
            cout << ',';
            cout << v[i];
            i = (i + 1);
        }
    } 
    cout << '}';
    cout << '\n';
}


void _main_aux() {
    int x;
    int* v;
    x = 0;
    v = new int[10];
    _readArr(v,10);

    _printArr(v,10);

    _sort(v,10);

    _printArr(v,10);

}


void _sort(int* v, int sz) {
    int i;
    int j;
    int k;
    int aux;
    i = 0;
    j = 0;
    k = 0;
    aux = 0;
    int __aux___expr__save__0 = sz;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        j = (i + 1);
        k = i;
        int __aux___expr__save__1 = (sz - j);
        for(int __while_var_1 = 0 ; __while_var_1 < __aux___expr__save__1; __while_var_1++) {
            if((v[j] < v[k])) {
                k = j;
            } 
            j = (j + 1);
        }
        aux = v[i];
        v[i] = v[k];
        v[k] = aux;
        i = (i + 1);
    }
}