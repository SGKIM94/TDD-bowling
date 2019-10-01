package domain;

public enum BallThrowCountGroup {
    ONE_BALL_THROW_COUNT(1),
    MAX_BALL_THROW_COUNT(2),
    MINIMUM_BALL_THROW_COUNT(0),
    START_BALL_THROW_COUNT(1);

    private int count;

    BallThrowCountGroup(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }
}
