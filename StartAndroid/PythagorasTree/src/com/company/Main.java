package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static java.lang.Math.*;

public class Main {
    static final int depthLimit = 2;
    static float hue = 0.15f;
    static final double ALPHA = 45;

    public static void main(String[] args) {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());
    }

    static class MyComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            drawTree((Graphics2D) g, 450, 500, 550, 500, 0);

        }
    }

    private static void drawTree(Graphics2D g, double x1, double y1, double x2, double y2, int depth) {

        if (depth == depthLimit)
            return;


        double dx2 = (x2 - x1);
        double dy2 = (y2 - y1);

        double x3 = x2 - dy2;
        double y3 = y2 - dx2;
        double x4 = x1 - dy2;
        double y4 = y1 - dx2;
        double cs = cos(toRadians(ALPHA));
        double sn = sin(toRadians(ALPHA));

        double dx = (x3 - x4) * sin(toRadians(ALPHA));
        double dy = (y3 - y4) * cos(toRadians(ALPHA));

        double x5 = x4 + dx * cs + dy * sn;
        double y5 = y3 - dx * sn + dy * cs;


        Line2D line2D = new Line2D.Double(x1, y1, x2, y2);
        Line2D line2D1 = new Line2D.Double(x2, y2, x3, y3);
        Line2D line2D2 = new Line2D.Double(x3, y3, x4, y4);
        Line2D line2D3 = new Line2D.Double(x4, y4, x1, y1);

        g.setColor(Color.lightGray);
        g.draw(line2D);
        g.draw(line2D1);
        g.draw(line2D2);
        g.draw(line2D3);


        Line2D line2D5 = new Line2D.Double(x4, y4, x5, y5);
        Line2D line2D6 = new Line2D.Double(x5, y5, x3, y3);

        g.setColor(Color.lightGray);
        g.draw(line2D5);
        g.draw(line2D6);

        drawTree(g, x4, y4, x5, y5, depth + 1);
        drawTree(g, x5, y5, x3, y3, depth + 1);
    }


    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 350, 1000, 700);
        return jFrame;
    }
}
