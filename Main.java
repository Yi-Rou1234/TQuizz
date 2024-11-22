import java.util.List;
import java.util.Scanner;

public class Main {
    private static int correctAnswers = 0;
    private static int totalQuestions = 10;

    public static void main(String[] args) {
        Trivial.loadQuestions("question.txt");
        List<Question> questions = Trivial.getQuestions();
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            Trivial.clearConsole();
            System.out.println(question.getQuestionText());
            System.out.println("a. " + question.getOptionA());
            System.out.println("b. " + question.getOptionB());
            System.out.println("c. " + question.getOptionC());
            System.out.print("Your answer or type 'exit' to quit: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("exit")) {
                break;
            }

            while (!answer.equalsIgnoreCase("a") && !answer.equalsIgnoreCase("b") && !answer.equalsIgnoreCase("c")) {
                System.out.println("Invalid input. Please enter 'a', 'b', 'c', or 'exit'.");
                System.out.print("Your answer or type 'exit' to quit: ");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            if (answer.equalsIgnoreCase("exit")) {
                break;
            }

            if (question.isCorrect(answer)) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer was " + question.getCorrectAnswer() + ".");
            }
            // totalQuestions++;
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }

        double percentage = ((double) correctAnswers / totalQuestions) * 100;
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentage + "%");
    }
}