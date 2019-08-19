package domain;

class BawlingGame {
    private final Frames frames;
    private int totalScore;

    BawlingGame(Score score) {
        this.frames = new Frames();
        this.totalScore = score.getScore();
    }

    int getTotalScore(Score score) {
        return score.getScore() + this.totalScore;
    }
}
