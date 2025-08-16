#include <iostream>
#include <tuple>

using namespace std;

class Aluno {
    private:
        int id;
        float* notas;

    public:
        static tuple< Aluno* > novoAluno(int nome) {
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
        static void definirNota(Aluno* a, int i, float v) {
            if((i < 0)) {
                i = ((0 - 1) * i);
            } 
            i = (i % 4);
            a->notas[i] = v;
        }
        static tuple< float > nota(Aluno* a, int i) {
            if((i < 0)) {
                i = ((0 - 1) * i);
            } 
            i = (i % 4);
            return make_tuple(a->notas[i]);
        }
        static void imprimeAluno(Aluno* a) {
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

void main_aux();
    

int main(int argc, char* argv[]) {
    cout << boolalpha;
    main_aux();
}

void main_aux() {
    Aluno* aluno;
    aluno = get< 0 >(Aluno::novoAluno(150));
    Aluno::definirNota(aluno,0,10.0);

    Aluno::definirNota(aluno,1,9.5);

    Aluno::definirNota(aluno,2,8.9);

    Aluno::imprimeAluno(aluno);

}