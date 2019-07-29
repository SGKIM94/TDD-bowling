package domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int BEFORE_FRAME = 1;

    private List<Frame> frames;

    Frames() {
        this.frames = new ArrayList<>();
    }

    boolean add(Frame frame) {
        return this.frames.add(frame);
    }

    private int getFramesSize() {
        return this.frames.size();
    }

    Frame get(int index) {
        return this.frames.get(index);
    }

    Frame getBeforeFrame() {
        return this.frames.get(getFramesSize() - BEFORE_FRAME);
    }

    String makeScoreDisplayForm(Score score) {
        int beforeFrameIndex = getFramesSize() - 1;

        if (this.frames.get(beforeFrameIndex).getBallThrowCount().equals(new BallThrowCount(1))
                && score.isStrike()) {
            BallThrowCount ballThrowCount = new BallThrowCount(1);
            this.frames.add(new Frame(score, ballThrowCount, score.getDisplayScore(ballThrowCount)));
        }

        if (this.frames.get(beforeFrameIndex).getBallThrowCount().equals(new BallThrowCount(2))
            && score.sumScores(this.frames.get(beforeFrameIndex).getFirstScore()).isSmallerThanStrike()) {

            String beforeScoreDisplay = this.frames.get(beforeFrameIndex).getFirstScore().getDisplayScore(new BallThrowCount(1));
            return beforeScoreDisplay + score.getScore();
        }

        if (this.frames.get(beforeFrameIndex).getBallThrowCount().equals(new BallThrowCount(2))
                && score.sumScores(this.frames.get(beforeFrameIndex).getFirstScore()).isStrike()) {

            String beforeScoreDisplay = this.frames.get(beforeFrameIndex).getFirstScore().getDisplayScore(new BallThrowCount(2));
            return beforeScoreDisplay + score.getScore();
        }

        if (this.frames.get(beforeFrameIndex).getBallThrowCount().equals(new BallThrowCount(2))
                && score.sumScores(this.frames.get(beforeFrameIndex).getFirstScore()).isZero()) {

            String beforeScoreDisplay = this.frames.get(beforeFrameIndex).getFirstScore().getDisplayScore(new BallThrowCount(2));
            return beforeScoreDisplay + score.getScore();
        }

        throw new IllegalArgumentException("잘못된 점수 입력입니다.");
    }
}

