package dao;

import entity.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDao {
    // 注册新会员
    void addMember(Member member) throws SQLException;

    // 修改会员信息
    void updateMember(Member member) throws SQLException;

    // 根据ID查询会员
    Member findById(int memberId) throws SQLException;

    // 根据用户名查询会员（登录用）
    Member findByName(String name) throws SQLException;

    // 更新积分（购买后调用）
    void updatePoints(int memberId, int points) throws SQLException;

    List<Member> findAll() throws SQLException;
}