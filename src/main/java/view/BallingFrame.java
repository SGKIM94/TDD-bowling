package view;

import java.util.List;

public class BallingFrame {
    public static void printNameFrame() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printEmptyFrames(int emptyFrameCount) {
        makeEmptyFrameFormat(emptyFrameCount);
    }

    public static void printFrames(int frameCount, List<String> scoreDisplays) {
        for (String scoreDisplay : scoreDisplays) {
            makeFrameWhenExistScoreDisplay(scoreDisplay);
        }

        int scoreDisplaySize = scoreDisplays.size();
        int remainFrameCount = frameCount - scoreDisplaySize;
        for (int i = scoreDisplaySize; i < remainFrameCount; i++) {
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
