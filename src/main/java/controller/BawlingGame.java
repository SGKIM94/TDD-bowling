package controller;

import domain.*;
import view.BallingFrame;
import view.NameFrame;

import static view.ScoreFrame.printScoreFrames;

class BawlingGame {
    private ScoreDisplays scoreDisplays;
    private Player player;
    private Frames frames;
    private Scores scores;

    BawlingGame(String playerName) {
        inputPlayerName();

        this.player = new Player(playerName);
        this.frames = new Frames();
        this.scoreDisplays = new ScoreDisplays();
        this.scores = new Scores();
    }

    private void inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
    }

    void bowlingGameStart() {
        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(this.player);
        BallingFrame.printEmptyFrames(10);

        bowl(10, "1")
                .bowl(8, "2")
                .bowl(2, "3")
                .bowl(5, "4")
                .bowl(3, "5")
                .bowl(10, "6");
    }

    private BawlingGame bowl(int score, String turnNumber) {
        Score firstScore = new Score(score);
        scores.add(firstScore);
        this.scoreDisplays = frames.makeScoreDisplayForm(firstScore, scoreDisplays);

        printCurrentFrames(firstScore, scoreDisplays, turnNumber);
        printCurrentFrameScore();
        return this;
    }

    private void printCurrentFrames(Score score, ScoreDisplays scoreDisplays, String turnNumber) {
        System.out.println(turnNumber + "프레임 투구 : " + score.toString());

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(this.player);
        BallingFrame.printFrames(scoreDisplays);
    }

    private void printCurrentFrameScore() {
        printScoreFrames(this.frames);
    }
}
