package util;

public class Debug {
    public static boolean ENABLED = false;

    public static void log(String message) {
        if (ENABLED) {
            System.out.println("[DEBUG] " + message);
        }
    }
}