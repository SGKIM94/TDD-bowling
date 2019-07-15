package domain;

public class BallThrowCount {
    public static final int NEXT_STEP = 1;
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
}
