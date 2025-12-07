package ma.enset.mcpservice.models;

import lombok.Builder;

@Builder
public record Product(
        Long id,
        String name,
        double price,
        int quantity) {}
