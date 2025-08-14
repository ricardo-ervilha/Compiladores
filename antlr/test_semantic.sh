#!/bin/bash

JAR="antlr-4.8-complete.jar"
MAIN="src/Main"

ok=0
fail=0

echo "=== Testando casos CERTOS (Semântico) ==="
find examples/tests/semantica/certo -type f -name "*.lan" | while read -r file; do
    echo -n "Testando $file ... "
    output=$(java -cp .:$JAR $MAIN -t "$file")
    if [ "$output" == "accept" ]; then
        echo "OK ✅"
        ((ok++))
    else
        echo "FALHOU ❌ (retornou '$output')"
        ((fail++))
    fi
done

echo ""
echo "=== Testando casos ERRADOS (Semântico) ==="
find examples/tests/semantica/errado -type f -name "*.lan" | while read -r file; do
    echo -n "Testando $file ... "
    output=$(java -cp .:$JAR $MAIN -t "$file")
    if [ "$output" == "reject" ]; then
        echo "OK ✅"
        ((ok++))
    else
        echo "FALHOU ❌ (retornou '$output')"
        ((fail++))
    fi
done

echo ""
echo "===== RESUMO (Semântico) ====="
echo "Passaram: $ok"
echo "Falharam: $fail"
