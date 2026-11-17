package DAO;


// Fpr combining all the DAO's together
public class Database {
    public static final userDAO users = new userDAO();
    public static final chatDAO chats = new chatDAO();
    public static final messageDAO messages = new messageDAO();
    public static final callDAO calls = new callDAO();
    public static final stickerDAO stickers = new stickerDAO();
    public static final used_inDAO used_in = new used_inDAO();
    public static final ownsDAO owns = new ownsDAO();
    public static final makesDAO makes = new makesDAO();
    public static final joinsDAO joins = new joinsDAO();
    public static final friendshipDAO friendships = new friendshipDAO();

}
