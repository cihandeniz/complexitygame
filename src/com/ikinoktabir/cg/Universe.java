package com.ikinoktabir.cg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.Timer;

public class Universe {
    private final Space[][] spaces;

    public Universe(int size) {
        spaces = new Space[size][size];
    }

    public void build() {
        for (int x = 0; x < spaces.length; x++) {
            for (int y = 0; y < spaces[x].length; y++) {
                spaces[x][y] = new Space();
            }
        }

        forEach(x -> y -> space -> {
            int antiX = (x + spaces.length / 2) % spaces.length;
            int antiY = (y + spaces.length / 2) % spaces.length;
            space.anti(spaces[antiX][antiY]);

            for (Direction direction : Direction.values()) {
                int aX = (x + direction.offsetX() + spaces.length) % spaces.length;
                int aY = (y + direction.offsetY() + spaces.length) % spaces.length;

                spaces[x][y].adjacent(direction, spaces[aX][aY]);
            }
        });
    }

    public void forEach(Consumer<Space> consumer) {
        forEach(x -> y -> consumer);
    }

    public void forEach(Function<Integer, Function<Integer, Consumer<Space>>> consumer) {
        for (int x = 0; x < spaces.length; x++) {
            for (int y = 0; y < spaces[x].length; y++) {
                consumer.apply(x).apply(y).accept(spaces[x][y]);
            }
        }
    }

    private Timer emitTimer;

    public void start() {
        for (int i = 0; i < 250; i++) {
            spaces[around(center(), 1)][around(center(), 1)].add(new Atom());
        }

        emitTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forEach((Space space) -> space.pulse());
                forEach((Space space) -> space.emit());
                forEach((Space space) -> space.absorb());
                forEach((Space space) -> space.react());
                forEach((Space space) -> space.move());
            }
        });

        emitTimer.start();
    }

    private int around(int value, int precision) {
        return ((int) (Math.random() * spaces.length / precision - spaces.length / (2 * precision) + value)
                + spaces.length) % spaces.length;
    }

    private int center() {
        return center(0);
    }

    private int center(double distance) {
        return (int) ((spaces.length / 2) + (spaces.length / 2) * distance);
    }

    public void stop() {
        emitTimer.stop();
    }
}