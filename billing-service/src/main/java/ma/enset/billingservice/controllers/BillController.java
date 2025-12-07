package ma.enset.billingservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.billingservice.entities.Bill;
import ma.enset.billingservice.services.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    public List<Bill> getAllBills(){
        return this.billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return this.billService.getBillById(id);
    }

    @PostMapping
    public Bill saveBill(@RequestBody Bill bill) {
        return this.billService.saveBill(bill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.billService.delete(id);
    }
}
