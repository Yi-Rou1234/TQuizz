import java.io.IOException;
import java.util.Scanner;

public interface ITrivia {
    void loadQuestions(String filename) throws IOException;
    void processQuestions(Scanner scanner);
}