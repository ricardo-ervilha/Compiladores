# Compiladores
Repositório associado ao trabalho de Compiladores 

Limpar arquivos compilados gerados

    make clean

Rodar o analisador sintático

     make run_main_syn file=exemploD.txt

Rodar o interpretador

     make run_main_interp file=exemploD.txt

Excluir class geradas

    find . -name "*.class" -delete

Gerar o parser

    java -jar antlr-4.8-complete.jar ./parser/Lang.g4

Compilar Main.java

    javac -cp .:antlr-4.8-complete.jar Teste.java
    javac -cp .:antlr-4.8-complete.jar Main.java

Rodar Sintático
    
    java -cp .:antlr-4.8-complete.jar src/Main -syn examples/exemploD.txt

Rodar Interpretador
    
    java -cp .:antlr-4.8-complete.jar src/Main -i examples/exemploD.txt
