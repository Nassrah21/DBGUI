package GUI;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.Database;
import Tables.USER;

public class LoginPanel extends JPanel{
     public LoginPanel(MainFrame frame) {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(200, 40, 200, 40));

        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JButton loginButton = new JButton("Login");
        

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(e -> {
            try {
                String Lusername = usernameField.getText();
                String Lpassword = passwordField.getText();

                USER user = Database.users.valid(Lusername, Lpassword);
                if (user != null) {
                    frame.login(user);
                } else {
                    JOptionPane.showMessageDialog(this, "User not found");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a number");
            }
        });
    }
}
