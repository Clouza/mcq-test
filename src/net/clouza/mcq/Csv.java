package net.clouza.mcq;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @author Clouza (Siwa)
 * @version 1.0.0
 * @link https://github.com/clouza
 */
public class Csv {
    Scanner input = new Scanner(System.in);
    private File file;
    private HashMap<Integer, String> question = new HashMap<>();
    private HashMap<Integer, String[]> choice = new HashMap<>();
    private HashMap<Integer, String> correctAnswer = new HashMap<>();
    private String[] mcq;
    private ArrayList<String> csv = new ArrayList<>();

    public Csv(String path) {
        this.setFile(path);
    }

    private void setFile(String file) {
        this.file = new File(file);
    }

    private File getFile() {
        return this.file;
    }

    public Csv readFile() {
        try {
            if(!csv.isEmpty()) {
                ArrayList<String> fileCollections = new ArrayList<>();

                System.out.println("[ Select file ]");
                for (int i = 0; i < csv.size(); i++) {
                    fileCollections.add(csv.get(i));

                    String[] fileName;
                    fileName = csv.get(i).split("\\.");
                    fileName = fileName[1].split("\\\\");
                    fileName = fileName[2].split("_");
                    System.out.printf("%d. %s \n", i + 1, fileName[2]);
                }

                System.out.print("Which number: ");
                int file = input.nextInt();
                try {
                    this.setFile(fileCollections.get(file - 1));
                } catch (Exception e) {
                    System.out.println("File doesn't exists");
                    return this;
                }
            }

            BufferedReader br = new BufferedReader(new FileReader(this.getFile()));
            String result;
            boolean isSkipped = true;

            while((result = br.readLine()) != null) {
                this.mcq = result.split(",");

                if(!isSkipped) {
                    int key = Integer.parseInt(this.mcq[0]);
                    this.question.put(key, this.mcq[1]);
                    this.choice.put(key,
                            new String[]{
                                    this.mcq[2],
                                    this.mcq[3],
                                    this.mcq[4],
                                    this.mcq[5]}
                    );
                    this.correctAnswer.put(key, this.mcq[6]);
                }
                isSkipped = false;
            }
        } catch (Exception e) {
            if(isExists() && isDirectory()) {
                System.out.printf("It's not a file \n%s", file.getAbsoluteFile());
            } else {
                System.out.printf("File doesn't exists \n%s", file.getAbsoluteFile());
            }

        }
        return this;
    }

    public void scanFileInDirectory() {
        try {
            for (File dir: file.listFiles()) {
                System.out.println(dir.getName());
            }
        } catch (Exception e) {
            if(isExists() && isFile()) {
                System.out.printf("It's not a directory \n%s", file.getAbsoluteFile());
            } else {
                System.out.printf("Directory not exists \n%s", file.getAbsoluteFile());
            }
        }
    }

    public Csv scanCsvFileInDirectory() {
        try {
            for (File dir: file.listFiles()) {
                String[] csv = dir.getName().split("\\.");
                if(csv[csv.length - 1].equals("csv")) {
                    this.csv.add(dir.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            if(isExists() && isFile()) {
                System.out.printf("It's not a directory \n%s", file.getAbsoluteFile());
            } else {
                System.out.printf("Directory not exists \n%s", file.getAbsoluteFile());
            }
        }
        return this;
    }

    public void get() {
        String[] abcd = {"A", "B", "C", "D"};
        for (int i = 1; i <= question.size(); i++) {
            System.out.printf("%d. %s \n", i, question.get(i));
            for (int j = 0; j < choice.get(i).length; j++) {
                System.out.printf("%s. %s \n", abcd[j], choice.get(i)[j]);
            }
            System.out.print("\n");
        }
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }
    public boolean isFile() { return file.isFile(); }
    public boolean isExists() { return file.exists(); }
}
