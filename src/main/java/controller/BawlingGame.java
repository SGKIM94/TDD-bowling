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

        domain.BawlingGame bawlingGame = new domain.BawlingGame();

        Scores scores = new Scores();

        Score firstScore = new Score(10);
        scores.add(firstScore);
        frames.add(bawlingGame.setFramesScore(scores));
        frames.makeScoreDisplayForm(firstScore);

        makeFramesFormat(firstScore, player, scoreDisplays, "1");

        Score secondScore = new Score(8);
        scores.add(secondScore);
        frames.add(bawlingGame.setFramesScore(scores));
        frames.makeScoreDisplayForm(secondScore);

        makeFramesFormat(secondScore, player, scoreDisplays, "2");

        Score thirdScore = new Score(2);
        frames.add(bawlingGame.setFramesScore(scores));
        scores.add(thirdScore);
        frames.makeScoreDisplayForm(thirdScore);

        makeFramesFormat(thirdScore, player, scoreDisplays, "3");

        //TODO : ballThrowCount List<Integer> 로 일급 콜렉션으로 만들기
        //TODO : controller 중복 제거하기 (함수형으로 변경)
        //TODO : 람다와 스트림으로 변경할 수 있는 것 변경하기
        //TODO : Frame 에 몇 카운트 인지 넣기
        //TODO : 점수를 입력할 때 넘어갈 수 있는지 계속 확인
        // 넘어갈 수 있다면 넘기고 못넘어가면 넘기지 말기
        // ballCount 를 수동으로 더해주지 않아도 자동으로 증가할 수 있도록 해야할듯
        // 람다와 스트림화 시키기
        // 출력하기 전에 넘어갈 수 있는지 검사하고 출력할 때 라운드를 보고 출력해야할 듯
    }

    private static void makeFramesFormat(Score score, Player player, ScoreDisplays scoreDisplays, String turnNumber) {
        System.out.println(turnNumber + "프레임 투구 : " + score);

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);
        BallingFrame.printFrames(10, scoreDisplays);
    }
}
