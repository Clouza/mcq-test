package net.clouza.mcq.throwable;

import net.clouza.mcq.env.Configuration;

import java.io.File;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class FileOrDirectory extends Exception {
    public FileOrDirectory(Exception e, File path, boolean isExists, boolean isDirectory, String... message) {
        if(isExists && isDirectory) {
            System.out.printf(message[0], path);
        } else {
            System.out.printf(message[1], path);
        }
        System.out.print("\n");

        Configuration.printStackTrace(e);
    }
}
