import java.io.*;
import java.util.*;

public class Trivial {
    private static List<Question> questions = new ArrayList<>();

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void loadQuestions(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    questions.add(new Question(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading questions file: " + e.getMessage());
        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error clearing console: " + e.getMessage());
        }
    }
}