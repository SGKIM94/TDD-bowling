package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 2;
    private static final int SUBTRACT_ARRAY_LENGTH_WITH_INDEX = 1;
    private static final int INDEX_OF_SECOND_SCORE_DISPLAY = 2;
    private static final int STRIKE_SCORE_DISPLAY_SIZE = 1;
    private static final int FIRST_FRAME_SIZE = 1;
    private static final int MORE_THAN_TWO_FRAMES = 2;
    private static final int MORE_THAN_THREE_FRAMES = 3;
    public static final int BEFORE_SECOND_TO_LAST_INDEX = 3;
    public static final int BEFORE_THIRD_TO_LAST_INDEX = 4;

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

        Frame currentFrame = get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX);
        BallThrowCount currentFrameBallCount = currentFrame.getBallThrowCount();

        if (currentFrameBallCount.isFirstBallThrowing() && score.isStrike()) {
            makeCurrentFrame(score, scoreDisplay, currentFrame);
            calculateTotalScore(currentFrame);
            addEmptyNextFrame();
            addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike
                    (getBeforeFrame(), currentFrame);

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

        if (isNotFirstFrame()) {
            addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike
                    (getBeforeFrame(), currentFrame);
        }

        String secondScoreDisplay = getScoreDisplayWhenSecondBallThrow(score, currentFrame);
        currentFrame.appendScoreDisplay(secondScoreDisplay);
        scoreDisplays.setBeforeDisplay(secondScoreDisplay);

        addEmptyNextFrame();

        return scoreDisplays;
    }

    private boolean isNotFirstFrame() {
        return frames.size() > FIRST_FRAME_SIZE;
    }

    private void addFrameSecondBallThrowCountAndScoreDisplay(Score score, String scoreDisplay, ScoreDisplays scoreDisplays) {
        add(new Frame(score, new BallThrowCount(2), scoreDisplay));
        scoreDisplays.add(scoreDisplay);
    }

    private void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpareAndStrike(Frame beforeFrame, Frame currentFrame) {
        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike();
        addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsSpare(beforeFrame, currentFrame);
    }

    void addBeforeTotalScoreThatCurrentScoreWhenBeforeScoreDisplayIsStrike() {
        Frame currentFrame = getLastFrame();
        Frame firstToLastFrame = getBeforeFrame();

        String beforeScoreDisplay = firstToLastFrame.getDisplayScore();
        Score sumScore = currentFrame.getSumScores();

        if (isNotEmpty(beforeScoreDisplay)
                &&isEqualDisplayStrikeWithInputDisplay(beforeScoreDisplay)) {
            firstToLastFrame.sumTotalScore(sumScore);
            currentFrame.sumTotalScore(sumScore);
        }

        Score firstToLastFrameSumScores = firstToLastFrame.getSumScores();

        if (getFramesSize() > MORE_THAN_TWO_FRAMES) {
            addSecondToLastTotalScoreThatCurrentScoreWhenSecondToLastDisplayIsStrike(firstToLastFrameSumScores);
        }

        if (getFramesSize() > MORE_THAN_THREE_FRAMES) {
            addThirdToLastTotalScoreThatCurrentScoreWhenThirdToLastDisplayIsStrike(firstToLastFrameSumScores);
        }
    }

    private void addSecondToLastTotalScoreThatCurrentScoreWhenSecondToLastDisplayIsStrike(Score sumScore) {
        Frame firstToLastFrame = getBeforeFrame();
        Frame secondToLastFrame = getSecondToLastFrame();
        Frame currentFrame = getLastFrame();

        String secondToLastDisplay = secondToLastFrame.getDisplayScore();
        String beforeScoreDisplay = firstToLastFrame.getDisplayScore();

        if (isNotEmpty(beforeScoreDisplay)
                && isEqualDisplayStrikeWithInputDisplay(beforeScoreDisplay)
                && isEqualDisplayStrikeWithInputDisplay(secondToLastDisplay)
                ) {
            firstToLastFrame.sumTotalScore(sumScore);
            currentFrame.sumTotalScore(sumScore);
        }
    }

    private void addThirdToLastTotalScoreThatCurrentScoreWhenThirdToLastDisplayIsStrike(Score sumScore) {
        Frame firstToLastFrame = getBeforeFrame();
        Frame secondToLastFrame = getSecondToLastFrame();
        Frame currentFrame = getLastFrame();
        Frame thirdToLastFrame = getThirdToLastFrame();

        String thirdToLastDisplay = thirdToLastFrame.getDisplayScore();
        String secondToLastDisplay = secondToLastFrame.getDisplayScore();
        String beforeScoreDisplay = firstToLastFrame.getDisplayScore();

        if (isEqualDisplayStrikeWithInputDisplay(thirdToLastDisplay)
                && isEqualDisplayStrikeWithInputDisplay(beforeScoreDisplay)
                && isEqualDisplayStrikeWithInputDisplay(secondToLastDisplay)
                && isEqualDisplayStrikeWithInputDisplay(thirdToLastDisplay)) {
            firstToLastFrame.sumTotalScore(sumScore);
            currentFrame.sumTotalScore(sumScore);
        }
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
        return get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX);
    }

    Frame getBeforeFrame() {
        return this.frames.get(getFramesSize() - BEFORE_FRAME);
    }

    Frame getSecondToLastFrame() {
        return this.frames.get(getFramesSize() - BEFORE_SECOND_TO_LAST_INDEX);
    }

    Frame getThirdToLastFrame() {
        return this.frames.get(getFramesSize() - BEFORE_THIRD_TO_LAST_INDEX);
    }

    TotalScore getLsatIndexTotalScore() {
        return getTotalScore(this.frames.get(getFramesSize() - SUBTRACT_ARRAY_LENGTH_WITH_INDEX));
    }

    private TotalScore getTotalScore(Frame currentFrame) {
        return currentFrame.getTotalScore();
    }

    private boolean isNotEmpty(String value) {
        return !value.isEmpty();
    }
}

