import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.net.URL;

/**
 * MagicTools is a simple Java Swing application that provides a life counter,
 * commander tax counter, and dice roller for Magic: The Gathering players.
 * It allows users to increase or decrease their life total, manage commander tax,
 * and roll dice (D6 and D20) with the results displayed on the screen.
 */

public class MagicTools extends JFrame {

    // Variables for Life Counter
    private int lifeTotal = 40; // Default life total
    private JLabel lifeLabel; // Label to display life total

    // Variables for Commander Tax
    private int commanderTax = 0; // Default commander tax
    private JLabel commanderTaxLabel; // Label to display commander tax

    // Variables for Dice Roll
    private Random random = new Random(); // Random number generator
    private JLabel diceResultLabel; // Label to display dice roll result

    // Heads or Tails Icons
    private ImageIcon headsIcon = getScaledIcon("/cfimgs/cf_h.png", 100, 100);
    private ImageIcon tailsIcon = getScaledIcon("/cfimgs/cf_t.png", 100, 100);
    
    // Declare panels as instance variables
    private JPanel lifeCounterPanel;
    private JPanel southPanel;

    public MagicTools() {
        setTitle("Magic Tools");
        setSize(960, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setBackground(Color.decode("#F8F8F2")); // Off-white background

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F8F8F2"));
        
        // Left Panel (60%)
        lifeCounterPanel = new JPanel();
        lifeCounterPanel.setLayout(new BoxLayout(lifeCounterPanel, BoxLayout.Y_AXIS));
        lifeCounterPanel.setPreferredSize(new Dimension(560, 500));
        lifeCounterPanel.setBackground(Color.decode("#F8F8F2"));
        lifeCounterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title - Using the logo image
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.decode("#F8F8F2"));
        titlePanel.setMaximumSize(new Dimension(576, 100));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        ImageIcon titleIcon = getScaledIcon("/otherimgs/mt_logo.png", 500, 100);
        JLabel titleLabel = new JLabel(titleIcon);
        titlePanel.add(titleLabel);
        
        // Life Counter Panel
        JPanel lifePanel = new JPanel(new GridLayout(1, 3));
        lifePanel.setMaximumSize(new Dimension(576, 80));
        lifePanel.setBackground(Color.decode("#F8F8F2"));
        lifePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Life Label
        lifeLabel = new JLabel("Life: " + lifeTotal);
        lifeLabel.setFont(new Font("Serif", Font.BOLD, 46));
        lifeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lifeLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        // Increase Life Button
        JButton increaseLife = new JButton("+");
        increaseLife.setFont(new Font("Arial", Font.BOLD, 24));
        increaseLife.setFocusable(false);
        increaseLife.addActionListener(e -> updateLife(1));
        increaseLife.setBackground(new Color(220, 230, 240)); // Light blue button
        
        // Decrease Life Button
        JButton decreaseLife = new JButton("-");
        decreaseLife.setFont(new Font("Arial", Font.BOLD, 24));
        decreaseLife.setFocusable(false);
        decreaseLife.addActionListener(e -> updateLife(-1));
        decreaseLife.setBackground(new Color(220, 230, 240)); // Light blue button
        
        lifePanel.add(lifeLabel);
        lifePanel.add(increaseLife);
        lifePanel.add(decreaseLife);
        
        // Commander Tax Panel
        JPanel taxPanel = new JPanel(new GridLayout(1, 3));
        taxPanel.setMaximumSize(new Dimension(576, 50));
        taxPanel.setBackground(Color.decode("#F8F8F2"));
        taxPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Commander Tax Label
        commanderTaxLabel = new JLabel("CMD Tax: " + commanderTax);
        commanderTaxLabel.setFont(new Font("Serif", Font.BOLD, 24));
        commanderTaxLabel.setHorizontalAlignment(SwingConstants.LEFT);
        commanderTaxLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        // Increase Tax Button
        JButton increaseTax = new JButton("+ Tax");
        increaseTax.setFont(new Font("Arial", Font.BOLD, 16));
        increaseTax.setFocusable(false);
        increaseTax.addActionListener(e -> updateCommanderTax());
        increaseTax.setBackground(new Color(220, 230, 240)); // Light blue button
        
        // Reset Tax Button
        JButton resetTax = new JButton("Reset");
        resetTax.setFont(new Font("Arial", Font.BOLD, 16));
        resetTax.setFocusable(false);
        resetTax.addActionListener(e -> resetCommanderTax());
        resetTax.setBackground(new Color(220, 230, 240)); // Light blue button
        
        taxPanel.add(commanderTaxLabel);
        taxPanel.add(increaseTax);
        taxPanel.add(resetTax);
        
        // Dice Panel
        JPanel dicePanel = new JPanel(new GridLayout(1, 3, 10, 0));
        dicePanel.setMaximumSize(new Dimension(576, 100));
        dicePanel.setBackground(Color.decode("#F8F8F2"));
        dicePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // D6 Button
        JPanel d6Panel = new JPanel();
        d6Panel.setBackground(Color.BLACK);
        ImageIcon d6Icon = getScaledIcon("/d6imgs/d6_1.png", 80, 80);
        JButton d6Button = new JButton(d6Icon);
        d6Button.setPreferredSize(new Dimension(80, 80));
        d6Button.setBackground(Color.BLACK);
        d6Button.setFocusable(false);
        d6Button.setBorderPainted(false);
        d6Button.addActionListener(e -> rollD6WithAnimation(d6Button));
        d6Panel.add(d6Button);
        
        // D20 Button
        JPanel d20Panel = new JPanel();
        d20Panel.setBackground(Color.BLACK);
        ImageIcon d20Icon = getScaledIcon("/d20imgs/d20_1.png", 80, 80);
        JButton d20Button = new JButton(d20Icon);
        d20Button.setPreferredSize(new Dimension(80, 80));
        d20Button.setBackground(Color.BLACK);
        d20Button.setFocusable(false);
        d20Button.setBorderPainted(false);
        d20Button.addActionListener(e -> rollD20WithAnimation(d20Button));
        d20Panel.add(d20Button);
        
        // Coin Flip Button
        JPanel coinPanel = new JPanel();
        coinPanel.setBackground(Color.BLACK);
        JButton coinButton = new JButton(headsIcon);
        coinButton.setPreferredSize(new Dimension(80, 80));
        coinButton.setBackground(Color.BLACK);
        coinButton.setFocusable(false);
        coinButton.setBorderPainted(false);
        coinButton.addActionListener(e -> flipCoinWithAnimation(coinButton));
        coinPanel.add(coinButton);
        
        dicePanel.add(d6Panel);
        dicePanel.add(d20Panel);
        dicePanel.add(coinPanel);
        
        // Dice Result Label
        diceResultLabel = new JLabel("Roll!");
        diceResultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        diceResultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add spacing between components
        lifeCounterPanel.add(titlePanel);
        lifeCounterPanel.add(Box.createVerticalStrut(20));
        lifeCounterPanel.add(lifePanel);
        lifeCounterPanel.add(Box.createVerticalStrut(20));
        lifeCounterPanel.add(taxPanel);
        lifeCounterPanel.add(Box.createVerticalStrut(20));
        lifeCounterPanel.add(dicePanel);
        lifeCounterPanel.add(Box.createVerticalStrut(10));
        lifeCounterPanel.add(diceResultLabel);
        
        // Right Panel (40%) - Notepad
        JPanel notepadPanel = new JPanel(new BorderLayout());
        notepadPanel.setPreferredSize(new Dimension(384, 500));
        notepadPanel.setBackground(Color.decode("#F8F8F2"));
        notepadPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));
        
        JLabel notesLabel = new JLabel("Notes", SwingConstants.RIGHT);
        notesLabel.setFont(new Font("Serif", Font.BOLD, 24));
        notesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        notepadPanel.add(notesLabel, BorderLayout.NORTH);
        
        JTextArea notepad = new JTextArea();
        notepad.setLineWrap(true);
        notepad.setWrapStyleWord(true);
        notepad.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JScrollPane scrollPane = new JScrollPane(notepad);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        notepadPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom Panel
        southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.decode("#F8F8F2"));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Color theme buttons
        JPanel colorButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorButtonPanel.setBackground(Color.decode("#F8F8F2"));
        
        // Create Color Theme Buttons
        RoundButton whiteButton = new RoundButton("", Color.decode("#F8F8F2"));
        whiteButton.setPreferredSize(new Dimension(30, 30));
        RoundButton blueButton = new RoundButton("", Color.decode("#1E90FF"));
        blueButton.setPreferredSize(new Dimension(30, 30));
        RoundButton blackButton = new RoundButton("", Color.decode("#2C2C2C"));
        blackButton.setPreferredSize(new Dimension(30, 30));
        RoundButton redButton = new RoundButton("", Color.decode("#FF4500"));
        redButton.setPreferredSize(new Dimension(30, 30));
        RoundButton greenButton = new RoundButton("", Color.decode("#228B22"));
        greenButton.setPreferredSize(new Dimension(30, 30));
        
        // Add action listeners
        whiteButton.addActionListener(e -> setTheme("White"));
        blueButton.addActionListener(e -> setTheme("Blue"));
        blackButton.addActionListener(e -> setTheme("Black"));
        redButton.addActionListener(e -> setTheme("Red"));
        greenButton.addActionListener(e -> setTheme("Green"));
        
        // Add buttons to panel
        colorButtonPanel.add(whiteButton);
        colorButtonPanel.add(blueButton);
        colorButtonPanel.add(blackButton);
        colorButtonPanel.add(redButton);
        colorButtonPanel.add(greenButton);
        
        // Phase Label
        JPanel phaseLabelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        phaseLabelPanel.setBackground(Color.decode("#F8F8F2"));
        JLabel phaseLabel = new JLabel("Untap → Upkeep → Draw → Main → Combat → Main → End");
        phaseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        phaseLabelPanel.add(phaseLabel);
        
        southPanel.add(colorButtonPanel, BorderLayout.WEST);
        southPanel.add(phaseLabelPanel, BorderLayout.EAST);
        
        // Add vertical divider between left and right panels
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(1, 500));
        separator.setForeground(Color.BLACK);
        
        JPanel separatorPanel = new JPanel(new BorderLayout());
        separatorPanel.add(separator, BorderLayout.CENTER);
        
        // Add all panels to the main layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(lifeCounterPanel, BorderLayout.WEST);
        topPanel.add(separatorPanel, BorderLayout.CENTER);
        topPanel.add(notepadPanel, BorderLayout.EAST);
        
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        setVisible(true);
    }
    
    // Method to update life total
    private void updateLife(int change) {
        lifeTotal += change;
        lifeLabel.setText("Life: " + lifeTotal);
    }
    
    // Method to update commander tax
    private void updateCommanderTax() {
        commanderTax += 2;
        commanderTaxLabel.setText("CMD Tax: " + commanderTax);
    }
    
    // Method to reset commander tax
    private void resetCommanderTax() {
        commanderTax = 0;
        commanderTaxLabel.setText("CMD Tax: " + commanderTax);
    }
    
    private ImageIcon getScaledIcon(String path, int width, int height) {
        URL resource = getClass().getResource(path);
        if (resource == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(resource);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    // Method to roll a D6 with animation
    private void rollD6WithAnimation(JButton d6Button) {
        Timer timer = new Timer(100, null);
        final int[] counter = {0};
        final int rollDuration = 2000;
        final long startTime = System.currentTimeMillis();
        
        ImageIcon[] d6Faces = {
            getScaledIcon("/d6imgs/d6_1.png", 80, 80),
            getScaledIcon("/d6imgs/d6_2.png", 80, 80),
            getScaledIcon("/d6imgs/d6_3.png", 80, 80),
            getScaledIcon("/d6imgs/d6_4.png", 80, 80),
            getScaledIcon("/d6imgs/d6_5.png", 80, 80),
            getScaledIcon("/d6imgs/d6_6.png", 80, 80)
        };
        
        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            if (elapsedTime >= rollDuration) {
                timer.stop();
                int finalResult = random.nextInt(6);
                d6Button.setIcon(d6Faces[finalResult]);
                diceResultLabel.setText("You rolled a " + (finalResult + 1) + "!");
            } else {
                d6Button.setIcon(d6Faces[counter[0]]);
                counter[0] = (counter[0] + 1) % 6;
            }
        });
        
        timer.start();
    }
    
    // Method to roll a D20 with animation
    private void rollD20WithAnimation(JButton d20Button) {
        Timer timer = new Timer(50, null);
        final int[] counter = {0};
        final int rollDuration = 2000;
        final long startTime = System.currentTimeMillis();
        
        ImageIcon[] d20Faces = {
            getScaledIcon("/d20imgs/d20_1.png", 80, 80),
            getScaledIcon("/d20imgs/d20_2.png", 80, 80),
            getScaledIcon("/d20imgs/d20_3.png", 80, 80),
            getScaledIcon("/d20imgs/d20_4.png", 80, 80),
            getScaledIcon("/d20imgs/d20_5.png", 80, 80),
            getScaledIcon("/d20imgs/d20_6.png", 80, 80),
            getScaledIcon("/d20imgs/d20_7.png", 80, 80),
            getScaledIcon("/d20imgs/d20_8.png", 80, 80),
            getScaledIcon("/d20imgs/d20_9.png", 80, 80),
            getScaledIcon("/d20imgs/d20_10.png", 80, 80),
            getScaledIcon("/d20imgs/d20_11.png", 80, 80),
            getScaledIcon("/d20imgs/d20_12.png", 80, 80),
            getScaledIcon("/d20imgs/d20_13.png", 80, 80),
            getScaledIcon("/d20imgs/d20_14.png", 80, 80),
            getScaledIcon("/d20imgs/d20_15.png", 80, 80),
            getScaledIcon("/d20imgs/d20_16.png", 80, 80),
            getScaledIcon("/d20imgs/d20_17.png", 80, 80),
            getScaledIcon("/d20imgs/d20_18.png", 80, 80),
            getScaledIcon("/d20imgs/d20_19.png", 80, 80),
            getScaledIcon("/d20imgs/d20_20.png", 80, 80),
        };
        
        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            if (elapsedTime >= rollDuration) {
                timer.stop();
                int finalResult = random.nextInt(20);
                d20Button.setIcon(d20Faces[finalResult]);
                diceResultLabel.setText("You rolled a " + (finalResult + 1) + "!");
            } else {
                d20Button.setIcon(d20Faces[counter[0]]);
                counter[0] = (counter[0] + 1) % 20;
            }
        });
        
        timer.start();
    }
    
    private void flipCoinWithAnimation(JButton coinButton) {
        Timer timer = new Timer(100, null);
        final int rollDuration = 2000;
        final long startTime = System.currentTimeMillis();
        
        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            
            if (elapsedTime >= rollDuration) {
                timer.stop();
                boolean isHeads = random.nextBoolean();
                coinButton.setIcon(isHeads ? headsIcon : tailsIcon);
                diceResultLabel.setText("You flipped " + (isHeads ? "Heads" : "Tails") + "!");
            } else {
                coinButton.setIcon(coinButton.getIcon() == headsIcon ? tailsIcon : headsIcon);
            }
        });
        
        timer.start();
    }
    
    // Method to set the color theme of the application
    private void setTheme(String color) {
        Color backgroundColor;
        Color textColor;
        
        switch (color) {
            case "White":
                backgroundColor = Color.decode("#F8F8F2");
                textColor = Color.decode("#282A36");
                break;
            case "Blue":
                backgroundColor = Color.decode("#1E90FF");
                textColor = Color.decode("#FFFFFF");
                break;
            case "Black":
                backgroundColor = Color.decode("#2C2C2C");
                textColor = Color.decode("#E6E6E6");
                break;
            case "Red":
                backgroundColor = Color.decode("#FF4500");
                textColor = Color.decode("#FFFFFF");
                break;
            case "Green":
                backgroundColor = Color.decode("#228B22");
                textColor = Color.decode("#FFFFFF");
                break;
            default:
                return;
        }
        
        // Update component colors
        getContentPane().setBackground(backgroundColor);
        
        // Update components with background color
        updateComponentsColors(this, backgroundColor, textColor);
        
        // Force repaint
        repaint();
    }
    
    // Helper method to update colors of all components
    private void updateComponentsColors(Container container, Color bgColor, Color fgColor) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel) {
                component.setForeground(fgColor);
            }
            if (component instanceof JPanel && !(component.getBackground().equals(Color.BLACK))) {
                component.setBackground(bgColor);
            }
            if (component instanceof Container) {
                updateComponentsColors((Container) component, bgColor, fgColor);
            }
        }
    }
    
    // Inner class for round buttons
    class RoundButton extends JButton {
        private Color buttonColor;
        
        public RoundButton(String text, Color color) {
            super(text);
            this.buttonColor = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) {
                g2.setColor(buttonColor.darker());
            } else {
                g2.setColor(buttonColor);
            }
            
            g2.fillOval(0, 0, getWidth(), getHeight());
            g2.setColor(Color.BLACK);
            g2.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            
            super.paintComponent(g);
            g2.dispose();
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }
        
        @Override
        public boolean contains(int x, int y) {
            int radius = Math.min(getWidth(), getHeight()) / 2;
            return Math.pow(x - getWidth() / 2, 2) + Math.pow(y - getHeight() / 2, 2) <= Math.pow(radius, 2);
        }
    }
    
    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MagicTools();
        });
    }
}