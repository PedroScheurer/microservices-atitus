package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.dtos.ProductDTO;
import br.edu.atitus.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = {"{idProduct}"}, params = {"targetCurrency"})
    public ResponseEntity<ProductDTO> findProduct(@PathVariable Long idProduct,
                                                 @RequestParam String targetCurrency){

        ProductDTO productDTO = service.findById(idProduct, targetCurrency);

        return ResponseEntity.ok().body(productDTO);
    }
}
