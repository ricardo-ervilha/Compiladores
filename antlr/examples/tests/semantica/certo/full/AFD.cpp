#include <iostream>
#include <tuple>

using namespace std;

struct Transition{
    char sym;
    int dest;
};

struct AFD{
    Transition*** st;
    int* numt;
    int numStates;
    int start;
};

tuple< AFD* > _mkAutomata(int numStates);
    
void _setFinal(AFD* m, int st);
    
tuple< bool > _isFinal(AFD* m, int st);
    
void _setNumTransitions(AFD* m, int st, int n);
    
void _addTransition(AFD* m, int st, char a, int d);
    
tuple< int > _abs(int n);
    
void _printAutomata(AFD* m);
    
tuple< int > _delta(AFD* m, int st, char c);
    
tuple< int > _runAFDHelper(AFD* m, int st, int p, int sz, char* s);
    
tuple< int > _runAFD(AFD* m, char* s, int sz);
    
tuple< AFD* > _myAFD();
    
void _main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

tuple< AFD* > _mkAutomata(int numStates) {
    AFD* m;
    int i;
    m = new AFD;
    m->st = new Transition**[numStates];
    m->numt = new int[numStates];
    i = 0;
    int __aux___expr__save__0 = numStates;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        m->numt[i] = 1;
        i = (i + 1);
    }
    m->numStates = numStates;
    m->start = 0;
    return make_tuple(m);
}


void _setFinal(AFD* m, int st) {
    m->numt[st] = (0 - m->numt[st]);
}


tuple< bool > _isFinal(AFD* m, int st) {
    return make_tuple((m->numt[st] < 0));
}


void _setNumTransitions(AFD* m, int st, int n) {
    m->st[st] = new Transition*[n];
    m->numt[st] = (n + 1);
}


void _addTransition(AFD* m, int st, char a, int d) {
    int i;
    bool add;
    i = 0;
    add = true;
    int __aux___expr__save__0 = (get< 0 >(_abs(m->numt[st])) - 1);
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        if(((m->st[st][i] == nullptr) && add)) {
            m->st[st][i] = new Transition;
            m->st[st][i]->sym = a;
            m->st[st][i]->dest = d;
            add = false;
        } 
        i = (i + 1);
    }
}


tuple< int > _abs(int n) {
    if((0 < n)) {
        return make_tuple(n);
    } 
    return make_tuple((0 - n));
}


void _printAutomata(AFD* m) {
    int st;
    int k;
    st = 0;
    int __aux___expr__save__0 = m->numStates;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        if(get< 0 >(_isFinal(m, st))) {
            cout << '*';
        } 
        cout << st;
        cout << ' ';
        cout << (get< 0 >(_abs(m->numt[st])) - 1);
        cout << ' ';
        if(((m->st[st] != nullptr) && (0 < (get< 0 >(_abs(m->numt[st])) - 1)))) {
            k = 0;
            int __aux___expr__save__1 = (get< 0 >(_abs(m->numt[st])) - 1);
            for(int __while_var_1 = 0 ; __while_var_1 < __aux___expr__save__1; __while_var_1++) {
                if((m->st[st][k] != nullptr)) {
                    cout << '[';
                    cout << m->st[st][k]->sym;
                    cout << ' ';
                    cout << '-';
                    cout << '>';
                    cout << ' ';
                    cout << m->st[st][k]->dest;
                    cout << ']';
                    cout << ' ';
                    k = (k + 1);
                } 
            }
        } 
        cout << '\n';
        st = (st + 1);
    }
}


tuple< int > _delta(AFD* m, int st, char c) {
    int k;
    if(((m->st[st] != nullptr) && (0 < (get< 0 >(_abs(m->numt[st])) - 1)))) {
        k = 0;
        int __aux___expr__save__0 = (get< 0 >(_abs(m->numt[st])) - 1);
        for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
            if((m->st[st][k]->sym == c)) {
                return make_tuple(m->st[st][k]->dest);
            } 
            k = (k + 1);
        }
    } 
    return make_tuple((0 - 1));
}


tuple< int > _runAFDHelper(AFD* m, int st, int p, int sz, char* s) {
    if((p < sz)) {
        return make_tuple(get< 0 >(_runAFDHelper(m, get< 0 >(_delta(m, st, s[p])), (p + 1), sz, s)));
    } 
    return make_tuple(st);
}


tuple< int > _runAFD(AFD* m, char* s, int sz) {
    return make_tuple(get< 0 >(_runAFDHelper(m, m->start, 0, sz, s)));
}


tuple< AFD* > _myAFD() {
    AFD* afd;
    afd = get< 0 >(_mkAutomata(3));
    _setNumTransitions(afd,0,2);

    _addTransition(afd,0,'a',0);

    _addTransition(afd,0,'b',1);

    _setNumTransitions(afd,1,2);

    _addTransition(afd,1,'a',0);

    _addTransition(afd,1,'b',2);

    _setNumTransitions(afd,2,2);

    _addTransition(afd,2,'a',2);

    _addTransition(afd,2,'b',2);

    _setFinal(afd,2);

    return make_tuple(afd);
}


void _main_aux() {
    AFD* afd;
    char* str;
    int stop;
    afd = get< 0 >(_myAFD());
    str = new char[4];
    stop = 0;
    str[0] = 'a';
    str[1] = 'b';
    str[2] = 'b';
    str[3] = 'a';
    stop = get< 0 >(_runAFD(afd, str, 4));
    cout << stop;
    cout << ' ';
    cout << get< 0 >(_isFinal(afd, stop));
    cout << '\n';
    str[0] = 'a';
    str[1] = 'b';
    str[2] = 'a';
    str[3] = 'b';
    stop = get< 0 >(_runAFD(afd, str, 4));
    cout << stop;
    cout << ' ';
    cout << get< 0 >(_isFinal(afd, stop));
    cout << '\n';
}