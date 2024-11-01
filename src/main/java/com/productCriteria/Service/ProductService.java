package com.productCriteria.Service;

import com.productCriteria.Entity.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);
    List<Product> all();
    Product update(Product product);
    void delete(String id);
    Product getById(String id);
    List<Product> findProductsByPrice(double min, double max);
    List<Product> findProductsByNameContains(String substring);
}
