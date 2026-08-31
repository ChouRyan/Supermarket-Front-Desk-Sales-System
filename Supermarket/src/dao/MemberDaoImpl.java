package dao;

import entity.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    private static final String URL = "jdbc:mysql://localhost:3306/supermarket?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASS = "1234";

    @Override
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO Member (MemberID, MemberName, Password, Points) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, member.getMemberID());
            pstmt.setString(2, member.getMemberName());
            pstmt.setString(3, member.getPassword());
            pstmt.setInt(3, member.getPoints());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateMember(Member member) throws SQLException {
        String sql = "UPDATE Member SET MemberName=?, Points=? WHERE MemberID=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberName());
            pstmt.setInt(2, member.getPoints());
            pstmt.setInt(3, member.getMemberID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Member findById(int memberId) throws SQLException {
        String sql = "SELECT * FROM Member WHERE MemberID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Member m = new Member();
                    m.setMemberID(rs.getInt("MemberID"));
                    m.setMemberName(rs.getString("MemberName"));
                    m.setPoints(rs.getInt("Points"));
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public Member findByName(String name) throws SQLException {
        String sql = "SELECT * FROM Member WHERE MemberName = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Member m = new Member();
                    m.setMemberID(rs.getInt("MemberID"));
                    m.setMemberName(rs.getString("MemberName"));
                    m.setPoints(rs.getInt("Points"));
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public void updatePoints(int memberId, int points) throws SQLException {
        String sql = "UPDATE Member SET Points = Points + ? WHERE MemberID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, points);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Member> findAll() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Member";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberID(rs.getInt("MemberID"));
                member.setMemberName(rs.getString("MemberName"));
                member.setPoints(rs.getInt("Points"));
                members.add(member);
            }
        }
        return members;
    }
}