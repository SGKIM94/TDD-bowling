package controller;

import domain.*;
import view.BallingFrame;
import view.NameFrame;

public class BawlingGame {
    public static void main() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        Player player = new Player("KSG");

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);

        BallingFrame.printEmptyFrames(10);

        BallThrowCount ballThrowCount = new BallThrowCount(1);
        domain.BawlingGame bawlingGame = new domain.BawlingGame();

        Scores scores = new Scores();

        Score firstScore = new Score(10);
        scores.add(firstScore);
        bawlingGame.setFramesScore(scores);
        makeFramesFormat(firstScore, player, ballThrowCount, "1");

        Score secondScore = new Score(8);
        scores.add(secondScore);
        bawlingGame.setFramesScore(scores);
        makeFramesFormat(secondScore, player, ballThrowCount, "2");

        Score thirdScore = new Score(2);
        bawlingGame.setFramesScore(scores);
        scores.add(thirdScore);
        makeFramesFormat(thirdScore, player, ballThrowCount, "3");

        //TODO : ballThrowCount List<Integer> 로 일급 콜렉션으로 만들기
        //TODO : 프레임을 넘어가지 못했을 때 출력 값을 한 프레임 안에 출력할 수 있도록 하기 (example : 8 / X)
        //TODO : controller 중복 제거하기 (함수형으로 변경)
        //TODO : 람다와 스트림으로 변경할 수 있는 것 변경하기
        //TODO : Frame 에 몇 카운트 인지 넣기
        //TODO : 점수를 입력할 때 넘어갈 수 있는지 계속 확인
        // 넘어갈 수 있다면 넘기고 못넘어가면 넘기지 말기
        // ballCount 를 수동으로 더해주지 않아도 자동으로 증가할 수 있도록 해야할듯
        // 일급콜렉션화 시켜서 하면 될듯
        // 람다와 스트림화 시키기

    }

    private static void makeFramesFormat(Score score, Player player, BallThrowCount ballThrowCount, String turnNumber) {
        System.out.println(turnNumber + "프레임 투구 : " + score);

        ScoreDisplays scoreDisplays = new ScoreDisplays();
        scoreDisplays.add(score.getDisplayScore(ballThrowCount));

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);
        BallingFrame.printFrames(10, scoreDisplays);
    }
}
