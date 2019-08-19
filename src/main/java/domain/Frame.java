package domain;

 public class Frame {
    private static final int MAX_BALL_THROW_COUNT = 2;
    private static final int MINIMUM_BALL_THROW_COUNT = 0;
    private static final int START_BALL_THROW_COUNT = 0;

    private Scores scores;
    private BallThrowCount ballThrowCount;
    private String scoreDisplay;
    private int totalScore;

    Frame() {
        this.scores = new Scores();
        this.ballThrowCount = new BallThrowCount(START_BALL_THROW_COUNT);
        this.scoreDisplay = "";
    }

    Frame(Score score, BallThrowCount ballThrowCount, String scoreDisplay) {
        makeFrame(score, ballThrowCount, scoreDisplay);
        this.totalScore += score.getScore();
    }

     void makeFrame(Score score, BallThrowCount ballThrowCount, String scoreDisplay) {
         this.scores = new Scores(score);
         this.scoreDisplay = scoreDisplay;
         this.ballThrowCount = ballThrowCount;
     }

    Frame(Score score, BallThrowCount ballThrowCount) {
        this.scores = new Scores(score);
        this.ballThrowCount = ballThrowCount;
        this.scoreDisplay = score.getDisplayScore(ballThrowCount);
    }

    boolean canSkipThisFrame() {
        ScoreGroup scoreGroup;

        if (this.ballThrowCount.isFirstBallThrowing()) {
            scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.scores.getFirstScore());

            return !ScoreGroup.ELSE.equals(scoreGroup);
        }

        scoreGroup = ScoreGroup.findByScore(this.ballThrowCount, this.scores.getSecondScore());

        return !ScoreGroup.ELSE.equals(scoreGroup);
    }

    void checkBallThrowCount() {
        if (this.ballThrowCount.isBigger(MAX_BALL_THROW_COUNT)
                || this.ballThrowCount.isSmaller(MINIMUM_BALL_THROW_COUNT)) {
            throw new IllegalArgumentException("볼 던진 횟수의 값이 올바르지 않습니다.");
        }
    }

    BallThrowCount getBallThrowCount() {
        return this.ballThrowCount;
    }

    Score getFirstScore() {
        return this.scores.getFirstScore();
    }

    boolean isSecondBallThrowing() {
        return this.ballThrowCount.isSecondBallThrowing();
    }

    String getDisplayScore() {
        return this.scoreDisplay;
    }

    public Score getSumScore() {
        return this.scores.getSumScores();
    }

    public int getScoreSize() {
        return this.scores.getScoresSize();
    }

     public boolean isEmptyFrame() {
        return this.scoreDisplay.isEmpty();
     }

     public int getTotalScore() {
        return this.totalScore;
     }
 }
