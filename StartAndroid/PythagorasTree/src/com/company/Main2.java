package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main2 {

    private static int depthLimit;
    private static double ALPHA;
    private static double cos;
    private static double sin;

    public static void main(String[] args) {
        System.out.print("Введите угол ALPHA: ");
        Scanner scanner = new Scanner(System.in);
        ALPHA = toRadians(scanner.nextDouble());
        cos = cos(toRadians(ALPHA));
        sin = sin(toRadians(ALPHA));

        System.out.print("Введите количество интераций: ");
        depthLimit = scanner.nextInt();

        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());
    }

    public static class MyComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            drawTree((Graphics2D) g, 450, 500, 0, 0, 100);
        }
    }

    private static void drawTree(Graphics2D g, double x, double y, int depth, double fi, double side) {

        if (depth == depthLimit)
            return;

        double dx = side * sin(fi); //0
        double dy = side * cos(fi); //100

        double x1 = x + dy;
        double y1 = y - dx;

        double x2 = x - dx + dy;
        double y2 = y - dy - dx;

        double x3 = x - dx;
        double y3 = y - dy;

        double x4 = x - dx - (side * sin(ALPHA) * sin(fi - ALPHA));
        double y4 = y - dy - (side * sin(ALPHA) * cos(fi - ALPHA));


        Line2D line2D = new Line2D.Double(x, y, x1, y1);
        Line2D line2D1 = new Line2D.Double(x1, y1, x2, y2);
        Line2D line2D2 = new Line2D.Double(x2, y2, x3, y3);
        Line2D line2D3 = new Line2D.Double(x3, y3, x, y);

        g.setColor(Color.getHSBColor((float) random() + depth * 0.02f, 1, 1));
        g.draw(line2D);
        g.draw(line2D1);
        g.draw(line2D2);
        g.draw(line2D3);


        drawTree(g, x4, y4,depth + 1, fi - ALPHA, side * cos(ALPHA));
        drawTree(g, x3, y3,depth + 1, fi - ALPHA + PI / 2, side * sin(ALPHA));
    }


    private static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 500, dimension.height / 2 - 350, 1000, 700);
        return jFrame;
    }
}
