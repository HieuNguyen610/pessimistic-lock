package hieu.pessimisticlock.service;

import hieu.pessimisticlock.entity.Product;
import hieu.pessimisticlock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void updateProductPricePessimistic(Long id, Double newPrice) {
        Optional<Product> product = productRepository.findByIdForUpdate(id);
        product.ifPresent(p -> {
            p.setPrice(newPrice);
            productRepository.save(p);
        });
    }

    @Transactional
    public void updateProductPriceOptimistic(Long id, Double newPrice) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p -> {
            p.setPrice(newPrice);
            productRepository.save(p);
        });
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
