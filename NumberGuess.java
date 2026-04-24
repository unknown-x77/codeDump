import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuess extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel sentence;
    private JTextField number;
    private JButton submit;
    private JButton high;
    private JButton low;
    private JButton correct;
    private int max = 25000;
    private int min = 0;
    private int guess = min + (max - min) / 2;
    private int WINDOW_WIDTH = 300;
    private int WINDOW_HEIGHT = 100;
    private JLabel sentence2;
    private JLabel sentence3;

    public NumberGuess() {
        setTitle("Number Guessing");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        add(panel1);

        setVisible(true);
    }

    private void buildPanel() {

        // Panel 1
        panel1 = new JPanel();
        sentence = new JLabel("Pick any whole number (0-25000)");
        submit = new JButton("Submit");
        number = new JTextField(15);
        submit.addActionListener(new ButtonListener());
        panel1.add(sentence);
        panel1.add(number);
        panel1.add(submit);

        // Panel 2
        panel2 = new JPanel();
        sentence2 = new JLabel("Is your number " + guess + "?");
        low = new JButton("Lower");
        correct = new JButton("Correct");
        high = new JButton("Higher");
        low.addActionListener(new ButtonListener());
        correct.addActionListener(new ButtonListener());
        high.addActionListener(new ButtonListener());
        panel2.add(sentence2);
        panel2.add(low);
        panel2.add(correct);
        panel2.add(high);

        //Panel 3
        panel3 = new JPanel();
        sentence3 = new JLabel();
        panel3.add(sentence3);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clicked = (JButton) e.getSource();
            try {
                if (Integer.parseInt(number.getText()) > 25000 || Integer.parseInt(number.getText()) < 0) {
                    sentence.setText("Error. Pick a whole number 0-25000");
                } else if (clicked == submit) {

                    remove(panel1);
                    add(panel2);
                    repaint();
                    revalidate();
                } else {
                    if (clicked == low) {
                        max = guess;
                        guess = min + (max - min) / 2;
                        sentence2.setText("Is your number " + guess + "?");

                    } else if (clicked == high) {
                        min = guess;
                        guess = min + (max - min) / 2;
                        sentence2.setText("Is your number " + guess + "?");
                    } else {
                        sentence3.setText("Your number is " + guess + "!");
                        remove(panel2);
                        add(panel3);
                        repaint();
                        revalidate();
                    }
                }
            } catch (NumberFormatException ex) {
                sentence.setText("Invalid. Use a whole number 0-25000");
                revalidate();
            }
        }
    }

    public static void main(String[] args) {
        new NumberGuess();
    }


}
