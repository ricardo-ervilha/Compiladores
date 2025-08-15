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