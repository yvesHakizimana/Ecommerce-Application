package com.rca.ecommerce.product;

import com.rca.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productServiceUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> purchaseRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        //Building our Http Body Request type and necessary Headers.
        HttpEntity<List<PurchaseRequest>> purchaseRequestEntity = new HttpEntity<>(purchaseRequests, headers);
        //Building our Http Response Type
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
        // Retrieving the request from another microservice.
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productServiceUrl + "/purchase", HttpMethod.POST, purchaseRequestEntity, responseType
        );
        if (responseEntity.getStatusCode().isError() || responseEntity.getBody() == null) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
