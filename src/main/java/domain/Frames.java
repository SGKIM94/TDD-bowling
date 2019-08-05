package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 1;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    boolean add(Frame frame) {
        return this.frames.add(frame);
    }

    int getFramesSize() {
        return this.frames.size();
    }

    Frame get(int index) {
        return this.frames.get(index);
    }

    Frame getBeforeFrame() {
        return this.frames.get(getFramesSize() - BEFORE_FRAME);
    }

    public ScoreDisplays makeScoreDisplayForm(Score score, ScoreDisplays scoreDisplays) {
        if (isFramesSizeZero() && score.isStrike()) {
            scoreDisplays.add(getScoreDisplayAndAddFrames(score));

            return scoreDisplays;
        }

        if (isFramesSizeZero() && score.isSmallerThanStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);

            this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }

        int currentFrameIndex = getFramesSize() - 1;
        BallThrowCount currentFrameBallCount = frames.get(currentFrameIndex).getBallThrowCount();
        // zero ball count 면 이전에 canSkipFrame 조건에 성립하지 못한 것이다.
        if (currentFrameBallCount.isZeroBallThrowing() && score.isStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);

            frames.get(currentFrameIndex).makeFrame(score, new BallThrowCount(1), scoreDisplay);
            frames.add(new Frame());

            scoreDisplays.add(scoreDisplay);

            return scoreDisplays;
        }


        if (currentFrameBallCount.isZeroBallThrowing() && score.isSmallerThanStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);
            Frame currentFrame = frames.get(currentFrameIndex);

            currentFrame.makeFrame(score, new BallThrowCount(1), scoreDisplay);

            scoreDisplays.add(currentFrame.getDisplayScore());

            return scoreDisplays;
        }

        Score firstScore = this.frames.get(currentFrameIndex).getFirstScore();
        scoreDisplays.setBeforeDisplay(firstScore.sumScores(score).getDisplayScore(new BallThrowCount(2)));

        return scoreDisplays;
    }

    private boolean isFramesSizeZero() {
        return this.frames.size() == 0;
    }

    private String getScoreDisplayAndAddFrames(Score score) {
        BallThrowCount ballThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(ballThrowCount);

        this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));
        this.frames.add(new Frame());
        return scoreDisplay;
    }
}

