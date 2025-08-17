/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 *
 *
 */
package util;

public class Debug {
    /*Classe para facilitar o debug do código... */
    public static boolean ENABLED = false;

    public static void log(String message) {
        if (ENABLED) {
            System.out.println("[DEBUG] " + message);
        }
    }
}