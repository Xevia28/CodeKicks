package entities;

public class Shoe {
    private int id;
    private String brand;
    private String style;
    private String size;

    public Shoe(String brand, String style, String size) {
        this.brand = brand;
        this.style = style;
        this.size = size;
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

    public int getId() {
        return id;
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
