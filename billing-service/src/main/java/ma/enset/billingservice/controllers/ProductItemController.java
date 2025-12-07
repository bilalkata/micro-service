package ma.enset.billingservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.services.ProductItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("products/items")
@AllArgsConstructor
public class ProductItemController {

    private final ProductItemService productItemService;
    
    @GetMapping
    public List<ProductItem> getAllProductItems(){
        return this.productItemService.getAllProductItems();
    }

    @GetMapping("/{id}")
    public ProductItem getProductItemById(@PathVariable Long id) {
        return this.productItemService.getProductItemById(id);
    }

    @PostMapping
    public ProductItem saveProductItem(@RequestBody ProductItem ProductItem) {
        return this.productItemService.saveProductItem(ProductItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productItemService.delete(id);
    }
}
