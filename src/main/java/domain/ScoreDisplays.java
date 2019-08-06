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

        addAndRemoveAtAddedIndex(beforeIndex, this.scoreDisplays.get(beforeIndex)  + scoreDisplay);
    }

    void addAndRemoveAtAddedIndex(int index, String value) {
        this.scoreDisplays.add(index, value);

        this.scoreDisplays.remove(index + 1);
    }
}
