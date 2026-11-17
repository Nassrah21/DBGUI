package GUI;

import javax.swing.*;

import Tables.USER;

import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel container = new JPanel(cardLayout);

    private LoginPanel loginPanel;
    private ChatsPanel chatsPanel;

    private USER currentUser;

    public MainFrame() {
        setTitle("LINE");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(this);
        chatsPanel = new ChatsPanel(this);

        container.add("login", loginPanel); 
        container.add(chatsPanel, "chats");

        add(container);
        cardLayout.show(container, "login");
    }

    public void login(USER user) {
        currentUser = user;
        chatsPanel.loadChats(user);
        cardLayout.show(container, "chats");
    }

    public void showScreen(String name) {
        cardLayout.show(container, name);
    }

    public USER getCurrentUser() {
        return currentUser;
    }
}
