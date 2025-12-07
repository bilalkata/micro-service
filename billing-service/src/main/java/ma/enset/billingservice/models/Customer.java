package ma.enset.billingservice.models;


import lombok.Builder;

@Builder
public record Customer(
        Long id,
        String name,
        String email) { }
