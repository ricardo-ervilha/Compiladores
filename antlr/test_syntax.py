import subprocess
import os
import sys

# Verifica se o diretório foi passado como argumento
if len(sys.argv) != 2:
    print("Uso: python test_syntax.py <diretorio_de_testes>")
    print("Exemplo: python test_syntax.py examples/tests/sintaxe/errado")
    sys.exit(1)

TEST_DIR = sys.argv[1]
JAR_PATH = "antlr-4.8-complete.jar"
MAIN_CLASS = "src/Main"

# Verifica se o diretório existe
if not os.path.isdir(TEST_DIR):
    print(f"Erro: diretório '{TEST_DIR}' não encontrado.")
    sys.exit(1)

# Define separador do classpath dependendo do SO
classpath_separator = ";" if os.name == "nt" else ":"

pass_count = 0
fail_count = 0

print(f"Iniciando testes sintáticos em: {TEST_DIR}")
print("-" * 40)

for filename in os.listdir(TEST_DIR):
    if filename.endswith(".lan"):
        file_path = os.path.join(TEST_DIR, filename)
        try:
            result = subprocess.run(
                ["java", "-cp", f".{classpath_separator}{JAR_PATH}", MAIN_CLASS, "-syn", file_path],
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                text=True
            )
            output = result.stdout.strip()
            if output == "accept":
                print(f"[ACCECPT] {filename}")
                pass_count += 1
            else:
                print(f"[REJECT] {filename} -> Output: {output}")
                fail_count += 1
        except Exception as e:
            print(f"[ERROR] {filename} -> {e}")
            fail_count += 1

print("-" * 40)
print(f"Total: {pass_count + fail_count} arquivos testados")
print(f"Passaram: {pass_count}")
print(f"Falharam: {fail_count}")

# Exit code para uso em CI
sys.exit(1 if fail_count > 0 else 0)
