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
        BallThrowCount ballThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(ballThrowCount);

        if (isFramesSizeZero() && score.isStrike()) {
            scoreDisplays.add(getScoreDisplayAndAddFrames(score));

            return scoreDisplays;
        }

        if (isFramesSizeZero() && score.isSmallerThanStrike()) {
            add(new Frame(score, ballThrowCount, scoreDisplay));

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }

        int currentFrameIndex = getFramesSize() - 1;
        Frame currentFrame = get(currentFrameIndex);
        BallThrowCount currentFrameBallCount = currentFrame.getBallThrowCount();

        if (currentFrameBallCount.isZeroBallThrowing() && score.isStrike()) {
            currentFrame.makeFrame(score, new BallThrowCount(1), scoreDisplay);
            currentFrame.getTotalScore().addTotalScoreWithBefore(getBeforeFrame().getTotalScore());
            add(new Frame());

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }


        if (currentFrameBallCount.isZeroBallThrowing() && score.isSmallerThanStrike()) {
            currentFrame.makeFrame(score, new BallThrowCount(1), scoreDisplay);
            currentFrame.getTotalScore().addTotalScoreWithBefore(getBeforeFrame().getTotalScore());
            scoreDisplays.add(currentFrame.getDisplayScore());

            return scoreDisplays;
        }

        scoreDisplays.setBeforeDisplay(score.getDisplayScore(new BallThrowCount(2)));
        currentFrame.sumTotalScore(score);

        add(new Frame());

        return scoreDisplays;
    }

    private boolean isFramesSizeZero() {
        return this.frames.isEmpty();
    }

    private String getScoreDisplayAndAddFrames(Score score) {
        BallThrowCount ballThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(ballThrowCount);

        this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));
        this.frames.add(new Frame());

        return scoreDisplay;
    }

    public Frame getLastIndex() {
        return this.get(getFramesSize() - 1);
    }

    TotalScore getLsatIndexTotalScore() {
        return this.frames.get(getFramesSize() - 1).getTotalScore();
    }
}

