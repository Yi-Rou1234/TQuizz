public interface IScoring {
    double calculatePercentage(int correctAnswers, int totalQuestions);
    void saveScore(String username, String question, String userAnswer, String correctAnswer, String result);
}