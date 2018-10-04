package com.company;

import javax.swing.*;
import java.awt.*;

public class Main2 {

    public static void main(String[] args) {

        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());
    }


    public static class MyComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            drawLineCustom(100, 120, 200, 90, g);
        }
    }

    private static void drawLineCustom(int x1, int y1, int x2, int y2, Graphics g) {

        int dx = (x2 - x1 >= 0 ? 1 : -1);
        int dy = (y2 - y1 >= 0 ? 1 : -1);

        int lenX = Math.abs(x2 - x1);
        int lenY = Math.abs(y2 - y1);

        int length = Math.max(lenX, lenY);

        if (length == 0)
        {
            g.drawRect(x1, y1, 1, 1);
            return;
        }

        float anime = 0;

        if (lenY <= lenX) {

            int x = x1;
            int y = y1;

            for (int i = 0; i <= length; i++) {
                g.drawRect(x, y, 1, 1);
                x += dx;
                anime += lenY;
                if (2 * anime >= lenX) {
                    y += dy;
                    anime -= lenX;
                }
            }

        } else {
            float x = x1;
            int y = y1;

            for (int i = 0; i <= length; i++) {
                g.drawRect((int) Math.ceil(x), y, 1, 1);
                y += dy;
                anime += lenX;
                if (2 * anime >= lenY) {
                    x += dx;
                    anime -= lenY;
                }
            }

        }

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

