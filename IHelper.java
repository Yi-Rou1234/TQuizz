import java.util.Scanner;

public interface IHelper {
    void clearConsole();
    String getValidInput(Scanner scanner, IQuestion question);
    String getValidCategory(Scanner scanner, String prompt);
}