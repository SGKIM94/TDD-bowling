package domain;

public class Frame {
    private static final int MAX_BALL_THROW_COUNT = 2;
    private static final int MINIMUM_BALL_THROW_COUNT = 0;

    private Scores scores;
    private BallThrowCount ballThrowCount;
    private String scoreDisplay;

    Frame(Score score, BallThrowCount ballThrowCount, String scoreDisplay) {
        this.scores = new Scores(score);
        this.ballThrowCount = ballThrowCount;
        this.scoreDisplay = scoreDisplay;
    }

    Frame(Score score, BallThrowCount ballThrowCount) {
        this.scores = new Scores(score);
        this.ballThrowCount = ballThrowCount;
        this.scoreDisplay = score.getDisplayScore(ballThrowCount);
    }

    Frame(Scores scores, BallThrowCount ballThrowCount) {
        this.scores = scores;
        this.ballThrowCount = ballThrowCount;
    }

    //TODO : 넘어갈 수 있는지 없는지 체크해서 넘어 간다면 다음 프레임에 점수를 저장하고
    // 넘어갈 수 없다면 한 프레임에 저장한다
    public boolean canSkipThisFrame() {
        ScoreGroup scoreGroup;

        if (this.ballThrowCount.isFirstBallThrowing()) {
            scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.scores.getFirstScore());

            return !ScoreGroup.ELSE.equals(scoreGroup);
        }

        scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.scores.getSecondScore());

        return !ScoreGroup.ELSE.equals(scoreGroup);
    }

    void checkBallThrowCount() {
        if (this.ballThrowCount.isBigger(MAX_BALL_THROW_COUNT) || this.ballThrowCount.isSmaller(MINIMUM_BALL_THROW_COUNT)) {
            throw new IllegalArgumentException("볼 던진 횟수의 값이 올바르지 않습니다.");
        }
    }

    BallThrowCount getBallThrowCount() {
        return this.ballThrowCount;
    }

    int getNextBallCount() {
        return this.ballThrowCount.getNextBallCount();
    }

    Score getFirstScore() {
        return this.scores.getFirstScore();
    }

    boolean isSecondBallThrowing() {
        return this.ballThrowCount.isSecondBallThrowing();
    }

    public String getDisplayScore() {
        return this.scoreDisplay;
    }
}
