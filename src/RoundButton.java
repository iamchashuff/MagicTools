import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    private static final int SIZE = 10; // Default size of the button

    public RoundButton(String text, Color bgColor) {
        super(text);
        setContentAreaFilled(false); // Prevents default button fill
        setFocusPainted(false); // Prevents focus border
        setBorderPainted(false); // Removes border
        setForeground(bgColor); // Default text color
        setBackground(bgColor); // Default button background color
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillOval(0, 0, getWidth(), getHeight()); // Draws the button as a circle
        super.paintComponent(g); // Calls the parent class to paint the text
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getBackground().darker()); // Darker border color
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Draws the border as a circle
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE); // Sets the preferred size of the button
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize(); // Sets the minimum size of the button
    }
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize(); // Sets the maximum size of the button
    }
    
}
