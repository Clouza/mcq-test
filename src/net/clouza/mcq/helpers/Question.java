package net.clouza.mcq.helpers;

import net.clouza.mcq.env.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
public class Question {
    private int key;
    private int score = 0;
    private HashMap<Integer, String> question;
    private HashMap<Integer, ArrayList<String>> options;
    private HashMap<Integer, ArrayList<String>> correctAnswer;
    private String name;
    private String userAnswer;
    private String regex;
    private ArrayList<String[]> option = new ArrayList<>();

    public Question(String name, int key, HashMap<Integer, String> question, HashMap<Integer, ArrayList<String>> options, HashMap<Integer, ArrayList<String>> correctAnswer) {
        this.key = key;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.name = name;
     }

    public void start() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < this.options.get(1).size(); i++) { // sample
            this.regex += Configuration.ALPHABET[i].toLowerCase();
        }
        Pattern pattern = Pattern.compile("[" + this.regex +"]");

        System.out.print("\n");

        this.question.forEach((key, value) -> {
            System.out.println(key + ". " + value);

            ArrayList<String> renameOptions = new ArrayList<>();
            for (int j = 0; j < this.options.get(key).size(); j++) {
                System.out.printf("%s. %s \n",Configuration.ALPHABET[j], this.options.get(key).get(j));

                for (int i = 0; i < this.correctAnswer.get(key).size(); i++) {
                    if(renameOptions.size() < this.correctAnswer.get(key).size()) {
                        int currentIndex = Arrays.asList(Configuration.ALPHABET).indexOf(this.correctAnswer.get(key).get(i));
                        renameOptions.add(this.correctAnswer.get(key).get(i) + ". " + this.options.get(key).get(currentIndex));
                    } else {
                        this.correctAnswer.put(key, renameOptions);
                    }
                }
            }

            for (int i = 0; i < this.correctAnswer.get(key).size(); i++) {
                this.option.add(this.correctAnswer.get(key).get(i).split("[.]"));
            }

            int remainingAnswer = 0, allCorrect = 0;
            while(remainingAnswer < this.correctAnswer.get(key).size()) {
                System.out.printf("Your answer (%d answer left): ", this.correctAnswer.get(key).size() - remainingAnswer);
                this.userAnswer = input.next().toLowerCase();
                Matcher matcher = pattern.matcher(this.userAnswer);

                if(matcher.find()) {
                    boolean isCorrect = false;
                    for (int i = 0; i < this.option.size(); i++) {
                        if(this.option.get(i)[0].toLowerCase().equals(this.userAnswer)) {
                            isCorrect = true;
                        }
                    }
                    if(isCorrect) {
                        System.out.println("You're correct!");
                        allCorrect++;
                    } else {
                        System.out.printf("Your answer is: %s \n", this.userAnswer);
                        System.out.printf("Wrong Answer. The Correct Answer is %s \n", this.correctAnswer.get(key).get(remainingAnswer));
                        allCorrect--;
                    }
                    remainingAnswer++;
                } else {
                    System.out.printf("%s not in option! \n\n", this.userAnswer);
                }
            }
            if(allCorrect == this.correctAnswer.get(key).size()) {
                this.score++;
            }
            this.option.clear();
            System.out.print("\n");
        });

        System.out.println("------------------------------");
        int getPercent = 100 / this.question.size() * this.score;
        System.out.printf("%s, you answered %d Question right, %d Question Wrong for a Total of %d Question, You scored %d%%", name, this.score, this.question.size() - this.score, this.question.size(), getPercent);

    }
}
