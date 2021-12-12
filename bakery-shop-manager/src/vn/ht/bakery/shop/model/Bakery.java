package vn.ht.bakery.shop.model;

public class Bakery {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public Bakery(int id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Bakery(String record) {
        String[] fields = record.split(",");
        id = Integer.parseInt(fields[0]);
        name = fields[1];
        price = Double.parseDouble(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        description = fields[4];
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + description;
    }

}
