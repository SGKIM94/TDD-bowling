package domain;

public class Frame {
    public static final int MAX_BALL_THROW_COUNT = 2;
    public static final int MINIMUM_BALL_THROW_COUNT = 0;

    private Score score;
    private BallThrowCount ballThrowCount;

    public Frame(Score score, BallThrowCount ballThrowCount) {
        this.score = score;
        this.ballThrowCount = ballThrowCount;
    }

    public boolean canSkipThisFrame() {
        ScoreGroup scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.score);

        return !ScoreGroup.ELSE.equals(scoreGroup);
    }

    public void checkBallThrowCount() {
        if (this.ballThrowCount.isBigger(MAX_BALL_THROW_COUNT) || this.ballThrowCount.isSmaller(MINIMUM_BALL_THROW_COUNT)) {
            throw new IllegalArgumentException("볼 던진 횟수의 값이 올바르지 않습니다.");
        }
    }

    public BallThrowCount getBallThrowCount() {
        return this.ballThrowCount;
    }
}
