package ma.enset.billingservice.services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.providers.ProductProvider;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductItemService {

    private final ProductItemRepository repository;
    private final ProductProvider productProvider;

    public List<ProductItem> getAllProductItems() {
        List<ProductItem> productItems = this.repository.findAll();
        productItems.forEach(p -> {
            Product product = productProvider.getProductById(p.getProductId());
            p.setProduct(product);
        });

        return productItems;
    }

    public ProductItem getProductItemById(Long id) {
        ProductItem productItem = this.repository.findById(id).orElse(null);
        Product product = productProvider.getProductById(productItem.getProductId());
        productItem.setProduct(product);
        return productItem;
    }

    public ProductItem saveProductItem(ProductItem productItem) {
        return this.repository.save(productItem);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
