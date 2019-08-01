package domain;

import org.omg.CORBA.BAD_CONTEXT;

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
        if (isFramesSizeZero() && score.isStrike()) {
            return getScoreDisplayAndAddFrames(score);
        }

        if (isFramesSizeZero() && score.isSmallerThanStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);

            this.frames.add(new Frame(score, ballThrowCount, scoreDisplay));

            return scoreDisplay;
        }

        int currentFrameIndex = getFramesSize() - 1;
        BallThrowCount currentFrameBallCount = frames.get(currentFrameIndex).getBallThrowCount();
        // zero ball count 면 이전에 canSkipFrame 조건에 성립하지 못한 것이다.
        if (currentFrameBallCount.isZeroBallThrowing() && score.isStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);

            frames.get(currentFrameIndex).makeFrame(score, new BallThrowCount(1), scoreDisplay);
            frames.add(new Frame());

            return scoreDisplay;
        }


        if (currentFrameBallCount.isZeroBallThrowing() && score.isSmallerThanStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            String scoreDisplay = score.getDisplayScore(ballThrowCount);
            Frame currentFrame = frames.get(currentFrameIndex);

            currentFrame.makeFrame(score, new BallThrowCount(1), scoreDisplay);

            Score sumScore = currentFrame.getFirstScore().sumScores(score);

            if (sumScore.isStrike() || sumScore.isZero()) {
                return sumScore.getDisplayScore(new BallThrowCount(2));
            }

            return currentFrame.getFirstScore() + ScoreGroup.ELSE.getDisplay() + score;
        }

        Score firstScore = this.frames.get(currentFrameIndex).getFirstScore();
        return firstScore + ScoreGroup.ELSE.getDisplay() + firstScore.sumScores(score).getDisplayScore(new BallThrowCount(2));
//
//        BallThrowCount beforeFrameBallCount = this.frames.get(currentFrameIndex).getBallThrowCount();
//        Score firstScore = this.frames.get(currentFrameIndex).getFirstScore();
//
//        if (isBallCountOne(beforeFrameBallCount) && score.isStrike()) {
//            return getScoreDisplayAndAddFrames(score);
//        }
//
//        if (isBallCountTwo(beforeFrameBallCount)
//                && score.sumScores(firstScore).isSmallerThanStrike()) {
//
//            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(1));
//            return beforeScoreDisplay + score.getScore();
//        }
//
//        if (isBallCountTwo(beforeFrameBallCount)
//                && score.sumScores(firstScore).isStrike()) {
//
//            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(2));
//            return beforeScoreDisplay + score.getScore();
//        }
//
//        if (isBallCountTwo(beforeFrameBallCount)
//                && score.sumScores(firstScore).isZero()) {
//
//            String beforeScoreDisplay = firstScore.getDisplayScore(new BallThrowCount(2));
//            return beforeScoreDisplay + score.getScore();
//        }
//
//        return score.getDisplayScore(new BallThrowCount(1));
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

    private boolean isBallCountTwo(BallThrowCount beforeBallCount) {
        return beforeBallCount.equals(new BallThrowCount(2));
    }

    private boolean isBallCountOne(BallThrowCount beforeBallCount) {
        return beforeBallCount.equals(new BallThrowCount(1));
    }
}

