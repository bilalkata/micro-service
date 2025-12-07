package ma.enset.billingservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.models.Customer;
import ma.enset.billingservice.models.Product;
import ma.enset.billingservice.providers.CustomerProvider;
import ma.enset.billingservice.providers.ProductProvider;
import ma.enset.billingservice.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BillService {

    private final BillRepository repository;
    private final CustomerProvider customerProvider;
    private final ProductProvider productProvider;

    public List<Bill> getAllBills() {
        List<Bill> bills = this.repository.findAll();
        bills.forEach(b -> {
            Customer customer = customerProvider.getCustomerById(b.getCustomerId());
            b.setCustomer(customer);
            b.getProductItems().forEach(p -> {
                Product product = productProvider.getProductById(p.getProductId());
                p.setProduct(product);
            });
        });
        return bills;
    }

    public Bill getBillById(Long id) {
        Bill bill =  this.repository.findById(id).orElse(null);
        if(bill == null) return null;
        Customer customer = customerProvider.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(p -> {
            Product product = productProvider.getProductById(p.getProductId());
            p.setProduct(product);
        });
        return bill;
    }

    public Bill saveBill(Bill bill) {
        return this.repository.save(bill);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
