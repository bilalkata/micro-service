package ma.enset.billingservice.providers;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.enset.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventory-service")
public interface ProductProvider {

    @GetMapping("products/{id}")
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "getDefaultProduct")
    Product getProductById(@PathVariable Long id);

    default Product getDefaultProduct(Long id, Exception e) {
        return Product.builder().id(id).name("default").price(0.0).quantity(0).build();
    }

}
