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
        int displaysSize = scoreDisplays.displaysSize();
        for (int i = 0; i < displaysSize; i++) {
            makeFrameWhenExistScoreDisplay(scoreDisplays.get(i));
        }

        int remainFrameCount = frameTotalCount - displaysSize;
        for (int i = displaysSize; i < remainFrameCount; i++) {
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

    private static void printEmptyFrame() {
        System.out.print("      |");
    }

    private static void printEdgeFrame() {
        System.out.println("      |");
    }
}
