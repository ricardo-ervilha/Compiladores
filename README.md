# Compiladores
Repositório associado ao trabalho de Compiladores 

make clean

make all

Teste

    make run exemplo1.txt
Rodar sintatico com Main

    make run_main_syn exemplo1.txt

Excluir class gerados

    find . -name "*.class" -delete

parser dentro da parser

    java -jar antlr-4.8-complete.jar ./parser/Lang.g4

Compilar Teste.java

    javac -cp .:antlr-4.8-complete.jar Teste.java
    javac -cp .:antlr-4.8-complete.jar Main.java

Rodar Teste

    java -cp .:antlr-4.8-complete.jar Teste examples/exemplo1.txt
    java -cp .:antlr-4.8-complete.jar Main -syn examples/exemplo1.txt 