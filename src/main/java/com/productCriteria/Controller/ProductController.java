package com.productCriteria.Controller;


import com.productCriteria.Entity.Product;
import com.productCriteria.Service.ProductService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> all(){
        return new ResponseEntity<>(productService.all(), HttpStatus.OK);
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
