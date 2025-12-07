package ma.enset.billingservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.billingservice.models.Product;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
    @JsonIgnore
    @ManyToOne
    private Bill bill;
    @Transient
    private Product product;
}
