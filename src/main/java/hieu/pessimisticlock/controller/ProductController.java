package hieu.pessimisticlock.controller;

import hieu.pessimisticlock.entity.Product;
import hieu.pessimisticlock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Endpoint for saving a new product
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Endpoint for pessimistic locking
    @PutMapping("/pessimistic/{id}")
    public String updateProductPricePessimistic(@PathVariable Long id, @RequestParam Double newPrice) {
        productService.updateProductPricePessimistic(id, newPrice);
        return "Product price updated with pessimistic lock.";
    }

    // Endpoint for optimistic locking
    @PutMapping("/optimistic/{id}")
    public String updateProductPriceOptimistic(@PathVariable Long id, @RequestParam Double newPrice) {
        productService.updateProductPriceOptimistic(id, newPrice);
        return "Product price updated with optimistic lock.";
    }
}
