package ma.enset.mcpservice.providers;

import ma.enset.mcpservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("inventory-service")
public interface ProductProvider {

    @GetMapping("/products")
    List<Product> getProducts();
}
