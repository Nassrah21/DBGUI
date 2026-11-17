package Tables;


import java.time.LocalDate;

import Enum.Role;

public class JOINS {
    private USER user;
    private CHAT chat;
    private Role role;
    private LocalDate join_date;
    private boolean Mute;
    private boolean Pin;
    private boolean Archive;

    public JOINS(USER user, CHAT chat, Role role, LocalDate join_date, boolean mute, boolean pin, boolean archive) {
        this.user = user;
        this.chat = chat;
        this.role = role;
        this.join_date = join_date;
        Mute = mute;
        Pin = pin;
        Archive = archive;
    }

    public JOINS() {
        this.user = null;
        this.chat = null;
        this.role = Role.Member; // Default role
        this.join_date = LocalDate.now(); // Default to current date
        Mute = false; // Default to not muted
        Pin = false; // Default to not pinned
        Archive = false; // Default to not archived
    }

    public USER getUser() {
        return user;
    }

    public void setUser(USER user) {
        this.user = user;
    }

    public CHAT getChat() {
        return chat;
    }

    public void setChat(CHAT chat) {
        this.chat = chat;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public boolean isMute() {
        return Mute;
    }

    public void setMute(boolean mute) {
        Mute = mute;
    }

    public boolean isPin() {
        return Pin;
    }

    public void setPin(boolean pin) {
        Pin = pin;
    }

    public boolean isArchive() {
        return Archive;
    }

    public void setArchive(boolean archive) {
        Archive = archive;
    }

    
}
