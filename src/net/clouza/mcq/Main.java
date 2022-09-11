package net.clouza.mcq;

import net.clouza.mcq.helpers.Csv;
import net.clouza.mcq.helpers.Question;
import net.clouza.mcq.throwable.FileOrDirectory;

import java.util.Scanner;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class Main {
    public static void main(String[] args) throws FileOrDirectory {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Csv mcqSets = new Csv("./mcq-sets").scanCsvFileInDirectory().readFile();
        Question question = new Question(name, mcqSets.getQuestionNumber(), mcqSets.getQuestion(), mcqSets.getOptions(), mcqSets.getCorrectAnswer());
        question.start();
    }
}