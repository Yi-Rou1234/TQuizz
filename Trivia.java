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
                if (parts.length == 6) {
                    questions.add(new IQuestion(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                } else {
                    throw new IOException("Invalid question format: " + line);
                }
            }
        }
    }

    @Override
    public void run(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter the category you are looking for (anime, food, music): ");
        String selectedCategory = scanner.nextLine().trim().toLowerCase();

        List<IQuestion> filteredQuestions = new ArrayList<>();
        for (IQuestion question : questions) {
            if (question.getCategory().equalsIgnoreCase(selectedCategory)) {
                filteredQuestions.add(question);
            }
        }

        if (filteredQuestions.isEmpty()) {
            System.out.println("No questions found for the selected category.");
            return;
        }

        Collections.shuffle(filteredQuestions);
        
        int correctAnswers = 0;
        int totalQuestions = filteredQuestions.size();

        IHelper helper = new Helper();
        IScoring scoring = new Scoring();

        for (IQuestion question : filteredQuestions) {
            helper.clearConsole();
            System.out.println(question.getQuestionText());
            System.out.println("a. " + question.getOptionA());
            System.out.println("b. " + question.getOptionB());
            System.out.println("c. " + question.getOptionC());

            String answer = helper.getValidInput(scanner, question);

            if (answer.equals("exit")) {
                break;
            }

            boolean isCorrect = question.isCorrect(answer);
            if (isCorrect) {
                System.out.println("Correct!");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer was " + question.getCorrectAnswer() + ".");
            }

            scoring.saveScore(username, question.getQuestionText(), answer, question.getCorrectAnswer(), isCorrect ? "Correct" : "Incorrect");

            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }

        double percentage = scoring.calculatePercentage(correctAnswers, totalQuestions);
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentage + "%");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(username + ".txt", true))) {
            writer.write("Total correct answers: " + correctAnswers);
            writer.newLine();
            writer.write("Total questions: " + totalQuestions);
            writer.newLine();
            writer.write("Score: " + percentage + "%");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}