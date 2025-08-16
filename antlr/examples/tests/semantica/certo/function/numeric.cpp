#include <iostream>
#include <tuple>

using namespace std;


tuple< float > _guessRoot(float x, float a);
    
tuple< float > _sqrt(float x);
    
tuple< float > _sqrtrec(float x, float x0);
    
tuple< float > _ln(float x);
    
tuple< float, float > _dowToOneRec(float n, float dc);
    
tuple< float > _lnb(float base, float x);
    
void _main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

tuple< float > _guessRoot(float x, float a) {
    if((x < (a * a))) {
        return make_tuple(get< 0 >(_guessRoot(x, (a / 2.0))));
    } 
    return make_tuple(a);
}


tuple< float > _sqrt(float x) {
    float g;
    g = get< 0 >(_guessRoot(x, (x / 2.0)));
    return make_tuple(get< 0 >(_sqrtrec(x, g)));
}


tuple< float > _sqrtrec(float x, float x0) {
    float x1;
    float diff;
    x1 = ((x0 + (x / x0)) / 2.0);
    diff = ((x1 * x1) - x);
    if((diff < 0.0)) {
        diff = ((0.0 - 1.01) * diff);
    } 
    if((diff < 0.000001)) {
        return make_tuple(x1);
    } 
    return make_tuple(get< 0 >(_sqrtrec(x, x1)));
}


tuple< float > _ln(float x) {
    float a;
    float n;
    float y;
    float k;
    float s;
    float t;
    float log;
    auto __return_function_func_call_  = _dowToOneRec(x,1.0);
    a = get< 0 >(__return_function_func_call_);
    n = get< 1 >(__return_function_func_call_);
    y = ((a - 1.0) / (a + 1.0));
    k = 0.0;
    s = 0.0;
    t = y;
    int __aux___expr__save__0 = 20;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        s = (s + (t / ((2.0 * k) + 1.0)));
        t = ((t * y) * y);
        k = (k + 1.0);
    }
    s = (2.0 * s);
    log = (((n - 1.0) * 2.302585093) + s);
    return make_tuple(log);
}


tuple< float, float > _dowToOneRec(float n, float dc) {
    if((n == 1.0)) {
        return make_tuple(n, dc);
    } else {
        if(((1.0 < n) && (n < 10.0))) {
            return make_tuple(n, dc);
        } else {
            if(!(!(10.0 < n) && !(n == 10.0))) {
                auto __return_function_func_call_  = _dowToOneRec((n / 10.0),(dc + 1.0));
                n = get< 0 >(__return_function_func_call_);
                dc = get< 1 >(__return_function_func_call_);
                return make_tuple(n, dc);
            } else {
                if((n < 1.0)) {
                    auto __return_function_func_call_  = _dowToOneRec((n * 10.0),(dc - 1.0));
                    n = get< 0 >(__return_function_func_call_);
                    dc = get< 1 >(__return_function_func_call_);
                    return make_tuple(n, dc);
                } 
            }

        }

    }

    return make_tuple(0.0, 0.0);
}


tuple< float > _lnb(float base, float x) {
    return make_tuple((get< 0 >(_ln(x)) / get< 0 >(_ln(base))));
}


void _main_aux() {
    float log;
    log = get< 0 >(_lnb(10.0, 255.0));
    cout << log;
    cout << '\n';
    cout << get< 0 >(_sqrt(2.0));
    cout << '\n';
}