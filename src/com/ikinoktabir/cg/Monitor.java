package com.ikinoktabir.cg;

import java.awt.Color;
import java.awt.Graphics;

import static com.ikinoktabir.cg.Painter.*;

public interface Monitor {
    void render(Graphics g, Space space, int x, int y);

    class Energy implements Monitor {
        @Override
        public void render(Graphics g, Space space, int x, int y) {
            int energy = (int) (space.energy() / 100);

            if (energy <= 1) {
                return;
            }

            int red = 0, green = 0, blue = 0;

            red = (int) (energy > 255 ? 255 : energy);
            energy -= red;
            green = (int) (energy > 255 ? 255 : energy);
            energy -= green;
            blue = (int) (energy > 255 ? 255 : energy);
            energy -= blue;

            g.setColor(new Color(red, green, blue));
            g.fillRect(x * RADIUS, y * RADIUS, RADIUS, RADIUS);
        }

    }

    class AntiEnergy implements Monitor {
        @Override
        public void render(Graphics g, Space space, int x, int y) {
            int energy = -(int) (space.energy() / 100);

            if (energy <= 1) {
                return;
            }

            int red = 0, green = 0, blue = 0;

            blue = (int) (energy > 255 ? 255 : energy);
            energy -= blue;
            green = (int) (energy > 255 ? 255 : energy);
            energy -= green;
            red = (int) (energy > 255 ? 255 : energy);
            energy -= red;

            g.setColor(new Color(red, green, blue));
            g.fillRect(x * RADIUS, y * RADIUS, RADIUS, RADIUS);
        }
    }

    class Matter implements Monitor {
        @Override
        public void render(Graphics gx, Space space, int x, int y) {
            space.forEach(matter -> {
                var mass = matter.mass();
    
                double divider = 100;
                int r = 50, g = 50, b = 255;
                if(mass <= 100) {
                    r = g = b = 255;

                    divider = 0.25;
                } else if(mass >= 50000) {
                    r = b = 255;
                    g = 0;

                    divider = 1000;
                }
    
                final var max = RADIUS * 2.0;
                final var min = RADIUS / 4.0;
    
                int radius = (int) Math.max(min, Math.min(max, mass / divider));
                int alpha = Math.min(255, (int) ((radius / (max - min)) * 255));
    
                gx.setColor(new Color(r, g, b, alpha));
                gx.fillOval(x * RADIUS + RADIUS - radius, y * RADIUS + RADIUS - radius, radius, radius);
            });
        }
    }
}
