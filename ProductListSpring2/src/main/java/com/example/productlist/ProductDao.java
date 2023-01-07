package com.example.productlist;

import java.util.List;

public interface ProductDao {

    void insertProduct(Product product);
    List<Product> selectAllProducts();
    Product selectProductById(int id);
    void deleteProductById(int id);
    void updateProduct(Product product);

}
