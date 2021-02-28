package com.ikinoktabir.cg;

public abstract class Matter {
    private double mass;
    private Space space;
    private Direction direction;
    private Direction momentum;
    private double min;
    private double max;

    public Matter(double mass, double min, double max) {
        this.mass = mass;
        this.min = min;
        this.max = max;
    }

    public Space space() {
        return space;
    }

    public void space(Space space) {
        this.space = space;
    }

    public double mass() {
        return mass;
    }

    public void direction(Direction direction) {
        momentum = this.direction;

        this.direction = direction;
    }

    public Direction direction() {
        return direction;
    }

    public Direction momentum() {
        return momentum;
    }

    public Direction towards(Direction direction) {
        if (momentum() == null) {
            return direction;
        }

        return momentum().towards(direction);
    }

    public Direction awayFrom(Direction direction) {
        if (momentum() == null) {
            return direction.opposite();
        }

        return momentum().awayFrom(direction);
    }

    public void move() {
        mass -= mass * decayRate();

        if (space == null || direction == null) {
            return;
        }

        space.adjacent(direction).add(this);
        direction(null);

        if (mass > max) {
            space().remove(this);
            create();
        } else if (mass < min) {
            space().remove(this);
            destroy();
        }
    }

    public abstract void react();

    public abstract double decayRate();

    public abstract void create();

    public abstract void destroy();
}
