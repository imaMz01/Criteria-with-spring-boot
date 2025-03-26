package com.productCriteria.Controller;


import com.productCriteria.Dtos.PaginationDto;
import com.productCriteria.Dtos.PaginationResponse;
import com.productCriteria.Entity.Product;
import com.productCriteria.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Page<Product>> all(@ModelAttribute PaginationDto paginationDto){
        return new ResponseEntity<>(productService.all(paginationDto), HttpStatus.OK);
    }

    @PostMapping("/products/v2")
    public ResponseEntity<PaginationResponse> allProducts(@ModelAttribute PaginationDto paginationDto){
        return new ResponseEntity<>(productService.allProducts(paginationDto), HttpStatus.OK);
    }

    @PostMapping("/products/category-price")
    public ResponseEntity<PaginationResponse> allProductsWithCategoryAndPrice(
            @ModelAttribute PaginationDto paginationDto,
            @RequestParam String category,
            @RequestParam double min,
            @RequestParam double max){
        System.out.println("category "+category +"min "+ min +"max "+ max);
        return new ResponseEntity<>(productService.findProductsWithCategoryAndPrice(paginationDto, category, min, max), HttpStatus.OK);
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Product> productById(@PathVariable String id){
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.add(product),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.update(product),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/findProductsByPrice/{min}/{max}")
    public ResponseEntity<List<Product>> findProductsByPrice(@PathVariable double min, @PathVariable double max){
        return new ResponseEntity<>(productService.findProductsByPrice(min,max),HttpStatus.OK);
    }

    @GetMapping("/findProductsByNameContains/{subString}")
    public ResponseEntity<List<Product>> findProductsByNameContains(@PathVariable String subString){
        return new ResponseEntity<>(productService.findProductsByNameContains(subString),HttpStatus.OK);
    }
}
