package com.ikinoktabir.cg;

import java.util.function.Consumer;

public enum Direction {
    Top(0, -1), TopRight(1, -1), Right(1, 0), BottomRight(1, 1), Bottom(0, 1), BottomLeft(-1, 1), Left(-1, 0),
    TopLeft(-1, -1);

    public static final int COUNT = values().length;
    private static final double ENERGY = Math.sqrt(2) / (4 + 4 * Math.sqrt(2));

    public static void forEach(Consumer<Direction> consumer) {
        for (var direction : Direction.values()) {
            consumer.accept(direction);
        }
    }

    public static Direction random() {
        return values()[(int) (Math.random() * COUNT)];
    }

    private final int offsetX;
    private final int offsetY;

    private Direction(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int offsetX() {
        return offsetX;
    }

    public int offsetY() {
        return offsetY;
    }

    public double coef() {
        return ENERGY / distance();
    }

    private double distance() {
        return Math.sqrt(Math.abs(offsetX) + Math.abs(offsetY));
    }

    public Direction right() {
        return Direction.values()[(ordinal() + 1) % COUNT];
    }

    public Direction left() {
        return Direction.values()[(ordinal() - 1 + COUNT) % COUNT];
    }

    public Direction opposite() {
        return Direction.values()[(ordinal() + COUNT / 2) % COUNT];
    }

    public Direction towards(Direction direction) {
        if (direction == this) {
            return this;
        }

        var directionOrdinal = direction.ordinal();

        if (directionOrdinal < ordinal()) {
            directionOrdinal += COUNT;
        }

        if (directionOrdinal - ordinal() <= COUNT / 2) {
            return right();
        } else {
            return left();
        }
    }

    public Direction awayFrom(Direction direction) {
        return towards(direction.opposite());
    }
}
