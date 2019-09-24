package view;

import domain.Frame;
import domain.Frames;
import domain.TotalScore;

import static view.BallingFrame.printRemainEmptyFrame;

public class ScoreFrame {
    private static final int START_FRAME_INDEX = 0;
    static final int FRAME_TOTAL_SIZE = 10;
    private static final int TWO_DIGITS = 2;

    public static void printScoreFrames(Frames frames) {
        int framesSize = reduceOneSizeWhenLastFrameEmpty(frames);

        printStartFrame();

        for (int i = 0; i < framesSize; i++) {
            Frame frame = frames.get(i);
            makeFrameWhenExistScores(frame);
        }

        printRemainEmptyFrame(framesSize);
        printEdgeFrame();
    }

    private static int reduceOneSizeWhenLastFrameEmpty(Frames frames) {
        int framesSize = frames.getFramesSize();
        if (isLastFrameEmpty(frames.getLastFrame())) {
            return framesSize - 1;
        }

        return framesSize;
    }

    private static boolean isLastFrameEmpty(Frame lastFrame) {
        return lastFrame.isEmptyFrame();
    }

    private static void makeFrameWhenExistScores(Frame frame) {
        if (frame.getScoreSize() == START_FRAME_INDEX) {
            return;
        }

        if (isTwoDigits(frame.getTotalScore())) {
            System.out.print("  " + frame.getTotalScore() + "  |");
            return;
        }

        System.out.print("   " + frame.getTotalScore() + "  |");
    }

    private static boolean isTwoDigits(TotalScore totalScore) {
        return totalScore.toStringSize()  == TWO_DIGITS;
    }

    private static void printStartFrame() {
        System.out.print("|      |");
    }

    private static void printEdgeFrame() {
        System.out.println("      |");
    }
}


