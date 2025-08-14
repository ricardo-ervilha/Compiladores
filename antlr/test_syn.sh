#!/bin/bash

JAR="antlr-4.8-complete.jar"
MAIN="src/Main"

ok=0
fail=0

echo "=== Testando casos CERTOS ==="
find examples/tests/sintaxe/certo -type f -name "*.lan" | while read -r file; do
    echo -n "Testando $file ... "
    output=$(java -cp .:$JAR $MAIN -syn "$file")
    if [ "$output" == "accept" ]; then
        echo "OK ✅"
        ((ok++))
    else
        echo "FALHOU ❌ (retornou '$output')"
        ((fail++))
    fi
done

echo ""
echo "=== Testando casos ERRADOS ==="
find examples/tests/sintaxe/errado -type f -name "*.lan" | while read -r file; do
    echo -n "Testando $file ... "
    output=$(java -cp .:$JAR $MAIN -syn "$file")
    if [ "$output" == "reject" ]; then
        echo "OK ✅"
        ((ok++))
    else
        echo "FALHOU ❌ (retornou '$output')"
        ((fail++))
    fi
done

echo ""
echo "===== RESUMO ====="
echo "Passaram: $ok"
echo "Falharam: $fail"
