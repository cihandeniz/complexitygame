package com.ikinoktabir.cg;

public class Molecule extends Matter {

    public Molecule() {
        super(100000, 100000, 100000);
    }

    private int count;

    @Override
    public void react() {
        if (count < 100) {
            count++;
            return;
        }

        count = 0;

        space().add(new Radiation());
    }

    @Override
    public double decayRate() {
        return 0;
    }

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }
}