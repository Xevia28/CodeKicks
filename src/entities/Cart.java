package entities;

public class Cart {
    private int id;
    private int userid;

    public Cart(int userid) {
        this.userid = userid;
    }

    public Cart(int id, int userid) {
        this.id = id;
        this.userid = userid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userid;
    }
}
