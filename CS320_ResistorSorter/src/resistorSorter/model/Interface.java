package resistorSorter.model;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Interface extends JPanel {
        // constants defining the preferred width and height of the panel
        private static final int WIDTH = 1500;
        private static final int HEIGHT = 1000;

        // this font will be used to display the count
        private static final Font font = new Font("Dialog", Font.BOLD, 30);

        // as the count increases, the rectangle will cycle through these colors
        private static final Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };

        // field storing the current count
        private int count;

        // constructor
        public Interface() {
                count = 0;

                setBackground(Color.DARK_GRAY);

                setPreferredSize(new Dimension(WIDTH, HEIGHT));

                // install event handlers
                addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                handleMouseClick(e);
                        }
                });
        }

        private void handleMouseClick(MouseEvent e) {
                // change the "state" of the program
                count++;

                // now that the state is changed,
                // redraw the panel to reflect the state change
                repaint();
        }

        @Override
        public void paint(Graphics g) {
                super.paint(g); // call the superclass's paint() method to paint the background

                //Draw rectangle around parameters
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(10, 10, 620, HEIGHT-20);
                
                //Draw the Resistance, tolerance, and power rating
                g.setFont(font);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString("Resistance", 40, 50);
                g.drawString("Tolerance", 240, 50);
                g.drawString("Power Rating", 420, 50);
                
                //Draw lines dividing charts
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(220, 10, 220, HEIGHT-10);
                g.drawLine(400, 10, 400, HEIGHT-10);
        }
}
