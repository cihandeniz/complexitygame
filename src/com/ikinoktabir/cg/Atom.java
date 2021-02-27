package com.ikinoktabir.cg;

public class Atom extends Matter {

    public Atom() {
        super(10000, 10, 100000);
    }

    @Override
    public void react() {
        var result = new Object() {
            Direction direction;
            double energy;
        };

        if (space().energy() > energy() * 4) {
            direction(Direction.random());
            return;
        }

        result.energy = energy() / 2;

        Direction.forEach(direction -> {
            var energy = space().adjacent(direction).energy() - space().adjacent(direction.opposite()).energy();
            if (energy > result.energy) {
                result.direction = direction;
                result.energy = energy;
            }
        });

        if (result.direction != null) {
            if (result.energy > energy() * 2) {
                direction(result.direction.right().right());
            } else if (result.energy > energy() * 1.5) {
                direction(towards(result.direction));
            } else {
                direction(awayFrom(result.direction));
            }
        } else {
            direction(momentum());
        }
    }

    @Override
    public double energyRate() {
        return direction() != null ? -0.0001 : 0.0001;
    }

    @Override
    public void create() {
        space().add(new Atom());
        space().add(new Atom());
    }

    @Override
    public void destroy() {
    }

}
