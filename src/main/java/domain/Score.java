package domain;

import java.util.Objects;

public class Score {
    private static final int MINIMUM_SCORE = 0;
    private static final int MAX_SCORE = 10;
    private static final int STRIKE_SCORE = 10;

    private int score;

    public Score(int score) {
        this.score = score;
        inputScore();
    }

    public Score(String score) {
        this(Integer.parseInt(score));
    }

    private void inputScore() {
        checkScoreIsNull(this.score);
        checkScore();
    }

    void checkScoreIsNull(int score) {
        if (String.valueOf(score).isEmpty()) {
            throw new IllegalArgumentException("점수를 입력해주세요.");
        }
    }

    private void checkScore() {
        checkScoreIsWrong(this.score);
    }

    void checkScoreIsWrong(int score) {
        if (isScoreSmallerThanMinimumScore(score) || isScoreBiggerThanMaxScore(score)) {
            throw new IllegalArgumentException("잘못된 점수를 입력하셨습니다.");
        }
    }

    private boolean isScoreBiggerThanMaxScore(int score) {
        return score > MAX_SCORE;
    }

    private boolean isScoreSmallerThanMinimumScore(int score) {
        return score < MINIMUM_SCORE;
    }

    boolean isStrike() {
        return this.score == STRIKE_SCORE;
    }

    String getDisplayScore(BallThrowCount ballThrowCount) {
        ScoreGroup scoreGroup = ScoreGroup.findByScore(ballThrowCount, this);

        if (isScoreGroupElse(scoreGroup)) {
            return this.score + scoreGroup.getDisplay();
        }

        return scoreGroup.getDisplay();
    }

    private boolean isScoreGroupElse(ScoreGroup scoreGroup) {
        return scoreGroup.equals(ScoreGroup.ELSE);
    }

    Score sumScores(Score anotherScore) {
        return new Score(this.score + anotherScore.score);
    }

    public int getScore() {
        return this.score;
    }

    boolean isSmallerThanStrike() {
        return this.score < STRIKE_SCORE;
    }

    int toStringSize() {
        return this.toString().length();
    }

    @Override
    public boolean equals(Object score) {
        if (this == score) {
            return true;
        }

        if (score == null) {
            return false;
        }

        return this.score == (int)score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return String.valueOf(this.score);
    }
}
