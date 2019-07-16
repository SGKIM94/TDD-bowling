package domain;

public class BallThrowCount {
    public static final int NEXT_STEP = 1;
    public static final int FIRST_BALL_THROWING = 1;
    public static final int SECOND_BALL_THROWING = 2;
    private int ballThrowCount;

    public BallThrowCount(int ballThrowCount) {
        this.ballThrowCount = ballThrowCount;
    }

    @Override
    public boolean equals(Object ballThrowCount) {
        if (this == ballThrowCount) {
            return true;
        }

        if (ballThrowCount == null) {
            return false;
        }

        return this.ballThrowCount == (int)ballThrowCount;
    }

    public boolean isBigger(int maxBallThrowCount) {
        return this.ballThrowCount > maxBallThrowCount;
    }

    public boolean isSmaller(int minimumBallThrowCount) {
        return this.ballThrowCount < minimumBallThrowCount;
    }

    public int getNextBallCount() {
        return this.ballThrowCount + NEXT_STEP;
    }

    public boolean isFirstBallThrowing() {
        return this.ballThrowCount == FIRST_BALL_THROWING;
    }

    public boolean isSecondBallThrowing() {
        return this.ballThrowCount == SECOND_BALL_THROWING;
    }
}
