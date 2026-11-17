import DAO.Database;
import Enum.MSG_Type;
import Enum.User_type;
import Tables.CHAT;
import Tables.MESSAGE;
import Tables.USER;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import GUI.MainFrame;
public class Main {

    public static LinkedList<USER> users;
    public static LinkedList<CHAT> chats;
    public static LinkedList<MESSAGE> messages;
    public static void main(String[] args) {

        new MainFrame().setVisible(true);


        /* 
        Database database = new Database();
        Scanner input = new Scanner(System.in);

        users = database.users.selectAll();
        USER user = null;

        System.out.println("1.sign in\n2.log in");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                // sign in (inser user)
                System.out.println("username: ");
                String Susername = input.next();
                System.out.println("display name:");
                String display_name = input.next();
                System.out.println("phone number:");
                String phone = input.next();
                input.nextLine();
                System.out.print("birthdate, year:");
                int year = Integer.parseInt(input.nextLine());
                System.out.print("birthdate, month:");
                int month = Integer.parseInt(input.nextLine());
                System.out.print("birthdate, day:");
                int day = Integer.parseInt(input.nextLine());
                System.out.print("Year: ");
                LocalDate birthDate = LocalDate.of(year, month, day);
                System.out.println("password:");
                String Spassword = input.next();
                System.out.println("bio:");
                input.nextLine();
                String bio = input.nextLine();
                user = new USER(1, Susername, display_name, phone, null, Spassword, User_type.Standard, bio, null);
                if (database.users.insert(user))
                    System.out.println("succuss!");
                else
                    System.out.println("error");
                break;
            case 2:
                System.out.println("username:");
                String Lusername = input.next();
                System.out.println("password:");
                String Lpassword = input.next();
                user = null;
                for(USER u: users){
                    user = u.validation(Lusername, Lpassword);
                    if(user != null)
                        break;
                }
                if(user != null)
                    System.out.println("succuss");
                else
                    System.out.println("failed");
            default:
                break;
        }

        for(CHAT c: database.chats.selectAll())
            System.out.println(c);

        input.nextLine();
        /* 
        String msg1 = input.nextLine();
        String msg2 = input.nextLine();
        MESSAGE msg = new MESSAGE(1, MSG_Type.TEXT, msg1, null, null, database.chats.selectByChatID(201), user);
        database.messages.insert(msg);
        msg = new MESSAGE(2, MSG_Type.TEXT, msg2, null, null, database.chats.selectByChatID(201), user);
        database.messages.insert(msg);

        for(MESSAGE m : database.messages.retrievePerChat(201))
            System.out.println(m);

        database.joins.delete(201, 1);*/

    }
}