package entity;

public class Product {
    private int productID;
    private String productName;
    private double price;
    private String category;

    // Getters & Setters
    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
