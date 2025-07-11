# 🛠️ Compiladores – Projeto da Disciplina DCC045

Repositório relacionado ao trabalho da disciplina **DCC045 - Teoria de Compiladores**, contendo o analisador sintático e interpretador construídos com ANTLR 4.8 para uma linguagem definida pelo arquivo `Lang.g4`.

---

## 📁 Estrutura do Projeto

```
.
├── ast/                      # Classes da AST
├── docs/                     # documentações do projeto
├── examples/                 # Arquivos de teste da linguagem
├── parser/                   # Gramática ANTLR (Lang.g4)
├── src/                      # Código-fonte principal (Main.java)
├── util/                     # Classes utiliárias
├── visitors/                 # Classes que implementam o padrão de projeto visitor
├── antlr-4.8-complete.jar    # Biblioteca do ANTLR
├── Makefile                  # Automatização de tarefas
└── README.md                 # Este arquivo
```

---

## ⚙️ Requisitos

- Java 17
- ANTLR 4.8
- `make`

---

## 🧪 Comandos Úteis

### 🔄 Limpar arquivos compilados

Remove todos os arquivos `.class` gerados:

```bash
make clean
# ou manualmente:
find . -name "*.class" -delete
```

---

### 🧰 Gerar o Parser com ANTLR

Gera os analisadores léxico e sintático a partir da gramática `Lang.g4`:

```bash
java -jar antlr-4.8-complete.jar ./parser/Lang.g4
```

---

### 🧱 Compilar os arquivos Java

```bash
javac -cp .:antlr-4.8-complete.jar src/Teste.java
javac -cp .:antlr-4.8-complete.jar src/Main.java
```

> ⚠️ **Atenção:** Em sistemas Windows, use `;` em vez de `:` no classpath (`-cp`).

---

### 🚀 Executar o Analisador Sintático

```bash
make run_main_syn file=exemploD.txt
# ou diretamente:
java -cp .:antlr-4.8-complete.jar src/Main -syn examples/exemploD.txt
```

---

### 🧮 Executar o Interpretador

```bash
make run_main_interp file=exemploD.txt
# ou diretamente:
java -cp .:antlr-4.8-complete.jar src/Main -i examples/exemploD.txt
```

---

## 👥 Alunos

- **Lucas Silva Santana**
- **Ricardo Ervilha Silva**

---

## 📚 Disciplina

**DCC045 - Teoria de Compiladores**  
Departamento de Ciência da Computação  - UFJF



