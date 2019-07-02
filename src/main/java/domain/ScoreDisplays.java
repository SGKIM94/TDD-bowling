package domain;

import java.util.ArrayList;
import java.util.List;

public class ScoreDisplays {
    private List<String> scoreDisplays;

    public ScoreDisplays() {
        this.scoreDisplays = new ArrayList<>();
    }

    public ScoreDisplays(List<String> scoreDisplays) {
        this.scoreDisplays = scoreDisplays;
    }

    public int displaysSize() {
        return this.scoreDisplays.size();
    }

    public boolean add(String scoreDisplay) {
        return this.scoreDisplays.add(scoreDisplay);
    }

    public String get(int index) {
        return this.scoreDisplays.get(index);
    }
}
