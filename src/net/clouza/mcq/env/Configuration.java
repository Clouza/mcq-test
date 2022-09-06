package net.clouza.mcq.env;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public final class Configuration {
    public static boolean DEBUG_INFO = true;

    public static void printStackTrace(Exception e) {
        if(DEBUG_INFO) {
            System.out.println("------------------------------");
            e.printStackTrace();
        }
    }

    public static String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
}
