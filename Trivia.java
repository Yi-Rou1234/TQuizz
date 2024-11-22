import java.io.*;
import java.util.*;

public class Trivia implements ITrivia {
    private static List<IQuestion> questions = new ArrayList<>();

    public static List<IQuestion> getQuestions() {
        return questions;
    }

    @Override
    public void loadQuestions(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    questions.add(new IQuestion(parts[0], parts[1], parts[2], parts[3], parts[4]));
                } else {
                    throw new IOException("Invalid question format: " + line);
                }
            }
        }
    }

    @Override
    public void processQuestions(Scanner scanner) {
        int correctAnswers = 0;
        int totalQuestions = questions.size();

        Helper helper = new Helper();
        Scoring scoring = new Scoring();

        for (IQuestion question : questions) {
            helper.clearConsole();
            System.out.println(question.getQuestionText());
            System.out.println("a. " + question.getOptionA());
            System.out.println("b. " + question.getOptionB());
            System.out.println("c. " + question.getOptionC());

            String answer = helper.getValidInput(scanner, question);

            if (answer.equals("exit")) {
                break;
            }

            if (question.isCorrect(answer)) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer was " + question.getCorrectAnswer() + ".");
            }
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }

        double percentage = scoring.calculatePercentage(correctAnswers, totalQuestions);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentage + "%");
    }
}