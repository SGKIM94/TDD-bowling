package view;

import domain.Frame;
import domain.Frames;

public class ScoreFrame {
    public static void printScoreFrames(int frameTotalCount, Frames frames) {
        int framesSize = frames.getFramesSize();
        for (int i = 0; i < framesSize; i++) {
            Frame frame = frames.get(i);
            makeFrameWhenExistScores(frame);
        }

        int remainFrameCount = frameTotalCount - framesSize;
        for (int i = framesSize; i < remainFrameCount; i++) {
            printEmptyFrame();
        }

        printEmptyFrame();
    }

    private static void makeFrameWhenExistScores(Frame frame) {
        if (frame.getScoreSize() == 0) {
            System.out.print("|    |");
            return;
        }

        System.out.print("   " + frame.getSumScore().toString() + "  |");
    }

    private static void printEmptyFrame() {
        System.out.print("      |");
    }
}
