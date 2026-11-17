package Tables;
import java.time.LocalDate;
import java.util.LinkedList;

import Enum.Chat_Type;

public class CHAT {
    private int Chat_id;
    private Chat_Type chat_type;
    private LocalDate Created_Date;
    private LinkedList<MESSAGE> messages;

    public CHAT(int chat_id, Chat_Type chat_type, LocalDate created_Date) {
        this.Chat_id = chat_id;
        setChat_type(chat_type);
        this.Created_Date = created_Date;
        this.messages = new LinkedList<>();
    }
        
    

    public CHAT() {
        chat_type = Chat_Type.Private; // Default chat type
        this.Created_Date = LocalDate.now(); // Default to current date and time
        this.messages = new LinkedList<>();
    }

    public int getChat_id() {
        return Chat_id;
    }

    public void setChat_id(int chat_id) {
        Chat_id = chat_id;
    }

    public Chat_Type getChat_type() {
        return chat_type;
    }

    public void setChat_type(Chat_Type chat_type) {
        if (chat_type == Chat_Type.Public || chat_type == Chat_Type.Private) {
            this.chat_type = chat_type;
        } else {
            throw new IllegalArgumentException("Invalid chat type. Must be PUBLIC or PRIVATE.");
        }
    }

    public LocalDate getCreated_Date() {
        return Created_Date;
    }

    public void setCreated_Date(LocalDate created_Date) {
        Created_Date = created_Date;
    }
    
    public String toString() {
    return "CHAT{" +
           "Chat_id=" + Chat_id +
           ", chat_type=" + chat_type +
           ", Created_Date=" + Created_Date +
           "}";
}


}
