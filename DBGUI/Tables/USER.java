package Tables;
import java.time.LocalDate;

import Enum.User_type;

public class USER{
    private int User_id;
    private String Username;
    private String Display_Name;
    private String Phone_Number;
    private LocalDate Birth_Date;
    private String Password;
    private User_type user_type;
    private String Bio;
    private LocalDate Registration_Date;
    private String Profile_Photo;

    public USER(int user_id, String username, String display_Name, String phone_Number,
                LocalDate birth_Date, String password, User_type user_Type,
                String bio, LocalDate registration_Date, String photoPath) {
        
        this.User_id = user_id;
        setUsername(username);
        setDisplay_Name(display_Name);
        setPhone_Number(phone_Number);
        setBirth_Date(birth_Date);
        setPassword(password);
        setUser_Type(user_Type);
        this.Bio = bio;
        this.Registration_Date = registration_Date;
        setProfile_Photo(photoPath);
    }

    public USER() {
        this.User_id = 0;
        this.Username = "";
        this.Display_Name = "";
        this.Phone_Number = "";
        this.Birth_Date = LocalDate.now();
        this.Password = "";
        this.user_type = User_type.Standard;
        this.Bio = "";
        this.Registration_Date = LocalDate.now();
        this.Profile_Photo = null;
    }

    public USER(int user_id, String username, String display_Name, String phone_Number,
                LocalDate birth_Date, String password, User_type user_Type,
                String bio, String photoPath) {
        this.User_id = user_id;
        setUsername(username);
        setDisplay_Name(display_Name);
        setPhone_Number(phone_Number);
        setBirth_Date(birth_Date);
        setPassword(password);
        setUser_Type(user_Type);
        this.Bio = bio;
        this.Registration_Date = LocalDate.now();
        setProfile_Photo(photoPath); 
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        else if(username.length() < 1 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be between 1 and 20 characters.");
        }
        else if(!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers, and underscores.");
        }
        Username = username;
    }

    public String getDisplay_Name() {
        return Display_Name;
    }

    public void setDisplay_Name(String display_Name) {
        if(display_Name == null || display_Name.trim().isEmpty()) {
            throw new IllegalArgumentException("Display name cannot be null or empty.");
        }
        else if(display_Name.length() > 20) {
            throw new IllegalArgumentException("Display name must be less than 20 characters.");
        }
        Display_Name = display_Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        if(phone_Number == null || phone_Number.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        else if(!phone_Number.matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Phone number must be between 10 and 15 digits, optionally starting with a '+'.");
        }
        Phone_Number = phone_Number;
    }

    public LocalDate getBirth_Date() {
        return Birth_Date;
    }

    public void setBirth_Date(LocalDate birth_Date) {
        if (birth_Date == null) 
            Birth_Date = null;
        else if(birth_Date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future.");
        }
        Birth_Date = birth_Date;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        if(password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        else if(password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        else if(!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }
        else if(!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter.");
        }
        else if(!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one digit.");
        }
        else if(!password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character (!@#$%^&*()).");
        }
        Password = password;
    }

    public User_type getUser_Type() {
        return user_type;
    }

    public void setUser_Type(User_type user_Type) {
        if(user_Type == null) {
            throw new IllegalArgumentException("User type cannot be null.");
        }
        if(user_Type != User_type.Administrator && user_Type != User_type.Standard) {
            throw new IllegalArgumentException("User type must be either Administrator or Regular_User.");
        }
        user_type = user_Type;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public LocalDate getRegistration_Date() {
        return Registration_Date;
    }

    public void setRegistration_Date(LocalDate registration_Date) {
        Registration_Date = registration_Date;
    }

    public String getProfile_Photo() {
        return Profile_Photo;
    }

    public void setProfile_Photo(String photoPath) {
        if(photoPath == null)
            Profile_Photo = null;
        /* 
        try{
            BufferedImage originalImage = ImageIO.read(new File(photoPath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( originalImage, "jpg", baos );
            baos.flush();
            Profile_Photo = baos.toByteArray();
            baos.close();
            }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }*/
    }

    public boolean isAdmin() {
        return user_type == User_type.Administrator;
    }

    // For logging in, we can compare the user_id and password with the stored values
    public USER validation(String username, String password) {
        if (this.Username.equals(username) && this.Password.equals(password))
            return this;
        return null;
    }

    public String toString() {
        return "USER{" +
                "User_id=" + User_id +
                ", Username='" + Username + '\'' + 
                ", Display_Name='" + Display_Name + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", Birth_Date=" + Birth_Date +
                ", Password='" + Password + '\'' +
                ", User_Type=" + user_type +
                ", Bio='" + Bio + '\'' +
                ", Registration_Date=" + Registration_Date +
                '}';
    }
}