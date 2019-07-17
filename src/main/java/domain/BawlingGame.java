package domain;

public class BawlingGame {
    //TODO : ENUM 으로 만들어주는 것 필요
    private static final int START_ROUND = 0;
    private static final int FIRST_BALL_THROW_COUNT = 1;
    private static final int BEFORE_FIRST_BALL_THROWING = 0;

    private final Frames frames;
    private final Round round;

    public BawlingGame() {
        this.frames = new Frames();
        this.round = new Round(START_ROUND);
    }

    public boolean isEqualMaxValue(int score, int maxScore) {
        return score == maxScore;
    }

    public Frame setFramesScore(Scores scores) {
        Score firstScore = scores.getFirstScore();

        if (this.round.isFirstRound()) {
            Frame frame = new Frame(firstScore, new BallThrowCount(FIRST_BALL_THROW_COUNT));

            this.frames.add(frame);
            this.round.addRound();

            return frame;
        }

        Frame beforeFrame = this.frames.getBeforeFrame();
        Frame frame = new Frame(scores, new BallThrowCount(beforeFrame.getNextBallCount()));


        if (!frame.canSkipThisFrame()) {
            this.frames.add(new Frame(firstScore.sumScores(beforeFrame.getFirstScore()), beforeFrame.getBallThrowCount()));

            return frame;
        }

        this.frames.add(frame);

        return frame;
    }

    private boolean isFirstThrowing() {
        return this.frames.getFramesSize() == BEFORE_FIRST_BALL_THROWING;
    }

    public boolean checkFrameStepOverNext() {
        return this.frames.get(this.round.getRound()).canSkipThisFrame();
    }


}
