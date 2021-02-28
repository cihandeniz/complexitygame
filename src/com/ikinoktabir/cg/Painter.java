package com.ikinoktabir.cg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Painter extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int FRAME_REFRESH_RATE = 1000 / 25;

    public static final int PIXELS = 800;
    public static final int SPACES = 200;
    public static final int RADIUS = PIXELS / SPACES;

    public final Universe universe;
    public final Timer repaintTimer;
    public final List<Monitor> monitors;

    public Painter() {
        super();

        monitors = new ArrayList<Monitor>() {
            private static final long serialVersionUID = 1L;

            {
                //add(new Monitor.Energy());
                //add(new Monitor.AntiEnergy());
                add(new Monitor.Matter());
            }
        };

        universe = new Universe(SPACES);
        universe.build();
        universe.start();

        repaintTimer = new Timer(FRAME_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        repaintTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, PIXELS, PIXELS);

        universe.forEach(x -> y -> space -> {
            for (var monitor : monitors) {
                monitor.render(g, space, x, y);
            }
        });
    }
}