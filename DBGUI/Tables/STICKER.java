package Tables;
import Enum.Sticker_Type;

public class STICKER { 
    private int Sticker_ID;
    private String Sticker_Name;
    private String Sticker_URL;
    private Sticker_Type Sticker_Type;
    private double Price;
    private String Package;

    public STICKER(int sticker_id, String sticker_name, String sticker_url,
                   Sticker_Type sticker_type, double price, String packageName) {
        this.Sticker_ID = sticker_id;
        this.Sticker_Name = sticker_name;
        this.Sticker_URL = sticker_url;
        this.Sticker_Type = sticker_type;
        this.Price = price;
        this.Package = packageName;
    }

    public STICKER() {
        this.Sticker_ID = 0; // Default ID
        this.Sticker_Name = ""; // Default name
        this.Sticker_URL = ""; // Default URL
        this.Sticker_Type = Sticker_Type.Static; // Default sticker type
        this.Price = 0.0; // Default price
        this.Package = ""; // Default package name
    }

    public int getSticker_id() {
        return Sticker_ID;
    }

    public void setSticker_id(int sticker_id) {
        this.Sticker_ID = sticker_id;
    }

    public String getSticker_name() {
        return Sticker_Name;
    }

    public void setSticker_name(String sticker_name) {
        this.Sticker_Name = sticker_name;
    }

    public String getSticker_url() {
        return Sticker_URL;
    }

    public void setSticker_url(String sticker_url) {
        this.Sticker_URL = sticker_url;
    }

    public Sticker_Type getSticker_type() {
        return Sticker_Type;
    }

    public void setSticker_type(Sticker_Type sticker_type) {
        this.Sticker_Type = sticker_type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String packageName) {
        this.Package = packageName;
    }

    
}