import javax.swing.*;
import java.awt.*;

public class LoginUi extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginUi() {
        setTitle("EHR App Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Set background color for the whole frame content pane
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Use a BoxLayout vertical for main content
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // padding

        // Title label
        JLabel titleLabel = new JLabel("WELCOME TO THE EHS APP");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.DARK_GRAY);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // spacer

        // Username field
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        usernameField.setToolTipText("Username");
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameField);

        panel.add(Box.createRigidArea(new Dimension(0, 15))); // spacer

        // Password field
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setToolTipText("Password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        panel.add(Box.createRigidArea(new Dimension(0, 25))); // spacer

        // Login button
        loginButton = new JButton("login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(90, 120, 255)); // blue button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setMaximumSize(new Dimension(100, 30));
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(loginButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginUi ui = new LoginUi();
            ui.setVisible(true);
        });
    }
}
