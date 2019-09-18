package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 2;
    private static final int SUBTRACT_ARRAY_LENGTH_WITH_INDEX = 1;

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
        addCurrentScoreWhenSpareScore(getBeforeFrame(), currentFrame, score);

        scoreDisplays.setBeforeDisplay(secondScoreDisplay);

        addEmptyNextFrame();

        return scoreDisplays;
    }

    private void addCurrentScoreWhenSpareScore(Frame beforeFrame, Frame currentFrame, Score score) {
        String beforeScoreDisplay = beforeFrame.getDisplayScore();

        System.out.println(score.getScore());
        System.out.println(beforeScoreDisplay);

        if ("/".equals(beforeScoreDisplay)) {
            currentFrame.sumTotalScore(score);
            return;
        }

        if ("X".equals(beforeScoreDisplay)) {
            currentFrame.sumTotalScore(new Score(beforeFrame.getTotalScore().getTotalScore()));
        }
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

