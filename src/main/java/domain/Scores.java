package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private static final int FIRST_INDEX = 0;

    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    Scores(Score score) {
        this.scores = new ArrayList<>(Collections.singletonList(score));
    }

    Score getFirstScore() {
        return this.scores.get(FIRST_INDEX);
    }

    Score getSecondScore() {
        return this.scores.get(1);
    }

    public Scores add(Score score) {
        this.scores.add(score);

        return this;
    }
}
