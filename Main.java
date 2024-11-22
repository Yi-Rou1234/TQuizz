import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ITrivia trivial = new Trivia();
        trivial.loadQuestions("question.txt");
        Scanner scanner = new Scanner(System.in);
        trivial.processQuestions(scanner);
        scanner.close();
    }
}