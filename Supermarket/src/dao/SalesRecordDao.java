package dao;

import entity.SalesRecord;

import java.sql.SQLException;
import java.util.List;

public interface SalesRecordDao {
    // 添加销售记录
    void addSalesRecord(SalesRecord record) throws SQLException;

    // 查询某日销售记录（报表用）
    List<SalesRecord> findByDate(String saleDate) throws SQLException;

    // 查询某商品的销售记录（退货用）
    List<SalesRecord> findByProductId(int productId) throws SQLException;

    List<SalesRecord> findAll() throws SQLException;

    SalesRecord findById(int salesId) throws SQLException;

    void deleteSalesRecord(int salesId) throws SQLException;
}