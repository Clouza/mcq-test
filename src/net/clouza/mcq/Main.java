package net.clouza.mcq;

import net.clouza.mcq.helpers.Csv;
import net.clouza.mcq.throwable.FileOrDirectory;
import net.clouza.mcq.throwable.Magic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class Main {
    public static void main(String[] args) throws FileOrDirectory {
        Csv mcqSets = new Csv("./mcq-sets").scanCsvFileInDirectory().readFile();
        Scanner input = new Scanner(System.in);
        int i = 1;
        int score = 0;

        while(i <= mcqSets.getQuestion().size()) {
            System.out.println(i + " " + mcqSets.getQuestion().get(i));
            System.out.print("Your answer: ");
            String userAnswer = input.next().toLowerCase();

            // check answer
            System.out.println(mcqSets.getCorrectAnswer().get(i));
            if(mcqSets.getCorrectAnswer().get(i).toLowerCase().equals(userAnswer)) {
                score++;
            }
            i++;
            System.out.print("\n");
        }

        System.out.println("------------------------------");
        System.out.println("Your score: " + score);

    }
}