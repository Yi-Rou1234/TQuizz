import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ITrivia trivia = new Trivia();
        Scanner scanner = new Scanner(System.in);
        trivia.loadQuestions("question.txt");
        trivia.run(scanner);
        scanner.close();
    }
}