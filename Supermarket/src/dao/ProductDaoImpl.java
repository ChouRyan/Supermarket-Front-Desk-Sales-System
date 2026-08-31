package dao;

import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASS = "1234";

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (ProductName, Price, Category) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getCategory());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Product> findAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setCategory(rs.getString("Category"));
                products.add(p);
            }
        }
        return products;
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setProductID(rs.getInt("ProductID"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getDouble("Price"));
                    p.setCategory(rs.getString("Category"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET ProductName=?, Price=?, Category=? WHERE ProductID=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getCategory());
            pstmt.setInt(4, product.getProductID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}