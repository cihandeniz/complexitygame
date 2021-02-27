package com.ikinoktabir.cg;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Application extends JFrame {
    private static final long serialVersionUID = 1L;

    public Application() {
        initUI(Painter.PIXELS);

        var painter = new Painter();

        add(painter);
        
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                painter.repaintTimer.stop();
                painter.universe.stop();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

        });
    }

    private void initUI(int size) {
        setSize(size, size+20);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);
        });
    }
}