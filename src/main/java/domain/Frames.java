package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 2;
    private static final int SUBTRACT_ARRAY_LENGTH_WITH_INDEX = 1;
    private static final int INDEX_OF_SECOND_SCORE_DISPLAY = 2;
    public static final int STRIKE_SCORE_DISPLAY_SIZE = 1;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    Frames add(Frame frame) {
        this.frames.add(frame);
        return this;
    }

    public int getFramesSize() {
        return this.frames.size();
    }

    public Frame get(int index) {
        return this.frames.get(index);
    }

    Frame getBeforeFrame() {
        return this.frames.get(getFramesSize() - BEFORE_FRAME);
    }

    public ScoreDisplays makeScoreDisplayAndAddFrame(Score score, ScoreDisplays scoreDisplays) {
        BallThrowCount firstBallThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(firstBallThrowCount);

        if (isFramesSizeZero() && score.isStrike()) {
            scoreDisplays.add(getScoreDisplayAndAddFrames(score));

            return scoreDisplays;
        }

        if (isFramesSizeZero() && score.isSmallerThanStrike()) {
            add(new Frame(score, firstBallThrowCount, scoreDisplay));
            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }

        Frame currentFrame = get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX);
        BallThrowCount currentFrameBallCount = currentFrame.getBallThrowCount();

        if (currentFrameBallCount.isZeroBallThrowing()) {
            // 가져오는 이전 display 가 더해지고 나서가 아닌 그전의 형태임
            // 예를 들어 8| 가 나옴
            addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareScoreAndStrike(getBeforeFrame(), score);
        }

        if (currentFrameBallCount.isZeroBallThrowing() && score.isStrike()) {
            makeCurrentFrame(score, scoreDisplay, currentFrame);
            calculateTotalScore(currentFrame);
            addEmptyNextFrame();

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }

        if (currentFrameBallCount.isZeroBallThrowing() && score.isSmallerThanStrike()) {
            makeCurrentFrame(score, scoreDisplay, currentFrame);
            calculateTotalScore(currentFrame);

            scoreDisplays.add(currentFrame.getDisplayScore());

            return scoreDisplays;
        }

        currentFrame.sumTotalScore(score);
        currentFrame.setSecondFrameScore(score);

        String secondScoreDisplay = getScoreDisplayWhenSecondBallThrow(score, currentFrame);
        currentFrame.appendScoreDisplay(secondScoreDisplay);

        scoreDisplays.setBeforeDisplay(secondScoreDisplay);

        addEmptyNextFrame();

        return scoreDisplays;
    }

    void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareScoreAndStrike(Frame beforeFrame, Score score) {
        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike(beforeFrame);
        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare(beforeFrame, score);
    }

    private void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike(Frame beforeFrame) {
        String beforeScoreDisplay = beforeFrame.getDisplayScore();

        if (ScoreGroup.STRIKE
                .isEqualScoreDisplayWithInputScoreDisplay
                        (beforeScoreDisplay)) {
            beforeFrame.sumTotalScore(new Score(beforeFrame.getPrimaryTotalScore()));
        }
    }

    private void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare(Frame beforeFrame, Score score) {
        String beforeScoreDisplay = beforeFrame.getDisplayScore();

        if (isStrikeScoreDisplay(beforeScoreDisplay)){
            return;
        }

        if (ScoreGroup.SPARE
                .isEqualScoreDisplayWithInputScoreDisplay
                        (getSecondScoreDisplayWhenNotStrike(beforeScoreDisplay))) {
            beforeFrame.sumTotalScore(score);
        }
    }

    private boolean isStrikeScoreDisplay(String beforeScoreDisplay) {
        return beforeScoreDisplay.length() == STRIKE_SCORE_DISPLAY_SIZE;
    }

    String getSecondScoreDisplayWhenNotStrike(String beforeScoreDisplay) {
        return beforeScoreDisplay.substring(INDEX_OF_SECOND_SCORE_DISPLAY);
    }

    String getScoreDisplayWhenSecondBallThrow(Score score, Frame currentFrame) {
        if (currentFrame.getSumScores().isSmallerThanStrike()) {
            return score.getDisplayScore(new BallThrowCount(2));
        }

        if (currentFrame.getSumScores().isStrike()) {
            return currentFrame.getSumScores().getDisplayScore(new BallThrowCount(2));
        }

        throw new IllegalArgumentException();
    }

    private void makeCurrentFrame(Score score, String scoreDisplay, Frame currentFrame) {
        currentFrame.makeFrame(score, new BallThrowCount(1), scoreDisplay);
    }

    private void calculateTotalScore(Frame currentFrame) {
        TotalScore beforeTotalScore = getTotalScore(getBeforeFrame());
        getTotalScore(currentFrame).addTotalScoreWithBefore(beforeTotalScore);
    }

    private boolean isFramesSizeZero() {
        return this.frames.isEmpty();
    }

    private String getScoreDisplayAndAddFrames(Score score) {
        BallThrowCount ballThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(ballThrowCount);

        this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));
        addEmptyNextFrame();

        return scoreDisplay;
    }

    private void addEmptyNextFrame() {
        this.frames.add(new Frame());
    }

    public Frame getLastIndex() {
        return this.get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX);
    }

    TotalScore getLsatIndexTotalScore() {
        return getTotalScore(this.frames.get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX));
    }

    private TotalScore getTotalScore(Frame currentFrame) {
        return currentFrame.getTotalScore();
    }
}

