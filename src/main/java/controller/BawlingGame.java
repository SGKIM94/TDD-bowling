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

        makeFramesFormat(firstScore, player, scoreDisplays, "1");

        Score secondScore = new Score(8);
        scores.add(secondScore);
        scoreDisplays = frames.makeScoreDisplayForm(secondScore, scoreDisplays);

        makeFramesFormat(secondScore, player, scoreDisplays, "2");

        Score thirdScore = new Score(2);
        scores.add(thirdScore);
        scoreDisplays = frames.makeScoreDisplayForm(thirdScore, scoreDisplays);

        makeFramesFormat(thirdScore, player, scoreDisplays, "3");

        //TODO : controller 중복 제거하기 (함수형으로 변경)
        //TODO : 람다와 스트림으로 변경할 수 있는 것 변경하기
        // 람다와 스트림화 시키기
        // Frames 의 canSkipFrame 으로 검사해서 넘어갈 수 있으면 새로운 Frame 을 생성하고
        // 넘어가지 못하면 Frame 을 생성해서 기존의 정보에 점수를 더해서 더한 형태의 형태를 출력한다.
    }

    private static void makeFramesFormat(Score score, Player player, ScoreDisplays scoreDisplays, String turnNumber) {
        System.out.println(turnNumber + "프레임 투구 : " + score.toString());

        BallingFrame.printNameFrame();
        NameFrame.printNameFrame(player);
        BallingFrame.printFrames(10, scoreDisplays);
    }
}
