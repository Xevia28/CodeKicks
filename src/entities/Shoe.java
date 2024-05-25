package entities;

public class Shoe {
    private int id;
    private String type; // casual, running, hiking, dress, sandal, boot, etc.
    private String brand;
    private String style; // oxford, loafer, pump, sneaker, etc.
    private String size;
    private double price;

    public Shoe(int id, String type, String brand, String style, String size, double price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.style = style;
        this.size = size;
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getStyle() {
        return style;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
