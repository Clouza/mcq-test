package net.clouza.mcq;

/**
 * @author Clouza (Siwa)
 * @version 1.0.0
 * @link https://github.com/clouza
 */
public class Main {
    public static void main(String[] args)  {
        Csv javaSet = new Csv("./mcq-sets");
        // Csv javaSet = new Csv("./mcq-sets/MCQ_set_Java Basic.csv");

        javaSet.scanCsvFileInDirectory().readFile().get();
        // javaSet.readFile().get();
    }
}