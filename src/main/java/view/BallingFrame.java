package view;

import domain.ScoreDisplays;

import static view.ScoreFrame.FRAME_TOTAL_SIZE;

public class BallingFrame {
    private static final int EXCEPT_START_INDEX = 1;

    public static void printNameFrame() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printEmptyFrames(int emptyFrameCount) {
        makeEmptyFrameFormat(emptyFrameCount);
    }

    public static void printFrames(ScoreDisplays scoreDisplays) {
        int displaysSize = scoreDisplays.displaysSize();
        for (int i = 0; i < displaysSize; i++) {
            makeFrameWhenExistScoreDisplay(scoreDisplays.get(i));
        }

        printRemainEmptyFrame(displaysSize);
        printEdgeFrame();
    }

    static void printRemainEmptyFrame(int size) {
        int remainFrameCount = FRAME_TOTAL_SIZE - size - 1;

        for (int i = 0; i < remainFrameCount; i++) {
            printEmptyFrame();
        }
    }

    private static void makeFrameWhenExistScoreDisplay(String scoreDisplay) {
        int scoreDisplaySize = scoreDisplay.length();

        if (isTwoDigitScoreDisplay(scoreDisplaySize)) {
            System.out.print("  " + scoreDisplay + "  |");
            return;
        }

        if (isThreeDigitScoreDisplay(scoreDisplaySize)) {
            System.out.print("  " + scoreDisplay + " |");
            return;
        }

        System.out.print("   " + scoreDisplay + "  |");
    }

    private static boolean isThreeDigitScoreDisplay(int scoreDisplaySize) {
        return scoreDisplaySize == 3;
    }

    private static boolean isTwoDigitScoreDisplay(int scoreDisplaySize) {
        return scoreDisplaySize == 2;
    }

    private static void makeEmptyFrameFormat(int emptyFrameCount) {
        for (int i = 0; i < emptyFrameCount - EXCEPT_START_INDEX; i++) {
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
