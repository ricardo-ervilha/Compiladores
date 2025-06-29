# Compiladores
Repositório associado ao trabalho de Compiladores 

make clean

make compile

make run exemplo1.txt

Excluir class gerados
    find . -name "*.class" -delete

parser dentro da parser
    - java -jar antlr-4.8-complete.jar ./parser/Lang.g4

Compilar Teste.java
    - javac -cp .:antlr-4.8-complete.jar Teste.java

Rodar Teste
    - java -cp .:antlr-4.8-complete.jar Teste exemplo1.txt 