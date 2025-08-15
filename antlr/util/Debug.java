package util;

public class Debug {
    public static boolean ENABLED = true;

    public static void log(String message) {
        if (ENABLED) {
            System.out.println("[DEBUG] " + message);
        }
    }
}