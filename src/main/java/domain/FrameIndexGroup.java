package domain;

public enum FrameIndexGroup {
    SUBTRACT_ARRAY_LENGTH_WITH_INDEX(1),
    BEFORE_FRAME_INDEX(2),
    BEFORE_SECOND_TO_LAST_INDEX(3),
    BEFORE_THIRD_TO_LAST_INDEX(4);

    private int index;

    FrameIndexGroup(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
