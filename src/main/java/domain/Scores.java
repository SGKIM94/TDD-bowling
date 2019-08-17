package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private static final int FIRST_INDEX = 0;
    private static final int ONE_BALL_THROW_COUNT = 1;
    private static final int FIRST_BAWL_SCORE = 0;
    private static final int SECOND_BAWL_SCORE = 1;

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

    Score getSumScores() {
        if (isOneBallThrowCount()) {
            return this.scores.get(FIRST_BAWL_SCORE);
        }

        return this.scores.get(FIRST_BAWL_SCORE).sumScores(this.scores.get(SECOND_BAWL_SCORE));
    }

    private boolean isOneBallThrowCount() {
        return this.scores.size() == ONE_BALL_THROW_COUNT;
    }

    int getScoresSize() {
        return this.scores.size();
    }
}
