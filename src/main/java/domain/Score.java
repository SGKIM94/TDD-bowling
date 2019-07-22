package domain;

import java.util.Objects;

public class Score {
    public static final int MINIMUM_SCORE = 0;
    public static final int MAX_SCORE = 10;
    public static final int STRIKE_SCORE = 10;
    public static final int ZERO_SCORE = 0;
    private int score;

    public Score(int score) {
        this.score = score;
        inputScore();
    }

    public Score(String score) {
        this(Integer.parseInt(score));
    }

    public void inputScore() {
        checkScoreIsNull();
        checkScore();
    }

    private void checkScoreIsNull() {
        if (String.valueOf(this.score).isEmpty()) {
            throw new IllegalArgumentException("점수를 입력해주세요.");
        }
    }

    public void checkScore() {
        checkScoreIsWrong();
    }

    private void checkScoreIsWrong() {
        if (this.score < MINIMUM_SCORE || this.score > MAX_SCORE) {
            throw new IllegalArgumentException("잘못된 점수를 입력하셨습니다.");
        }
    }

    public boolean isStrike() {
        return this.score == STRIKE_SCORE;
    }

    public String getDisplayScore(BallThrowCount ballThrowCount) {
        ScoreGroup scoreGroup = ScoreGroup.findByScore(ballThrowCount, this);

        if (isScoreGroupElse(scoreGroup)) {
            return this.score + scoreGroup.getDisplay();
        }

        return scoreGroup.getDisplay();
    }

    private boolean isScoreGroupElse(ScoreGroup scoreGroup) {
        return scoreGroup.equals(ScoreGroup.ELSE);
    }

    public Score sumScores(Score anotherScore) {
        return new Score(this.score + anotherScore.score);
    }

    public int getScore() {
        return this.score;
    }

    public boolean isSmallerThanStrike() {
        return this.score < STRIKE_SCORE;
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

    public boolean isZero() {
        return this.score == ZERO_SCORE;
    }
}
