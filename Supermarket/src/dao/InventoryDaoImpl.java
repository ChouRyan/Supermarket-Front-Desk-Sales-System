package dao;

import entity.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoImpl implements InventoryDao {
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASS = "1234";

    @Override
    public void addInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO Inventory (InventoryID, ProductID, Quantity) VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventory.getInventoryID());
            pstmt.setInt(2, inventory.getProductID());
            pstmt.setInt(3, inventory.getQuantity());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateInventory(Inventory inventory) throws SQLException {
        String sql = "UPDATE Inventory SET Quantity=? WHERE ProductID=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, inventory.getQuantity());
            pstmt.setInt(2, inventory.getProductID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Inventory findByProductId(int productId) throws SQLException {
        String sql = "SELECT * FROM Inventory WHERE ProductID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setInventoryID(rs.getInt("InventoryID"));
                    inv.setProductID(rs.getInt("ProductID"));
                    inv.setQuantity(rs.getInt("Quantity"));
                    return inv;
                }
            }
        }
        return null;
    }

    @Override
    public List<Inventory> findLowStock(int threshold) throws SQLException {
        List<Inventory> lowStock = new ArrayList<>();
        String sql = "SELECT * FROM Inventory WHERE Quantity < ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threshold);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Inventory inv = new Inventory();
                    inv.setProductID(rs.getInt("ProductID"));
                    inv.setQuantity(rs.getInt("Quantity"));
                    lowStock.add(inv);
                }
            }
        }
        return lowStock;
    }

    @Override
    public List<Inventory> findAll() throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setInventoryID(rs.getInt("InventoryID"));
                inv.setProductID(rs.getInt("ProductID"));
                inv.setQuantity(rs.getInt("Quantity"));
                inventoryList.add(inv);
            }
        }
        return inventoryList;
    }
}