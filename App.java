import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends Calculation{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setVisible(true);
        frame.setBounds(400, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", ".", "+",
                "sqrt", "mod", "="
        };

        int row = 0;
        int col = 0;
        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.setBackground(Color.decode("#f0f0f0"));
            if (buttonText.equals("=")) {
                button.setBackground(Color.lightGray);
                button.setForeground(Color.black);
                gbc.gridwidth = 2;
            } else {
                gbc.gridwidth = 1;
            }
            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(button, gbc);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        frame.add(panel);
    }
}
