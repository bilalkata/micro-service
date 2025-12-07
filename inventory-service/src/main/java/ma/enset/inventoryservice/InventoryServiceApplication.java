package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ProductRepository repository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                double price = Math.round((10 + Math.random() * 990) * 100.0) / 100.0;
                int quantity = 1 + (int) (Math.random() * 100);
                repository.save(Product.builder().name("product" + i).price(price).quantity(quantity).build());
            }
        };
    }
}
