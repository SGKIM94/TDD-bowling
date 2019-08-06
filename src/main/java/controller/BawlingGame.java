package controller;

import domain.*;
import view.BallingFrame;
import view.NameFrame;

class BawlingGame {
    static void main() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        Player player = new Player("KSG");

        Frames frames = new Frames();

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);

        BallingFrame.printEmptyFrames(10);

        ScoreDisplays scoreDisplays = new ScoreDisplays();

        Scores scores = new Scores();

        Score firstScore = new Score(10);
        scores.add(firstScore);
        scoreDisplays = frames.makeScoreDisplayForm(firstScore, scoreDisplays);

        printCurrentFrames(firstScore, player, scoreDisplays, "1");

        Score secondScore = new Score(8);
        scores.add(secondScore);
        scoreDisplays = frames.makeScoreDisplayForm(secondScore, scoreDisplays);

        printCurrentFrames(secondScore, player, scoreDisplays, "2");

        Score thirdScore = new Score(2);
        scores.add(thirdScore);
        scoreDisplays = frames.makeScoreDisplayForm(thirdScore, scoreDisplays);

        printCurrentFrames(thirdScore, player, scoreDisplays, "3");

        Score fourthScore = new Score(5);
        scores.add(fourthScore);
        scoreDisplays = frames.makeScoreDisplayForm(fourthScore, scoreDisplays);

        printCurrentFrames(fourthScore, player, scoreDisplays, "4");

        Score fifthScore = new Score(3);
        scores.add(fifthScore);
        scoreDisplays = frames.makeScoreDisplayForm(fifthScore, scoreDisplays);

        printCurrentFrames(fifthScore, player, scoreDisplays, "5");

        Score sixScore = new Score(10);
        scores.add(sixScore);
        scoreDisplays = frames.makeScoreDisplayForm(sixScore, scoreDisplays);

        printCurrentFrames(sixScore, player, scoreDisplays, "6");
    }

    private static void printCurrentFrames(Score score, Player player, ScoreDisplays scoreDisplays, String turnNumber) {
        System.out.println(turnNumber + "프레임 투구 : " + score.toString());

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);
        BallingFrame.printFrames(10, scoreDisplays);
    }
}
