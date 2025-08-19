# 🐉 Teoria dos Compiladores (DCC045) - 2025.1

## Alunos

- **Lucas Silva Santana**
- **Ricardo Ervilha Silva**

---

Repositório relacionado ao trabalho da disciplina **DCC045 - Teoria de Compiladores**. O objetivo do trabalho foi o desenvolvimento de um Compilador para a linguagem `Lang`, que contempla etapas de análise léxica, análise sintática, interpretação, análise semântica, geração de código *source-to-source* (no caso foi `C++`) e geração de código para arquitetura alvo (no caso foi `Jasmin`).

<img width="431" height="385" alt="image" src="https://github.com/user-attachments/assets/2ae38fce-5efb-4973-8c38-b6e1aeecd6af" />

## Lang

A linguagem `Lang` tem propósito meramente educacional, contendo construções que se assemelham a de várias linguagens conhecidas. Em linhas gerais, um programa nesta linguagem é constituído por um conjunto de definições de tipos de dados e funções. A estrutura sintática da linguagem é dividida em: tipos de dados e declarações, funções, comandos e expressões. Um exemplo de programa é:

```
main () {
  print fat(10)[0];
}

fat ( num :: Int ) : Int {
  if ( num < 1 )
  return 1;
  else
  return num * fat ( num-1 )[0];
}

divmod ( num :: Int , div :: Int ) : Int , Int {
  q = num / div ;
  r = num % div ;
  return q , r ;
}
```

---

## Estrutura do Projeto

```
.
├── ast/                      # Classes da AST (Abstract syntax tree)
├── docs/                     # Documentações do projeto
├── examples/                 # Arquivos de teste da linguagem
├── parser/                   # Gramática ANTLR (Lang.g4)
├── src/                      # Código-fonte principal (Main.java)
├── util/                     # Classes utiliárias
├── visitors/                 # Classes que implementam o padrão de projeto Visitor
├── *.jar                     # Arquivos .jar para execução de algumas ferramentas
├── Makefile                  # Automatização da execução do código
└── README.md                 
```

---

## Requisitos

O projeto foi desenvolvido na linguagem **Java**. Os requisitos de executação são:

- Java 17
- ANTLR 4.8
- `make`

---

## Guia de execução

O projeto da disciplina está em tornos das principais etapas para desenvolver um compilador, que estão ilustrados na imagem.

<img width="597" height="470" alt="image" src="https://github.com/user-attachments/assets/83ddefe1-7ee8-4f79-9760-bf7f94ea246e" />

Para as análises **léxica** e **sintática**, foi utilizada a ferramenta **ANTLR**, que realiza a análise sintática por meio do algoritmo **LL(\*)**.  

Na etapa de **geração de código *source-to-source* (S2S)**, empregou-se a ferramenta **StringTemplate**, que possibilita a geração de código de forma mais simples e estruturada.  

Já o **interpretador** e a **análise semântica** foram implementados manualmente, seguindo o padrão de projeto **Visitor**.

Para executar o projeto, basta:

```bash
$ git clone https://github.com/ricardo-ervilha/Compiladores.git
$ cd antlr
$ make lang flag=x filepath=y 
```

Onde $x$ é uma flag para a execução, que pode ser: -syn (Análise sintática), -i (Interpretação), -t (Análise Semântica), -src (Geração de código S2S - C++), -gen (Geração de código Jasmin). Já $y$, indica o caminho relativo onde estará o arquivo `.lan` a ser testado.



