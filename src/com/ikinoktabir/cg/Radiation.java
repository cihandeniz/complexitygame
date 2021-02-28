package com.ikinoktabir.cg;

public class Radiation extends Matter {

    public Radiation() {
        super(10, 1, 10);
    }

    @Override
    public void react() {
        if(momentum() == null){
            direction(Direction.random());
        } else {
            direction(momentum());
        }
    }

    @Override
    public double decayRate() {
        return 0.01;
    }

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }
}
