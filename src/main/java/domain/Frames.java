package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 1;

    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public boolean add(Frame frame) {
        return this.frames.add(frame);
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

    public String makeScoreDisplayForm(Score score) {
        if (this.frames.size() == 0) {
            return getScoreDisplayAndAddFrames(score);
        }

        int beforeFrameIndex = getFramesSize() - 1;

        BallThrowCount beforeFrameBallCount = this.frames.get(beforeFrameIndex).getBallThrowCount();
        Score firstScore = this.frames.get(beforeFrameIndex).getFirstScore();

        if (isBallCountOne(beforeFrameBallCount) && score.isStrike()) {
            return getScoreDisplayAndAddFrames(score);
        }

        if (isBallCountTwo(beforeFrameBallCount)
                && score.sumScores(firstScore).isSmallerThanStrike()) {

            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(1));
            return beforeScoreDisplay + score.getScore();
        }

        if (isBallCountTwo(beforeFrameBallCount)
                && score.sumScores(firstScore).isStrike()) {

            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(2));
            return beforeScoreDisplay + score.getScore();
        }

        if (isBallCountTwo(beforeFrameBallCount)
                && score.sumScores(firstScore).isZero()) {

            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(2));
            return beforeScoreDisplay + score.getScore();
        }

        return score.getDisplayScore(new BallThrowCount(1));
    }

    private String getScoreDisplayAndAddFrames(Score score) {
        BallThrowCount ballThrowCount = new BallThrowCount(1);
        String scoreDisplay = score.getDisplayScore(ballThrowCount);

        this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));
        return scoreDisplay;
    }

    private boolean isBallCountTwo(BallThrowCount beforeBallCount) {
        return beforeBallCount.equals(new BallThrowCount(2));
    }

    private boolean isBallCountOne(BallThrowCount beforeBallCount) {
        return beforeBallCount.equals(new BallThrowCount(1));
    }
}

