package domain;

import java.util.ArrayList;
import java.util.List;

import static domain.FrameIndexGroup.*;

public class Frames {
    private static final int INDEX_OF_SECOND_SCORE_DISPLAY = 2;
    private static final int STRIKE_SCORE_DISPLAY_SIZE = 1;
    private static final int FIRST_FRAME_SIZE = 1;
    private static final int MORE_THAN_TWO_FRAMES = 2;
    private static final int MORE_THAN_THREE_FRAMES = 3;


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

    public ScoreDisplays makeScoreDisplayAndAddFrame(Score score, ScoreDisplays scoreDisplays) {
        String scoreDisplay = score.getDisplayScore(new BallThrowCount(1));

        if (isFramesSizeZero() && score.isStrike()) {
            addFrameSecondBallThrowCountAndScoreDisplay(score, scoreDisplay, scoreDisplays);
            addEmptyNextFrame();

            return scoreDisplays;
        }

        if (isFramesSizeZero() && score.isSmallerThanStrike()) {
            addFrameSecondBallThrowCountAndScoreDisplay(score, scoreDisplay, scoreDisplays);

            return scoreDisplays;
        }

        Frame currentFrame = get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX.getIndex());
        BallThrowCount currentFrameBallCount = currentFrame.getBallThrowCount();

        if (currentFrameBallCount.isFirstBallThrowing() && score.isStrike()) {
            makeCurrentFrame(score, scoreDisplay, currentFrame);
            calculateTotalScore(currentFrame);
            addEmptyNextFrame();
            addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike(getBeforeFrame(), currentFrame);

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }

        if (currentFrameBallCount.isFirstBallThrowing() && score.isSmallerThanStrike()) {
            makeCurrentFrame(score, scoreDisplay, currentFrame);
            calculateTotalScore(currentFrame);

            scoreDisplays.add(currentFrame.getDisplayScore());

            return scoreDisplays;
        }

        currentFrame.setSecondFrameScore(score);
        currentFrame.sumTotalScore(score);

        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike(getBeforeFrame(), currentFrame);

        String secondScoreDisplay = getScoreDisplayWhenSecondBallThrow(score, currentFrame);
        currentFrame.appendScoreDisplay(secondScoreDisplay);
        scoreDisplays.setBeforeDisplay(secondScoreDisplay);

        addEmptyNextFrame();

        return scoreDisplays;
    }

    private boolean isFirstFrame() {
        return frames.size() == FIRST_FRAME_SIZE;
    }

    private void addFrameSecondBallThrowCountAndScoreDisplay(Score score, String scoreDisplay, ScoreDisplays scoreDisplays) {
        add(new Frame(score, new BallThrowCount(2), scoreDisplay));
        scoreDisplays.add(scoreDisplay);
    }

    private void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike(Frame beforeFrame, Frame currentFrame) {
        if (isFirstFrame()) {
            return;
        }

        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike();
        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare(beforeFrame, currentFrame);
    }

    void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike() {
        if (isNotEmpty(getBeforeFrameDisplayScore())) {
            addBeforeTotalScoreThatCurrentScoreWhenBeforeDisplayIsStrike(getLastFrameSumScores());
        }

        Score beforeFrameSumScores = getBeforeFrameSumScores();

        if (isFrameSizeMoreThanTwo()) {
            addSecondToLastTotalScoreThatCurrentScoreWhenSecondToLastDisplayIsStrike(beforeFrameSumScores);
        }

        if (isFrameSizeMoreThanThree()) {
            addThirdToLastTotalScoreThatCurrentScoreWhenThirdToLastDisplayIsStrike(beforeFrameSumScores);
        }
    }

    private Score getBeforeFrameSumScores() {
        return getBeforeFrame().getSumScores();
    }

    private String getBeforeFrameDisplayScore() {
        return getBeforeFrame().getDisplayScore();
    }

    private Score getLastFrameSumScores() {
        return getLastFrame().getSumScores();
    }

    private boolean isFrameSizeMoreThanThree() {
        return getFramesSize() > MORE_THAN_THREE_FRAMES;
    }

    private boolean isFrameSizeMoreThanTwo() {
        return getFramesSize() > MORE_THAN_TWO_FRAMES;
    }

    private void addBeforeTotalScoreThatCurrentScoreWhenBeforeDisplayIsStrike(Score sumScore) {
        if (isEqualDisplayStrikeWithBeforeDisplay()) {
            addBeforeAndCurrentFrameTotalScore(sumScore);
        }
    }

    private void addSecondToLastTotalScoreThatCurrentScoreWhenSecondToLastDisplayIsStrike(Score sumScore) {
        if (isEqualDisplayStrikeWithBeforeDisplay()
                && isEqualDisplayStrikeWithSecondDisplay()) {
            addBeforeAndCurrentFrameTotalScore(sumScore);
        }
    }

    private void addThirdToLastTotalScoreThatCurrentScoreWhenThirdToLastDisplayIsStrike(Score sumScore) {
        if (isEqualDisplayStrikeWithBeforeDisplay()
                && isEqualDisplayStrikeWithSecondDisplay()
                && isEqualDisplayStrikeWithThirdDisplay()) {
            addBeforeAndCurrentFrameTotalScore(sumScore);
        }
    }

    private void addBeforeAndCurrentFrameTotalScore(Score sumScore) {
        Frame firstToLastFrame = getBeforeFrame();
        Frame currentFrame = getLastFrame();

        firstToLastFrame.sumTotalScore(sumScore);
        currentFrame.sumTotalScore(sumScore);
    }

    private boolean isEqualDisplayStrikeWithBeforeDisplay() {
        String BeforeDisplay = getBeforeFrameDisplayScore();

        return isEqualDisplayStrikeWithInputDisplay(BeforeDisplay);
    }

    private boolean isEqualDisplayStrikeWithSecondDisplay() {
        String secondToLastDisplay = getSecondToLastFrame().getDisplayScore();

        return isEqualDisplayStrikeWithInputDisplay(secondToLastDisplay);
    }

    private boolean isEqualDisplayStrikeWithThirdDisplay() {
        String ThirdToLastDisplay = getThirdToLastFrame().getDisplayScore();

        return isEqualDisplayStrikeWithInputDisplay(ThirdToLastDisplay);
    }

    private boolean isEqualDisplayStrikeWithInputDisplay(String scoreDisplay) {
        return ScoreGroup.STRIKE
                .isEqualScoreDisplayWithInputScoreDisplay
                        (scoreDisplay);
    }

    void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare(Frame beforeFrame, Frame currentFrame) {
        String beforeScoreDisplay = beforeFrame.getDisplayScore();

        if (isStrikeScoreDisplay(beforeScoreDisplay)){
            return;
        }

        Score firstScore = currentFrame.getFirstScore();

        if (ScoreGroup.SPARE
                .isEqualScoreDisplayWithInputScoreDisplay
                        (getSecondScoreDisplayWhenNotStrike(beforeScoreDisplay))) {
            beforeFrame.sumTotalScore(firstScore);
            currentFrame.sumTotalScore(firstScore);
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
        currentFrame.makeFrame(score, new BallThrowCount(2), scoreDisplay);
    }

    private void calculateTotalScore(Frame currentFrame) {
        TotalScore beforeTotalScore = getTotalScore(getBeforeFrame());
        getTotalScore(currentFrame).addTotalScoreWithBefore(beforeTotalScore);
    }

    private boolean isFramesSizeZero() {
        return this.frames.isEmpty();
    }

    private void addEmptyNextFrame() {
        this.frames.add(new Frame());
    }

    public Frame getLastFrame() {
        return get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX.getIndex());
    }

    Frame getBeforeFrame() {
        return this.frames.get(getFramesSize() - BEFORE_FRAME_INDEX.getIndex());
    }

    private Frame getSecondToLastFrame() {
        return this.frames.get(getFramesSize() - BEFORE_SECOND_TO_LAST_INDEX.getIndex());
    }

    private Frame getThirdToLastFrame() {
        return this.frames.get(getFramesSize() - BEFORE_THIRD_TO_LAST_INDEX.getIndex());
    }

    TotalScore getLsatIndexTotalScore() {
        return getTotalScore(this.frames.get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX.getIndex()));
    }

    private TotalScore getTotalScore(Frame currentFrame) {
        return currentFrame.getTotalScore();
    }

    private boolean isNotEmpty(String value) {
        return !value.isEmpty();
    }
}