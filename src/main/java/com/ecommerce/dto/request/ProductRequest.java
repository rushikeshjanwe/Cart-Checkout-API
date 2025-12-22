package com.ecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull @Positive
    private BigDecimal price;
    @NotNull @Min(0)
    private Integer stock;
    private String category;
    private String imageUrl;
}
