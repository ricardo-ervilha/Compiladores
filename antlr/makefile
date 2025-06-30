all: genparser compile

genparser: parser/Lang.g4
	java -jar antlr-4.8-complete.jar ./parser/Lang.g4 -no-listener

compile: genparser Teste.java
	javac -cp .:antlr-4.8-complete.jar Teste.java
	javac -cp .:antlr-4.8-complete.jar Main.java

run: compile genparser
	java -cp .:antlr-4.8-complete.jar Teste examples/$(filter-out $@,$(MAKECMDGOALS))

run_main_syn: compile genparser
	java -cp .:antlr-4.8-complete.jar Main -syn examples/$(filter-out $@,$(MAKECMDGOALS))

clean:
	find . -type f -name "*.class" -delete
	find . -type f -name "*~" -delete
	rm -R parser/*Listener.java parser/LangLexer* parser/LangParser.java parser/Lang.interp parser/Lang.tokens

