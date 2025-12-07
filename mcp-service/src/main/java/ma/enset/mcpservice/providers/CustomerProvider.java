package ma.enset.mcpservice.providers;

import ma.enset.mcpservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("customer-service")
public interface CustomerProvider {

    @GetMapping("/customers")
    List<Customer> getCustomers();
}
