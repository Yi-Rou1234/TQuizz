import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ITrivia trivia = new Trivia();
        IHelper helper = new Helper();
        Scanner scanner = new Scanner(System.in);
        String category = helper.getValidCategory(scanner, "Choose a category: a. anime, b. music, c. food: ");

        trivia.loadQuestions(category);
        trivia.run(scanner);
        scanner.close();
    }
}