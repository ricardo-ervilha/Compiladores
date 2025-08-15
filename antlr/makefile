JUNIT_CP = junit-4.13.2.jar:hamcrest-core-1.3.jar

all: genparser compile

genparser: parser/Lang.g4
	java -jar antlr-4.8-complete.jar ./parser/Lang.g4 -no-listener

compile: genparser src/Teste.java src/Main.java util/Debug.java
	javac -cp .:antlr-4.8-complete.jar:$(JUNIT_CP):ST-4.3.1.jar \
      src/Teste.java src/Main.java util/Debug.java src/MainCPP.java

run: compile
	java -cp .:antlr-4.8-complete.jar src/Teste examples/$(file) $(args)

run_s2s: compile
	java -cp .:antlr-4.8-complete.jar:ST-4.3.1.jar src/MainCPP examples/$(file) $(args)

run_main_syn: compile
	java -cp .:antlr-4.8-complete.jar src/Main -syn examples/$(file)

run_main_interp: compile
	java -cp .:antlr-4.8-complete.jar src/Main -i examples/$(file)

run_main_type: clean compile
	java -cp .:antlr-4.8-complete.jar src/Main -t examples/$(file)

run_s2j: clean compile
	rm -f Programa.j
	java -cp .:antlr-4.8-complete.jar:ST-4.3.1.jar src/Main -gen examples/$(file)

run_jasmin: run_s2j
	java -jar jasmin.jar Programa.j  && java Programa

junit: compile
	java -cp src:.:antlr-4.8-complete.jar:$(JUNIT_CP) \
      org.junit.runner.JUnitCore src.Tester

lang: compile
	java -cp .:antlr-4.8-complete.jar:ST-4.3.1.jar src/Main $(filepath) $(flag) $(genfilepath)

clean:
	@echo "Limpando arquivos compilados e temporários..."
	find . -type f -name "*.class" -delete
	find . -type f -name "*~" -delete
	rm -f parser/*Listener.java parser/LangLexer* parser/LangParser.java parser/Lang.interp parser/Lang.tokens
	rm -rf out

