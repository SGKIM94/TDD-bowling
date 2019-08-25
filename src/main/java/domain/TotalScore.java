package domain;

public class TotalScore {
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
}
