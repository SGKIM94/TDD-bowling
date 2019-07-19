package view;

import domain.ScoreDisplays;

public class BallingFrame {
    public static void printNameFrame() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printEmptyFrames(int emptyFrameCount) {
        makeEmptyFrameFormat(emptyFrameCount);
    }

    public static void printFrames(int frameTotalCount, ScoreDisplays scoreDisplays) {
        // 라운드로 기준으로 묶는데, 출력하는 형태는 한 라운드안에 넘어가지 않는 경우는 묶여야 한다.
        // 출력 형태를 어디서 묶어서 주는 것이 맞는 건지 고민해봐야됨
        // 예를 들어 view 에서 라운드랑 출력형태를 매칭시켜서 한 라운드에 2개의 출력형태가 들어있는 경우
        // 만들어주는 것이 필요함.
        // 출력형태를 어디서 만들어주는게 맞는걸까?
        int scoreDisplaysSize = scoreDisplays.displaysSize();
        for (int i = 0; i < scoreDisplaysSize; i++) {
            makeFrameWhenExistScoreDisplay(scoreDisplays.get(i));
        }

        // 점수의 경계를 구분해주는 별도의 boolean이 필요할 듯
        int remainFrameCount = frameTotalCount - scoreDisplaysSize;
        for (int i = scoreDisplaysSize; i < remainFrameCount; i++) {
            printEmptyFrame();
        }

        printEdgeFrame();
    }

    private static void makeFrameWhenExistScoreDisplay(String scoreDisplay) {
        System.out.print("   " + scoreDisplay + "  |");
    }

    private static void makeEmptyFrameFormat(int emptyFrameCount) {
        for (int i = 0; i < emptyFrameCount - 1; i++) {
            printEmptyFrame();
        }

        printEdgeFrame();
    }

    public static void printEmptyFrame() {
        System.out.print("      |");
    }

    public static void printEdgeFrame() {
        System.out.println("      |");
    }
}
