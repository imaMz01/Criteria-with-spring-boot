package com.productCriteria.Service;

import com.productCriteria.Dtos.PaginationDto;
import com.productCriteria.Dtos.PaginationResponse;
import com.productCriteria.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product add(Product product);
    Page<Product> all(PaginationDto paginationDto);
    PaginationResponse allProducts(PaginationDto paginationDto);
    PaginationResponse findProductsWithCategoryAndPrice(PaginationDto paginationDto, String category, double min, double max);
    Product update(Product product);
    void delete(String id);
    Product getById(String id);
    List<Product> findProductsByPrice(double min, double max);
    List<Product> findProductsByNameContains(String substring);
}
