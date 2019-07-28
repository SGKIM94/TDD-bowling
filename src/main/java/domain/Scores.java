package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(Score score) {
        this.scores = new ArrayList<>(Collections.singletonList(score));
    }

    public Score getFirstScore() {
        return this.scores.get(0);
    }

    public Score getSecondScore() {
        return this.scores.get(1);
    }

    public boolean add(Score score) {
        return scores.add(score);
    }
}
