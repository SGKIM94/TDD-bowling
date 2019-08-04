package domain;

class BawlingGame {
    //TODO : ENUM 으로 만들어주는 것 필요
    private static final int START_ROUND = 0;
    private static final int FIRST_BALL_THROW_COUNT = 1;

    private final Frames frames;
    private final Round round;

    BawlingGame() {
        this.frames = new Frames();
        this.round = new Round(START_ROUND);
    }

    boolean isEqualMaxValue(int score, int maxScore) {
        return score == maxScore;
    }

    Frame setFramesScore(Scores scores) {
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
        addRound();

        return frame;
    }

    boolean checkFrameStepOverNext() {
        return this.frames.get(this.round.getRound()).canSkipThisFrame();
    }

    int addRound() {
        return this.round.addRound();
    }
}
