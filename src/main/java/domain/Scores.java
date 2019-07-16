package domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private List<Score> scores;

    public Scores() {
        this.scores = new ArrayList<>();
    }

    public Scores(List<Score> score) {
        this.scores = score;
    }

    public Score getFirstScore() {
        return this.scores.get(0);
    }

    public Score getSecondScore() {
        return this.scores.get(1);
    }

    public boolean add(Score score) {
        return this.scores.add(score);
    }
}
