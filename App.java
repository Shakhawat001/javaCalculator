import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    private JTextField display;
    private StringBuilder input;

    public static void main(String[] args) {
        new App().createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        display = new JTextField();
        display.setBounds(50, 40, 270, 50);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display);

        input = new StringBuilder();

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", ".", "+",
                "sqrt", "mod", "="
        };

        int x = 50;
        int y = 100;
        int width = 60;
        int height = 40;

        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons[i]);
            button.setBounds(x, y, width, height);
            button.setBackground(Color.decode("#f0f0f0"));
            button.addActionListener(new ButtonClickListener());
            frame.add(button);

            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 50;
                y += 50;
            }
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private String operator = "";
        private double num1 = 0;
        private boolean startNewNumber = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == 'C') {
                display.setText("");
                input.setLength(0);
                operator = "";
                num1 = 0;
                startNewNumber = true;
            } else if (command.charAt(0) == '=') {
                try {
                    String expression = input.toString();
                    double result = evaluateExpression(expression);
                    display.setText(expression + " = " + result);
                    input.setLength(0);
                    input.append(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    input.setLength(0);
                }
                startNewNumber = true;
            } else {
                if ("+-*/sqrtmod".contains(command)) {
                    if (startNewNumber && input.length() > 0) {
                        input.append(" ").append(command).append(" ");
                        display.setText(input.toString());
                        startNewNumber = false;
                    }
                } else {
                    if (startNewNumber) {
                        input.append(command);
                        display.setText(input.toString());
                        startNewNumber = false;
                    } else {
                        input.append(command);
                        display.setText(input.toString());
                    }
                }
            }
        }

        private double evaluateExpression(String expression) {
            String[] tokens = expression.split(" ");
            double result = Double.parseDouble(tokens[0]);

            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                double num = Double.parseDouble(tokens[i + 1]);

                switch (operator) {
                    case "+":
                        result += num;
                        break;
                    case "-":
                        result -= num;
                        break;
                    case "*":
                        result *= num;
                        break;
                    case "/":
                        result /= num;
                        break;
                    case "sqrt":
                        result = Math.sqrt(result);
                        break;
                    case "mod":
                        result %= num;
                        break;
                }
            }

            return result;
        }
    }
}
