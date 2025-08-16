#include <iostream>
#include <tuple>

using namespace std;

struct Node{
    int val;
    Node* next;
};

struct LList{
    Node* head;
    int size;
};

tuple< LList* > _createList();
    
tuple< Node* > _lastNode(Node* h);
    
void _addNode(LList* l, int x);
    
tuple< Node* > _removeHead(LList* l);
    
void _printList(Node* h);
    
void _printLList(LList* l);
    
void _main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

tuple< LList* > _createList() {
    LList* n;
    n = new LList;
    n->head = nullptr;
    n->size = 0;
    return make_tuple(n);
}


tuple< Node* > _lastNode(Node* h) {
    if((h->next == nullptr)) {
        return make_tuple(h);
    } 
    return make_tuple(get< 0 >(_lastNode(h->next)));
}


void _addNode(LList* l, int x) {
    Node* no;
    Node* last;
    no = new Node;
    no->val = x;
    no->next = nullptr;
    if((l->head == nullptr)) {
        l->head = no;
    } else {
        last = get< 0 >(_lastNode(l->head));
        last->next = no;
    }

    l->size = (l->size + 1);
}


tuple< Node* > _removeHead(LList* l) {
    Node* node;
    if((l->head != nullptr)) {
        node = l->head;
        l->head = l->head->next;
        l->size = (l->size - 1);
        return make_tuple(node);
    } 
    return make_tuple(nullptr);
}


void _printList(Node* h) {
    if((h != nullptr)) {
        cout << h->val;
        cout << '-';
        cout << '>';
        _printList(h->next);

    } 
    if((h == nullptr)) {
        cout << 'N';
        cout << 'U';
        cout << 'L';
        cout << 'L';
        cout << '\n';
    } 
}


void _printLList(LList* l) {
    cout << l->size;
    cout << ':';
    _printList(l->head);

}


void _main_aux() {
    int k;
    LList* l;
    Node* head;
    k = 65;
    l = get< 0 >(_createList());
    _addNode(l,k);

    int __aux___expr__save__0 = 5;
    for(int __while_var_0 = 0 ; __while_var_0 < __aux___expr__save__0; __while_var_0++) {
        k = (k + 1);
        _addNode(l,k);

    }
    _printLList(l);

    head = get< 0 >(_removeHead(l));
    _printLList(l);

}