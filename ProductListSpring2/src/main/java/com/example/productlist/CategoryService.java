package com.example.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(@Qualifier("cat") CategoryDao categoryDao){ this.categoryDao = categoryDao;}

    public List<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    }

}
