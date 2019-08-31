package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 2;

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

    public ScoreDisplays makeScoreDisplayForm(Score score, ScoreDisplays scoreDisplays) {
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

        Frame currentFrame = get(getFramesSize() - 1);
        BallThrowCount currentFrameBallCount = currentFrame.getBallThrowCount();

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

        scoreDisplays.setBeforeDisplay(score.getDisplayScore(new BallThrowCount(2)));
        currentFrame.sumTotalScore(score);
        addEmptyNextFrame();

        return scoreDisplays;
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
        return this.get(getFramesSize() - 1);
    }

    TotalScore getLsatIndexTotalScore() {
        return getTotalScore(this.frames.get(getFramesSize() - 1));
    }

    private TotalScore getTotalScore(Frame currentFrame) {
        return currentFrame.getTotalScore();
    }
}

