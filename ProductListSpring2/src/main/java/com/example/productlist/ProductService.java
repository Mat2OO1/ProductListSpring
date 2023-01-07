package com.example.productlist;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("postgres") ProductDao productDao){
        this.productDao = productDao;
    }

    public void addProduct(Product product){
        productDao.insertProduct(product);
    }

    public List<Product> getAllProducts(){
        return productDao.selectAllProducts();
    }

    public Product getProductById(int id){
        return productDao.selectProductById(id);
    }

    public void deleteProduct(int id){
        productDao.deleteProductById(id);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }
}
