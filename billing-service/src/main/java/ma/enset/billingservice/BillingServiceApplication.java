package ma.enset.billingservice;

import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.entities.ProductItem;
import ma.enset.billingservice.repositories.BillRepository;
import ma.enset.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(BillRepository repository, ProductItemRepository productItemRepository) {
        return args -> {
            for (int i = 1; i < 10; i++) {
                double price = Math.round((10 + Math.random() * 990) * 100.0) / 100.0;
                int quantity = 1 + (int) (Math.random() * 10);
                repository.save(Bill.builder().date(new Date()).customerId((long) i).build());
                productItemRepository.save(ProductItem.builder()
                        .productId((long) i)
                        .quantity(quantity)
                        .price(price)
                        .bill(repository.getReferenceById((long) i))
                        .build());
            }
        };
    }

}
