package com.ikinoktabir.cg;

public abstract class Matter {
    private double energy;
    private Space space;
    private Direction direction;
    private Direction momentum;
    private double min;
    private double max;

    public Matter(double energy, double min, double max) {
        this.energy = energy;
        this.min = min;
        this.max = max;
    }

    public Space space() {
        return space;
    }

    public void space(Space space) {
        this.space = space;
    }

    public double energy() {
        return energy;
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
        energy -= energy * energyRate();

        if (space == null || direction == null) {
            return;
        }

        space.adjacent(direction).add(this);
        direction(null);

        if (energy > max) {
            create();

            space().remove(this);
        } else if (energy < min) {
            destroy();

            space().remove(this);
        }
    }

    public abstract void react();

    public abstract double energyRate();

    public abstract void create();

    public abstract void destroy();
}
