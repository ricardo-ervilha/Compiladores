all: genparser compile

genparser: parser/Lang.g4
	java -jar antlr-4.8-complete.jar ./parser/Lang.g4 -no-listener

compile: genparser src/Teste.java src/Main.java
	javac -cp .:antlr-4.8-complete.jar src/Teste.java
	javac -cp .:antlr-4.8-complete.jar src/Main.java

run: compile
	java -cp .:antlr-4.8-complete.jar src/Teste examples/$(file)

run_main_syn: compile
	java -cp .:antlr-4.8-complete.jar src/Main -syn examples/$(file)

run_main_interp: compile
	java -cp .:antlr-4.8-complete.jar src/Main -i examples/$(file)

clean:
	@echo "Limpando arquivos compilados e temporários..."
	find . -type f -name "*.class" -delete
	find . -type f -name "*~" -delete
	rm -f parser/*Listener.java parser/LangLexer* parser/LangParser.java parser/Lang.interp parser/Lang.tokens

