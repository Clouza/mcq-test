package net.clouza.mcq.helpers;

import net.clouza.mcq.throwable.FileOrDirectory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class Csv {
    private Scanner input = new Scanner(System.in);
    private File file;
    private int questionNumber;
    private int key;
    private HashMap<Integer, String> question = new HashMap<>();
    private HashMap<Integer, ArrayList<String>> options = new HashMap<>();
    private HashMap<Integer, ArrayList<String>> correctAnswer = new HashMap<>();
    private String[] mcq;
    private ArrayList<String> csv = new ArrayList<>();
    private ArrayList<String> files = new ArrayList<>();
    private boolean isCsv = false;

    public Csv(String p) {
        if(p.contains("csv")) {
            this.isCsv = true;
        }
        this.setFile(p);
    }

    private void setFile(String file) {
        this.file = new File(file);
    }

    private File getFile() {
        return this.file;
    }

    public Csv readFile() throws FileOrDirectory {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getFile()));
            String result;
            boolean isSkipped = true;

            while((result = br.readLine()) != null) {
                if(!this.csv.isEmpty() || this.isCsv) {
                    this.mcq = result.split(",");

                    for (int i = 0; i < this.mcq.length; i++) {
                        if(this.mcq[i].contains("Correct")) {
                            this.key = i;
                        }
                    }

                    if(!isSkipped) {
                        int questionNumber = Integer.parseInt(this.mcq[0]); // key
                        this.questionNumber = questionNumber;

                        ArrayList<String> correctAnswer = new ArrayList<>();
                        for (int i = this.key; i < this.mcq.length; i++) {
                            correctAnswer.add(this.mcq[i]);
                        }
                        this.correctAnswer.put(questionNumber, correctAnswer); // correct answer

                        this.question.put(questionNumber, this.mcq[1]);

                        ArrayList<String> options = new ArrayList<>();
                        for (int i = 2; i < this.key; i++) {
                            options.add(this.mcq[i]);
                        }
                        this.options.put(questionNumber, options);

                    }
                    isSkipped = false;
                } else {
                    System.out.println(result);
                }
            }
        } catch (Exception e) {
            throw new FileOrDirectory(e, file.getAbsoluteFile(), this.isExists(), this.isDirectory(), "It's not a file \n%s", "File doesn't exists \n%s");
        }
        return this;
    }

    public Csv scanFileInDirectory() throws FileOrDirectory {
        try {
            ArrayList<String> fileCollections = new ArrayList<>();
            for (File dir: file.listFiles()) {
                this.files.add(dir.getAbsolutePath());
            }

            System.out.println("[ Select file ]");
            for (int i = 0; i < this.files.size(); i++) {
                fileCollections.add(this.files.get(i));

                String[] fileName;
                fileName = this.files.get(i).split("[\\\\]");
                System.out.printf("%d. %s \n", i + 1, fileName[fileName.length - 1]);
            }

            while(true) {
                try {
                    System.out.print("Which number: ");
                    int file = input.nextInt();

                    this.setFile(fileCollections.get(file - 1));
                    return this;
                } catch (Exception e) {
                    System.out.println("File doesn't exists\n");
                }
            }
        } catch (Exception e) {
            throw new FileOrDirectory(e, file.getAbsoluteFile(), this.isExists(), this.isFile(), "It's not a directory \n%s", "Directory not exists \n%s");
        }
    }

    public Csv scanCsvFileInDirectory() throws FileOrDirectory {
        try {
            ArrayList<String> fileCollections = new ArrayList<>();
            for (File dir: file.listFiles()) {
                String[] csv = dir.getName().split("\\.");
                if(csv[csv.length - 1].equals("csv")) {
                    this.csv.add(dir.getAbsolutePath());
                }
            }

            System.out.println("Choose your Multiple Choice Question Set. The Options are :");
            for (int i = 0; i < this.csv.size(); i++) {
                fileCollections.add(this.csv.get(i));

                String[] fileName;
                fileName = this.csv.get(i).split("\\.");
                fileName = fileName[1].split("\\\\");
                fileName = fileName[2].split("_");
                System.out.printf("%d. %s \n", i + 1, fileName[2]);
            }

            while(true) {
                try {
                    System.out.print("Which number: ");
                    int file = input.nextInt();

                    this.setFile(fileCollections.get(file - 1));
                    this.isCsv = true;
                    return this;
                } catch (Exception e) {
                    System.out.println("File doesn't exists\n");
                }
            }
        } catch (Exception e) {
            throw new FileOrDirectory(e, file.getAbsoluteFile(), this.isExists(), this.isFile(), "It's not a directory \n%s", "Directory not exists \n%s");
        }
    }

    public int getQuestionNumber() { return this.questionNumber; }
    public HashMap<Integer, String> getQuestion() { return this.question; }
    public HashMap<Integer, ArrayList<String>> getOptions() { return this.options; }
    public HashMap<Integer, ArrayList<String>> getCorrectAnswer() { return this.correctAnswer; }
    public boolean isDirectory() { return file.isDirectory(); }
    public boolean isFile() { return file.isFile(); }
    public boolean isExists() { return file.exists(); }
}
