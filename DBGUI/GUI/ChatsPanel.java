package GUI;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import DAO.Database;
import Tables.CHAT;
import Tables.USER;

public class ChatsPanel extends JPanel{

    private DefaultListModel<CHAT> listModel = new DefaultListModel<>();
    private JList<CHAT> chatList = new JList<>(listModel);

    public ChatsPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        add(new JLabel("Chats", SwingConstants.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(chatList), BorderLayout.CENTER);
    }

    public void loadChats(USER user) {
        listModel.clear();
        for (CHAT c : Database.chats.selectById(user.getUser_id())) {
            listModel.addElement(c);
        }
    }
}
