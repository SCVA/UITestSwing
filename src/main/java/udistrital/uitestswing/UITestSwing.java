/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package udistrital.uitestswing;

/**
 *
 * @author PC
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Simple Swing application that shows a login screen.
 */
public class UITestSwing {

    private UITestSwing() {
        // utility class
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UITestSwing::createAndShow);
    }

    private static void createAndShow() {
        setSystemLookAndFeel();

        JFrame frame = new JFrame("Login Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Welcome", JLabel.CENTER);
        headerLabel.setFont(headerLabel.getFont().deriveFont(Font.BOLD, 24f));
        headerLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 10, 10));
        frame.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel(" ");
        messageLabel.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(messageLabel, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(event -> {
            String username = userField.getText().trim();
            char[] password = passField.getPassword();
            try {
                if (isAuthenticated(username, password)) {
                    messageLabel.setForeground(new Color(0, 128, 0));
                    messageLabel.setText("Login successful!");
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Invalid username or password.");
                }
            } finally {
                // Clear password array for security
                java.util.Arrays.fill(password, '\0');
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            // If we cannot set the system L&F, just continue with defaults.
        }
    }

    private static boolean isAuthenticated(String username, char[] password) {
        return Objects.equals("admin", username) && java.util.Arrays.equals("password".toCharArray(), password);
    }
}