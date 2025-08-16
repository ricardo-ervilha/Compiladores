#include <iostream>
#include <tuple>

using namespace std;

class Aluno {
    private:
        int id;
        float* notas;

    public:
        static tuple< Aluno* > _novoAluno(int nome) {
            Aluno* a;
            int i;
            a = new Aluno;
            a->id = nome;
            a->notas = new float[4];
            int __aux___expr__save__0 = 4;
            for( i = 0 ; i < __aux___expr__save__0; i++) {
                a->notas[i] = 0.0;
            }
            return make_tuple(a);
        }
        static void _definirNota(Aluno* a, int i, float v) {
            if((i < 0)) {
                i = ((0 - 1) * i);
            } 
            i = (i % 4);
            a->notas[i] = v;
        }
        static tuple< float > _nota(Aluno* a, int i) {
            if((i < 0)) {
                i = ((0 - 1) * i);
            } 
            i = (i % 4);
            return make_tuple(a->notas[i]);
        }
        static void _imprimeAluno(Aluno* a) {
            int i;
            cout << 'A';
            cout << 'l';
            cout << 'u';
            cout << 'n';
            cout << 'o';
            cout << ' ';
            cout << a->id;
            cout << ' ';
            int __aux___expr__save__0 = 4;
            for( i = 0 ; i < __aux___expr__save__0; i++) {
                cout << a->notas[i];
                cout << ';';
            }
            cout << '\n';
        }
};

void _main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    _main_aux();
}

void _main_aux() {
    Aluno* aluno;
    aluno = get< 0 >(Aluno::_novoAluno(150));
    Aluno::_definirNota(aluno,0,10.0);

    Aluno::_definirNota(aluno,1,9.5);

    Aluno::_definirNota(aluno,2,8.9);

    Aluno::_imprimeAluno(aluno);

}