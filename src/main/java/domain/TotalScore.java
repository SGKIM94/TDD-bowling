package domain;

public class TotalScore {
    private static final int ZERO_SCORE = 0;
    private int totalScore;

    TotalScore(Score score) {
        this.totalScore = score.getScore();
    }

    TotalScore addInputScore(Score score) {
        this.totalScore += score.getScore();

        return this;
    }

    int getTotalScore() {
        return this.totalScore;
    }

    void addTotalScoreWithBefore(TotalScore beforeTotal) {
        this.totalScore = beforeTotal.getTotalScore() + getTotalScore();
    }

    public int toStringSize() {
        return toString().length();
    }

    @Override
    public String toString() {
        return String.valueOf(this.totalScore);
    }

    boolean isZeroTotalScore() {
        return this.totalScore == ZERO_SCORE;
    }
}
