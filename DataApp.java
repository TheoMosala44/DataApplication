package dataapp;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataApp extends JFrame {

    private JTextField nameField, emailField, phoneField;
    private JPasswordField passwordField;
    private JLabel nameError, emailError, phoneError, passwordError;
    private JButton submitButton;

    public DataApp() {
        // Set up the frame
        setTitle("DataApp - Input Validation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        nameError = new JLabel();
        nameError.setForeground(Color.RED);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailError = new JLabel();
        emailError.setForeground(Color.RED);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField();
        phoneError = new JLabel();
        phoneError.setForeground(Color.RED);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordError = new JLabel();
        passwordError.setForeground(Color.RED);

        submitButton = new JButton("Submit");

        // Add components to the frame with layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(nameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(nameError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(emailField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(emailError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(phoneField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(phoneError, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passwordField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        add(passwordError, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(submitButton, gbc);

        // Add listeners for real-time validation
        nameField.getDocument().addDocumentListener(new ValidationListener());
        emailField.getDocument().addDocumentListener(new ValidationListener());
        phoneField.getDocument().addDocumentListener(new ValidationListener());
        passwordField.getDocument().addDocumentListener(new ValidationListener());

        submitButton.addActionListener(new SubmitButtonListener());

        // Show the frame
        setVisible(true);
    }

    private class ValidationListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validateForm();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validateForm();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validateForm();
        }

        private void validateForm() {
            validateName();
            validateEmail();
            validatePhone();
            validatePassword();
        }

        private void validateName() {
            String name = nameField.getText();
            if (name.length() < 3) {
                nameError.setText("Name must be at least 3 characters.");
            } else {
                nameError.setText("");
            }
        }

        private void validateEmail() {
            String email = emailField.getText();
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                emailError.setText("Invalid email format.");
            } else {
                emailError.setText("");
            }
        }

        private void validatePhone() {
            String phone = phoneField.getText();
            if (!phone.matches("\\d{10}")) {
                phoneError.setText("Phone number must be 10 digits.");
            } else {
                phoneError.setText("");
            }
        }

        private void validatePassword() {
            String password = new String(passwordField.getPassword());
            if (!password.matches("^(?=.[a-z])(?=.[A-Z])(?=.*\\d).{8,}$")) {
                passwordError.setText("Password must be 8 characters, include uppercase, lowercase, and a digit.");
            } else {
                passwordError.setText("");
            }
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameError.getText().isEmpty() && emailError.getText().isEmpty() &&
                phoneError.getText().isEmpty() && passwordError.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Form submitted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please fix the errors before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new DataApp());
    }
}