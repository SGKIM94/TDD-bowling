package domain;

public class Frame {
    public static final int MAX_BALL_THROW_COUNT = 2;
    public static final int MINIMUM_BALL_THROW_COUNT = 0;
    public static final int ADD_CURRENT_BALL_THROWING = 1;

    private Score score;
    private int ballThrowCount;

    public Frame(Score score, int ballThrowCount) {
        this.score = score;
        this.ballThrowCount = ballThrowCount;
    }

    public boolean canSkipThisFrame() {
        ScoreGroup scoreGroup = ScoreGroup.findByScore(this.ballThrowCount + ADD_CURRENT_BALL_THROWING, this.score);

        return !ScoreGroup.ELSE.equals(scoreGroup);
    }

    public void checkBallThrowCount() {
        if (this.ballThrowCount > MAX_BALL_THROW_COUNT || this.ballThrowCount < MINIMUM_BALL_THROW_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    public int getBallThrowCount() {
        return this.ballThrowCount;
    }
}
