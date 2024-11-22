import java.util.Scanner;

public class Helper implements IHelper {
    @Override
    public void clearConsole() {
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

    @Override
    public String getValidInput(Scanner scanner, IQuestion question) {
        String input;
        while (true) {
            System.out.print("Your answer or type 'exit' to quit: ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("a") || input.equals("b") || input.equals("c") || input.equals("exit")) {
                return input;
            } else {
                clearConsole();
                System.out.println("Invalid input. Please enter only 'a', 'b', 'c', or 'exit'.");
                System.out.println(question.getQuestionText());
                System.out.println("a. " + question.getOptionA());
                System.out.println("b. " + question.getOptionB());
                System.out.println("c. " + question.getOptionC());
            }
        }
    }
}