package domain;

public class BallThrowCount {
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

        if (ballThrowCount instanceof  Integer) {
            return this.ballThrowCount == (int)ballThrowCount;
        }

        BallThrowCount otherBallThrowCount = (BallThrowCount)ballThrowCount;
        return this.ballThrowCount == (otherBallThrowCount.getThrowCount());
    }

    int getThrowCount() {
        return this.ballThrowCount;
    }

    boolean isBigger(int maxBallThrowCount) {
        return this.ballThrowCount > maxBallThrowCount;
    }

    boolean isSmaller(int minimumBallThrowCount) {
        return this.ballThrowCount < minimumBallThrowCount;
    }

    boolean isFirstBallThrowing() {
        return this.ballThrowCount == FIRST_BALL_THROWING;
    }

    boolean isSecondBallThrowing() {
        return this.ballThrowCount == SECOND_BALL_THROWING;
    }

    boolean isZeroBallThrowing() {
        return this.ballThrowCount == 0;
    }
}
