package com.ikinoktabir.cg;

public class Atom extends Matter {

    public Atom() {
        super(10000, 5000, 15000);
    }

    @Override
    public void react() {
        var result = new Object() {
            Direction direction;
            double energy;
        };

        result.energy = 0;

        Direction.forEach(direction -> {
            var energy = space().adjacent(direction).energy() - space().adjacent(direction.opposite()).energy();
            if (energy > result.energy) {
                result.direction = direction;
                result.energy = energy;
            }
        });

        if (result.direction != null) {
            if (result.energy > mass() * 4) {
                direction(result.direction);
            } else if (result.energy > mass() * 2) {
                direction(towards(result.direction));
            } else {
                direction(result.direction.right().right());
            }
        }
    }

    @Override
    public double decayRate() {
        return direction() == null ? 0.001 : -0.001;
    }

    @Override
    public void create() {
        space().add(new Molecule());
    }

    @Override
    public void destroy() {
    }
}
