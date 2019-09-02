package domain;

public class Frame {
    private static final int MAX_BALL_THROW_COUNT = 2;
    private static final int MINIMUM_BALL_THROW_COUNT = 0;
    private static final int START_BALL_THROW_COUNT = 0;

    private Scores scores;
    private BallThrowCount ballThrowCount;
    private String scoreDisplay;
    private TotalScore totalScore;

    Frame() {
        this.scores = new Scores();
        this.ballThrowCount = new BallThrowCount(START_BALL_THROW_COUNT);
        this.scoreDisplay = "";
        this.totalScore = new TotalScore(new Score(0));
    }

    Frame(Score score, BallThrowCount ballThrowCount, String scoreDisplay) {
        makeFrame(score, ballThrowCount, scoreDisplay);
    }

     void makeFrame(Score score, BallThrowCount ballThrowCount, String scoreDisplay) {
         this.scores = new Scores(score);
         this.scoreDisplay = scoreDisplay;
         this.ballThrowCount = ballThrowCount;
         this.totalScore = initializeTotalScore(score);
     }

    Frame(Score score, BallThrowCount ballThrowCount) {
        this.scores = new Scores(score);
        this.ballThrowCount = ballThrowCount;
        this.scoreDisplay = score.getDisplayScore(ballThrowCount);
        this.totalScore = initializeTotalScore(score);
    }

    private TotalScore initializeTotalScore(Score score) {
        if (this.totalScore == null || this.totalScore.isZeroTotalScore()) {
            return new TotalScore(this.scores.getSumScores());
        }

        return new TotalScore(this.scores.getSumScores()).addInputScore(score);
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

    boolean isSecondBallThrowing() {
        return this.ballThrowCount.isSecondBallThrowing();
    }

    String getDisplayScore() {
        return this.scoreDisplay;
    }

    public int getScoreSize() {
        return this.scores.getScoresSize();
    }

     public boolean isEmptyFrame() {
        return this.scoreDisplay.isEmpty();
     }

     public TotalScore getTotalScore() {
        return this.totalScore;
     }

     TotalScore sumTotalScore(Score score) {
        this.totalScore.addInputScore(score);

        return this.totalScore;
     }

     Score getSumScores() {
        return this.scores.getSumScores();
     }

     void setSecondFrameScore(Score score) {
         this.scores.add(score);
         this.ballThrowCount = new BallThrowCount(2);
     }
 }
