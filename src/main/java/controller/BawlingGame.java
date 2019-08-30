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
    private int turnNumber;

    BawlingGame(String playerName) {
        inputPlayerName();

        this.player = new Player(playerName);
        this.frames = new Frames();
        this.scoreDisplays = new ScoreDisplays();
        this.scores = new Scores();
        this.turnNumber = 0;
    }

    private void inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
    }

    void bowlingGameStart() {
        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(this.player);
        BallingFrame.printEmptyFrames(10);

        bowl(10)
                .bowl(8)
                .bowl(2)
                .bowl(5)
                .bowl(3)
                .bowl(10);
    }

    private BawlingGame bowl(int inputScore) {
        Score score = new Score(inputScore);
        scores.add(score);
        this.scoreDisplays = frames.makeScoreDisplayForm(score, scoreDisplays);

        printCurrentFrames(score, scoreDisplays);
        printCurrentFrameScore();
        return this;
    }

    private void printCurrentFrames(Score score, ScoreDisplays scoreDisplays) {
        System.out.println(turnNumber++ + "프레임 투구 : " + score.toString());

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(this.player);
        BallingFrame.printFrames(scoreDisplays);
    }

    private void printCurrentFrameScore() {
        printScoreFrames(this.frames);
    }
}
