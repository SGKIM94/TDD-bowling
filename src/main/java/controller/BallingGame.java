package controller;

import domain.Player;
import domain.Score;
import view.BallingFrame;
import view.NameFrame;

import java.util.ArrayList;
import java.util.List;

public class BallingGame {

    public static void main() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        Player player = new Player("KSG");

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);

        BallingFrame.printEmptyFrames(10);

        String inputFirstScore = "10";
        Score firstScore = new Score(inputFirstScore);
        int ballThrowCount = 1;

        System.out.println("1프레임 투구 : " + inputFirstScore);

        List<String> scoreDisplays = new ArrayList<>();
        scoreDisplays.add(firstScore.getDisplayScore(ballThrowCount));

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);
        BallingFrame.printFrames(10, scoreDisplays);
    }
}
