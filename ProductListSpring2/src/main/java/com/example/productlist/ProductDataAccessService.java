package com.example.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class ProductDataAccessService implements ProductDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertProduct(Product product) {
        jdbcTemplate.update(
                "INSERT INTO products (name,weight,price,category) VALUES (?,?,?,?)",
                product.getName(),product.getWeight(),product.getPrice(), product.getCategory().getCategory_id()
        );
    }

    @Override
    public List<Product> selectAllProducts() {
        return jdbcTemplate.query(
                "SELECT * FROM products ORDER BY id", (resultSet,i) -> {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    double weight = resultSet.getDouble("weight");
                    double price = resultSet.getDouble("price");
                    Category category = jdbcTemplate.queryForObject("SELECT * FROM categories where category_id=" +
                            resultSet.getInt("category"), (resultSet1,j) -> new Category(
                                    resultSet1.getInt("category_id"),
                                    resultSet1.getString("name")
                            ));
                    return new Product(
                            id,
                            name,
                            weight,
                            price,
                            category
                    );
                }
        );
    }

    @Override
    public Product selectProductById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM products WHERE id = ?",new Object[]{id}, (resultSet, i) ->
                    new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("weight"),
                            resultSet.getDouble("price"),
                            (Category) resultSet.getObject("category")

                    ));
                }

    @Override
    public void deleteProductById(int id) {
        jdbcTemplate.update(
                "DELETE FROM products WHERE id = ?", new Object[]{id}
        );

    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(
                "UPDATE Products SET name = ?, weight = ?, price = ?, category = ? WHERE id = ?",
                product.getName(),
                product.getWeight(),
                product.getPrice(),
                product.getCategory().getCategory_id(),
                product.getId()
        );
    }
}
