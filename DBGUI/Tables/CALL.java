package Tables;
import java.time.LocalDateTime;

import Enum.CallStatus;
import Enum.Call_Type;

public class CALL {
    private int Call_ID;
    private Call_Type Call_Type;
    private LocalDateTime Start_Time;
    private LocalDateTime End_Time;
    private CallStatus Status;
    private CHAT Chat;

    public CALL(int call_ID, Call_Type call_Type, LocalDateTime start_Time, LocalDateTime end_Time, CallStatus status, CHAT chat) {
        Call_ID = call_ID;
        Call_Type = call_Type;
        Start_Time = start_Time;
        End_Time = end_Time;
        Status = status;
        Chat = chat;
    }

    public int getCall_ID() {
        return Call_ID;
    }

    public void setCall_ID(int call_ID) {
        Call_ID = call_ID;
    }

    public Call_Type getCall_Type() {
        return Call_Type;
    }

    public void setCall_Type(Call_Type call_Type) {
        Call_Type = call_Type;
    }

    public LocalDateTime getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(LocalDateTime start_Time) {
        Start_Time = start_Time;
    }

    public LocalDateTime getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(LocalDateTime end_Time) {
        End_Time = end_Time;
    }

    public CallStatus getStatus() {
        return Status;
    }

    public void setStatus(CallStatus status) {
        Status = status;
    }

    public CHAT getChat() {
        return Chat;
    }

    public void setChat(CHAT chat) {
        Chat = chat;
    }
    
}