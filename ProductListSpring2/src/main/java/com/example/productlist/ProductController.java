package com.example.productlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/")
    public void deleteProductById(@RequestBody int id){
        productService.deleteProduct(id);
    }

    @PutMapping("/")
    public void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){return categoryService.getAllCategories();}
}
