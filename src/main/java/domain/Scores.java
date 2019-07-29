package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    Scores(Score score) {
        this.scores = new ArrayList<>(Collections.singletonList(score));
    }

    Score getFirstScore() {
        return this.scores.get(0);
    }

    Score getSecondScore() {
        return this.scores.get(1);
    }

    public void add(Score score) {
        this.scores.add(score);
    }
}
