package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private List<ProductDto> products;
    private Long userId;
}
