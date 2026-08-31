package dao;

import entity.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDao {
    // 添加库存记录（商品初始化时调用）
    void addInventory(Inventory inventory) throws SQLException;

    // 更新库存数量（销售/退货时调用）
    void updateInventory(Inventory inventory) throws SQLException;

    // 根据商品ID查询库存
    Inventory findByProductId(int productId) throws SQLException;

    // 库存预警查询（库存量<threshold）
    List<Inventory> findLowStock(int threshold) throws SQLException;

    List<Inventory> findAll() throws SQLException;
}
