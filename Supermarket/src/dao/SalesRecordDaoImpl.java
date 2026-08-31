package dao;

import entity.SalesRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesRecordDaoImpl implements SalesRecordDao {
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASS = "1234";

    @Override
    public void addSalesRecord(SalesRecord record) throws SQLException {
        String sql = "INSERT INTO SalesRecord (SalesID, ProductID, MemberID, Quantity, SaleDate) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, record.getSalesID());
            pstmt.setInt(2, record.getProductID());
            pstmt.setInt(3, record.getMemberID());
            pstmt.setInt(4, record.getQuantity());
            pstmt.setString(5, record.getSaleDate());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<SalesRecord> findByDate(String saleDate) throws SQLException {
        List<SalesRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM SalesRecord WHERE SaleDate = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, saleDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SalesRecord sr = new SalesRecord();
                    sr.setSalesID(rs.getInt("SalesID"));
                    sr.setProductID(rs.getInt("ProductID"));
                    sr.setMemberID(rs.getInt("MemberID"));
                    sr.setQuantity(rs.getInt("Quantity"));
                    sr.setSaleDate(rs.getString("SaleDate"));
                    records.add(sr);
                }
            }
        }
        return records;
    }

    @Override
    public List<SalesRecord> findByProductId(int productId) throws SQLException {
        List<SalesRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM SalesRecord WHERE ProductID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SalesRecord sr = new SalesRecord();
                    sr.setSalesID(rs.getInt("SalesID"));
                    sr.setProductID(rs.getInt("ProductID"));
                    sr.setMemberID(rs.getInt("MemberID"));
                    sr.setQuantity(rs.getInt("Quantity"));
                    sr.setSaleDate(rs.getString("SaleDate"));
                    records.add(sr);
                }
            }
        }
        return records;
    }

    @Override
    public List<SalesRecord> findAll() throws SQLException {
        List<SalesRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM SalesRecord";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SalesRecord sr = new SalesRecord();
                sr.setSalesID(rs.getInt("SalesID"));
                sr.setProductID(rs.getInt("ProductID"));
                sr.setMemberID(rs.getInt("MemberID"));
                sr.setQuantity(rs.getInt("Quantity"));
                sr.setSaleDate(rs.getString("SaleDate"));
                records.add(sr);
            }
        }
        return records;
    }

    @Override
    public SalesRecord findById(int id) throws SQLException {
        String sql = "SELECT * FROM SalesRecord WHERE SalesID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    SalesRecord sr = new SalesRecord();
                    sr.setSalesID(rs.getInt("SalesID"));
                    sr.setProductID(rs.getInt("ProductID"));
                    sr.setMemberID(rs.getInt("MemberID"));
                    sr.setQuantity(rs.getInt("Quantity"));
                    sr.setSaleDate(rs.getString("SaleDate"));
                    return sr;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteSalesRecord(int id) throws SQLException {
        String sql = "DELETE FROM SalesRecord WHERE SalesID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}