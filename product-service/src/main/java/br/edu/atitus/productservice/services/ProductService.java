package br.edu.atitus.productservice.services;

import br.edu.atitus.productservice.dtos.ProductDTO;
import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.infrastructure.exceptions.ProductNotFoundException;
import br.edu.atitus.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Value("${server.port}")
    private String port;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO findById(Long id, String targetCurrency){
        ProductEntity product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        return new ProductDTO(product.getId(),
                product.getDescription(),
                product.getBrand(),
                product.getModel(),
                product.getPrice(),
                product.getCurrency(),
                product.getStock(),
                "Product-service running on Port: " + port,
                null,
                targetCurrency);
    }
}
