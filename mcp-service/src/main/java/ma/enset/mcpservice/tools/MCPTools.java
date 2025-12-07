package ma.enset.mcpservice.tools;

import lombok.AllArgsConstructor;
import ma.enset.mcpservice.models.Customer;
import ma.enset.mcpservice.models.Product;
import ma.enset.mcpservice.providers.CustomerProvider;
import ma.enset.mcpservice.providers.ProductProvider;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MCPTools {

    private CustomerProvider customerProvider;
    private ProductProvider productProvider;

    @McpTool(name = "getProduct", description = "Get information about given product")
    public Product getProduct(@McpArg(description = "The product name") String name) {
        return this.productProvider.getProducts().stream().filter(p -> p.name().equals(name))
                .findFirst().orElse(null);
    }

    @McpTool(name = "getProducts", description = "Get information about all products")
    public List<Product> getProducts() {
        return this.productProvider.getProducts();
    }

    @McpTool(name = "getCustomer", description = "Get information about given customer")
    public Customer getCustomer(@McpArg(description = "The customer name") String name) {
        return this.customerProvider.getCustomers().stream().filter(c -> c.name().equals(name))
                .findFirst().orElse(null);
    }

    @McpTool(name = "getCustomers", description = "Get information about all customers")
    public List<Customer> getCustomers() {
        return this.customerProvider.getCustomers();
    }
}
