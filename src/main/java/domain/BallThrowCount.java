package domain;

public class BallThrowCount {
    private static final int NEXT_STEP = 1;
    private static final int FIRST_BALL_THROWING = 1;
    private static final int SECOND_BALL_THROWING = 2;
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

    boolean isBigger(int maxBallThrowCount) {
        return this.ballThrowCount > maxBallThrowCount;
    }

    boolean isSmaller(int minimumBallThrowCount) {
        return this.ballThrowCount < minimumBallThrowCount;
    }

    int getNextBallCount() {
        return this.ballThrowCount + NEXT_STEP;
    }

    boolean isFirstBallThrowing() {
        return this.ballThrowCount == FIRST_BALL_THROWING;
    }

    boolean isSecondBallThrowing() {
        return this.ballThrowCount == SECOND_BALL_THROWING;
    }
}
