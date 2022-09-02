package net.clouza.mcq;

import net.clouza.mcq.helpers.Csv;
import net.clouza.mcq.throwable.FileOrDirectory;
import net.clouza.mcq.throwable.Magic;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class Main {
    public static void main(String[] args) throws FileOrDirectory {
         Csv javaSet = new Csv("./mcq-sets");
//        Csv javaSet = new Csv("./mcq-sets/MCQ_set_Java Basic.csv");

         javaSet.scanCsvFileInDirectory().readFile().show();
//            javaSet.readFile().show();

    }
}