package entities;

public class Shoe {
    private String type; // casual, running, hiking, dress, sandal, boot, etc.
    private String brand;
    private String style; // oxford, loafer, pump, sneaker, etc.
    private String size;

    public Shoe(String type, String brand, String style, String size) {
        this.type = type;
        this.brand = brand;
        this.style = style;
        this.size = size;
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
}
