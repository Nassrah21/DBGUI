package Tables;
import java.time.LocalDateTime;

import Enum.MSG_Type;

public class MESSAGE {
    private int Message_id;
    private MSG_Type message_type;
    private String Content;
    private LocalDateTime Sent_Time;
    private LocalDateTime Read_Time;
    private CHAT chat;
    private USER sender;

    public MESSAGE(int message_id, MSG_Type message_type, String content,
                   LocalDateTime sent_Time, LocalDateTime read_Time,
                   CHAT chat, USER sender) {
        
        this.Message_id = message_id;
        try{
        setMessage_type(message_type);}
        catch(IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
        this.Content = content;
        this.Sent_Time = sent_Time;
        this.Read_Time = read_Time;
        this.chat = chat;
        this.sender = sender;
    }

    public int getMessage_id() {
        return Message_id;
    }

    public void setMessage_id(int message_id) {
        Message_id = message_id;
    }

    public MSG_Type getMessage_type() {
        return message_type;
    }

    public void setMessage_type(MSG_Type message_type) {
        if (message_type == MSG_Type.TEXT || message_type == MSG_Type.IMAGE ||
            message_type == MSG_Type.FILE || message_type == MSG_Type.VOICE ||
            message_type == MSG_Type.VIDEO || message_type == MSG_Type.STICKER ||
            message_type == MSG_Type.LOCATION || message_type == MSG_Type.CONTACT ||
            message_type == MSG_Type.LINK || message_type == MSG_Type.REACTION ||
            message_type == MSG_Type.POLL) {
            this.message_type = message_type;
        } else {
            throw new IllegalArgumentException("Invalid message type.");
        }
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public LocalDateTime getSent_Time() {
        return Sent_Time;
    }

    public void setSent_Time(LocalDateTime sent_Time) {
        this.Sent_Time = sent_Time;
    }

    public LocalDateTime getRead_Time() {
        return Read_Time;
    }

    public void setRead_Time(LocalDateTime read_Time) {
        this.Read_Time = read_Time;
    }

    public CHAT getChat() {
        return chat;
    }

    public void setChat(CHAT chat) {
        this.chat = chat;
    }

    public USER getSender() {
        return sender;
    }

    public void setSender(USER sender) {
        this.sender = sender;
    }

    public String toString() {
        return "MESSAGE{" +
            "Message_id=" + Message_id +
            ", message_type=" + message_type +
            ", Content='" + Content + "'" +
            ", Sent_Time=" + Sent_Time +
            ", Read_Time=" + Read_Time +
            ", chat=" + (chat != null ? chat.getChat_id() : "null") +
            ", sender=" + (sender != null ? sender.getUser_id() : "null") +
            "}";
        }


    
}
