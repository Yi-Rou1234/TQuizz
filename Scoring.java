public class Scoring implements IScoring {
    @Override
    public double calculatePercentage(int correctAnswers, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return ((double) correctAnswers / totalQuestions) * 100;
    }
}