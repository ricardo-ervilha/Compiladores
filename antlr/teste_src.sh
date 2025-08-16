#!/bin/bash

if [ $# -ne 2 ]; then
    echo "Uso: $0 <pasta> <arquivo>"
    echo "Exemplo: $0 simple assoc"
    exit 1
fi

PASTA=$1
ARQUIVO=$2

BASE="examples/tests/semantica/certo"

LAN_FILE="$BASE/$PASTA/$ARQUIVO.lan"
CPP_FILE="$BASE/$PASTA/$ARQUIVO.cpp"
EXE_FILE="$BASE/$PASTA/$ARQUIVO"

echo ">> Rodando make lang para $LAN_FILE"
make lang flag=-src filepath=$LAN_FILE

echo ">> Compilando $CPP_FILE"
g++ "$CPP_FILE" -o "$EXE_FILE"

echo ">> Executando $EXE_FILE"
"$EXE_FILE"