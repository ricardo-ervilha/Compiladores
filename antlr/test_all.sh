#!/bin/bash

# Cores
YELLOW="\033[1;33m"
GREEN="\033[1;32m"
RED="\033[1;31m"
RESET="\033[0m"

JAR="antlr-4.8-complete.jar"
MAIN="src/Main"

total_ok=0
total_fail=0

run_tests() {
    local tipo=$1       # "Sintático" ou "Semântico"
    local flag=$2       # "-syn" ou "-t"
    local pasta=$3      # caminho base ex: examples/tests/sintaxe

    local ok=0
    local fail=0

    echo -e "${YELLOW}=== Testando casos CERTOS ($tipo) ===${RESET}"
    while IFS= read -r file; do
        echo -n "Testando $file ... "
        output=$(java -cp .:$JAR $MAIN $flag "$file")
        if [ "$output" == "accept" ]; then
            echo -e "${GREEN}OK ✅${RESET}"
            ok=$((ok+1))
        else
            echo -e "${RED}FALHOU ❌ (retornou '$output')${RESET}"
            fail=$((fail+1))
        fi
    done < <(find "$pasta/certo" -type f -name "*.lan")

    echo ""
    echo -e "${YELLOW}=== Testando casos ERRADOS ($tipo) ===${RESET}"
    while IFS= read -r file; do
        echo -n "Testando $file ... "
        output=$(java -cp .:$JAR $MAIN $flag "$file")
        if [ "$output" == "reject" ]; then
            echo -e "${GREEN}OK ✅${RESET}"
            ok=$((ok+1))
        else
            echo -e "${RED}FALHOU ❌ (retornou '$output')${RESET}"
            fail=$((fail+1))
        fi
    done < <(find "$pasta/errado" -type f -name "*.lan")

    echo ""
    echo -e "${YELLOW}===== RESUMO ($tipo) =====${RESET}"
    echo -e "${GREEN}Passaram: $ok${RESET}"
    echo -e "${RED}Falharam: $fail${RESET}"
    echo -e "${YELLOW}==========================${RESET}"
    echo ""

    total_ok=$((total_ok + ok))
    total_fail=$((total_fail + fail))
}

# -------------------
# Verifica parâmetro
# -------------------
if [ $# -eq 0 ]; then
    echo -e "${RED}Erro:${RESET} informe 'sintatico', 'semantico' ou 'ambos'."
    exit 1
fi

case "$1" in
    sintatico)
        run_tests "Sintático" "-syn" "examples/tests/sintaxe"
        ;;
    semantico)
        run_tests "Semântico" "-t" "examples/tests/semantica"
        run_tests "Semântico" "-t" "examples/tests/types"
        ;;
    ambos)
        run_tests "Sintático" "-syn" "examples/tests/sintaxe"
        run_tests "Semântico" "-t" "examples/tests/semantica"
        ;;
    *)
        echo -e "${RED}Erro:${RESET} opção inválida. Use 'sintatico', 'semantico' ou 'ambos'."
        exit 1
        ;;
esac

# Resumo geral
echo -e "${YELLOW}===== RESUMO GERAL =====${RESET}"
echo -e "${GREEN}Passaram: $total_ok${RESET}"
echo -e "${RED}Falharam: $total_fail${RESET}"
