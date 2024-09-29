import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame() {
        // Create components
        JTextField textField = new JTextField();
        JButton button = new JButton("Submit");
        JLabel resultLabel = new JLabel("Result will be displayed here.");

        // Set layout and add components
        this.setLayout(null);
        textField.setBounds(50, 50, 200, 30);
        button.setBounds(50, 100, 100, 30);
        resultLabel.setBounds(50, 150, 300, 30);

        this.add(textField);
        this.add(button);
        this.add(resultLabel);

        // Set default close operation and size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse the user input from the text field
                    String userInput = textField.getText();
                    int value = Integer.parseInt(userInput);

                    // Use the parsed value (for example, double it)
                    int result = value * 2;

                    // Display the result in the label
                    resultLabel.setText("Result: " + result);
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid integer
                    resultLabel.setText("Please enter a valid integer.");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Create and display the frame
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}
