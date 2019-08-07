package domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreDisplays {
    private List<String> scoreDisplays;

    public ScoreDisplays() {
        this.scoreDisplays = new ArrayList<>();
    }

    ScoreDisplays(List<String> scoreDisplays) {
        this.scoreDisplays = scoreDisplays;
    }

    public int displaysSize() {
        return this.scoreDisplays.size();
    }

    boolean add(String scoreDisplay) {
        return this.scoreDisplays.add(scoreDisplay);
    }

    public String get(int index) {
        return this.scoreDisplays.get(index);
    }

    void setBeforeDisplay(String scoreDisplay) {
        int beforeIndex = displaysSize() - 1;

        scoreDisplay = removeLastOrChar(scoreDisplay);

        addAndRemoveAtAddedIndex(beforeIndex, this.scoreDisplays.get(beforeIndex) + scoreDisplay);
    }

    public String removeLastOrChar(String scoreDisplay) {
        int displaySize = scoreDisplay.length();

        if (scoreDisplay.charAt(displaySize - 1) == '|') {
            return scoreDisplay.substring(0, displaySize - 1);
        }

        return scoreDisplay;
    }

    void addAndRemoveAtAddedIndex(int index, String value) {
        this.scoreDisplays.add(index, value);

        this.scoreDisplays.remove(index + 1);
    }
}
