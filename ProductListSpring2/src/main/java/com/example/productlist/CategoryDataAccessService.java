package com.example.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cat")
public class CategoryDataAccessService implements CategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(
                "SELECT * FROM categories", (resultSet,i) -> {
                    String category = resultSet.getString("name");
                    int id = resultSet.getInt("category_id");
                    return new Category(id,category);

                }
        );
    }
}
