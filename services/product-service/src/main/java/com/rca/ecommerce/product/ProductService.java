package com.rca.ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productRepository.save(productMapper.toProduct(productRequest));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> purchaseRequestList) {
        return null;
    }

    public ProductResponse findProductById(Integer productId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
