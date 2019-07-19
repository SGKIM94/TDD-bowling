package view;

import domain.Frame;
import domain.Round;
import domain.Score;

public class FrameDisplay {
    private String scoreDisplay;
    private Round round;

    public FrameDisplay(Score score, Frame frame) {
        makeFramesScoreForm(score, frame);
    }

    public Round makeFramesScoreForm(Score score, Frame frame) {
        this.scoreDisplay = frame.makeScoreDisplayForm(score);

        if (frame.canSkipThisFrame()) {
            this.round.addRound();

            return round;
        }

        return round;
    }
}
