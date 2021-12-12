package vn.ht.bakery.shop.model;

public class OrderItem {
    private long id;
    private double price;
    private int quantity;
    private long orderId;
    private int bakeryId;
    private String bakeryName;
    private double total;

    public OrderItem(long id, double price, int quantity, long orderId, int bakeryId, String bakeryName, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.bakeryId = bakeryId;
        this.bakeryName = bakeryName;
        this.total = total;
    }
    public OrderItem(){

    }
    public OrderItem(String record) {
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0]);
        price = Double.parseDouble(fields[1]);
        quantity = Integer.parseInt(fields[2]);
        orderId = Long.parseLong(fields[3]);
        bakeryId = Integer.parseInt(fields[4]);
        bakeryName = fields[5];
        total = Double.parseDouble(fields[6]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getBakeryId() {
        return bakeryId;
    }

    public void setBakeryId(int bakeryId) {
        this.bakeryId = bakeryId;
    }

    public String getBakeryName() {
        return bakeryName;
    }

    public void setBakeryName(String bakeryName) {
        this.bakeryName = bakeryName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "," + price + "," + quantity + "," + orderId + "," + bakeryId + "," + bakeryName + "," +total;
    }


}
