package com.productCriteria.Repository;

import com.productCriteria.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>, PagingAndSortingRepository<Product,String> {

    Page<Product> findAll(Pageable pageable);

    @Query("Select p from Product p where p.category =:category and p.price between :min and :max")
    Page<Product> findProductsWithCategoryAndPrice(Pageable pageable, @Param("category") String category,@Param("min") double min,@Param("max") double max);
}
