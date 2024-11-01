package com.productCriteria.Exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String id) {
        super(ExceptionMessages.PRODUCT_NOT_FOUND.getMessage(id));
    }
}