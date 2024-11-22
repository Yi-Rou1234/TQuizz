import java.io.IOException;
import java.util.Scanner;

public interface ITrivia {
    void loadQuestions(String filename) throws IOException;
    void run(Scanner scanner);
}