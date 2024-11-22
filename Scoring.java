import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scoring implements IScoring {
    @Override
    public double calculatePercentage(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return ((double) correctAnswers / totalQuestions) * 100;
    }

    @Override
    public void saveScore(String username, String question, String userAnswer, String correctAnswer, String result) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(username + ".txt", false))) {
            writer.write("Question: " + question);
            writer.newLine();
            writer.write("Your answer: " + userAnswer);
            writer.newLine();
            writer.write("Correct answer: " + correctAnswer);
            writer.newLine();
            writer.write("Result: " + result);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}