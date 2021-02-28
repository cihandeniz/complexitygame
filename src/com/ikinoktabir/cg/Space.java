package com.ikinoktabir.cg;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Space {
    private Energy energy;
    private Space[] adjacents;
    private Space anti;
    private List<Matter> matters;

    public Space() {
        energy = new Energy();
        adjacents = new Space[Direction.COUNT];
        matters = new ArrayList<Matter>();
    }

    public void adjacent(Direction direction, Space adjacent) {
        adjacents[direction.ordinal()] = adjacent;
    }

    public Space adjacent(Direction direction) {
        if (direction == null) {
            return this;
        }

        return adjacents[direction.ordinal()];
    }

    public void anti(Space anti) {
        this.anti = anti;
    }

    public void add(Matter matter) {
        if (matter.space() != null) {
            matter.space().matters.remove(matter);
        }

        matters.add(matter);
        matter.space(this);
    }

    public void remove(Matter matter) {
        matters.remove(matter);
    }

    public void create(Matter matter) {
        matters.clear();

        add(matter);
    }

    public void pulse() {
        forEach(matter -> anti.energy.transfer(matter.mass(), energy));
    }

    public void react() {
        forEach(information -> information.react());
    }

    public void move() {
        forEach(information -> information.move());
    }

    public void absorb() {
        energy.sync();
    }

    public void emit() {
        var total = energy();
        if (total != 0) {
            Direction.forEach(direction -> {
                energy.transfer(total * direction.coef(), adjacents[direction.ordinal()].energy);
            });
        }
    }

    public double energy() {
        return energy.amount();
    }

    public double mass() {
        return matters.stream().map(i -> i.mass()).reduce(0.0, Double::sum);
    }

    public void forEach(Consumer<Matter> consumer) {
        for (var information : matters.toArray(new Matter[0])) {
            consumer.accept(information);
        }
    }

    public double surroundingEnergy() {
        var result = new Object() {
            double energy;
        };

        result.energy = energy();
        Direction.forEach(direction -> {
            result.energy += adjacent(direction).energy();
        });

        return result.energy;
    }
}