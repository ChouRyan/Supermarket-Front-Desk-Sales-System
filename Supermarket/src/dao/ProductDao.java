package dao;

import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void addProduct(Product product) throws SQLException;
    List<Product> findAllProducts() throws SQLException;
    Product findById(int id) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int id) throws SQLException;
}