package com.chair.springboot_mall.service;

import com.chair.springboot_mall.dto.ProductRequest;
import com.chair.springboot_mall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
