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
