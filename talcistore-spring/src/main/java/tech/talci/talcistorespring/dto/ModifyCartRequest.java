package tech.talci.talcistorespring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyCartRequest {
    @NotNull(message = "Product id is required")
    private Long productId;
    @Min(value = 1, message = "Quantity must be greater than 1")
    private int quantity;

    @NotNull(message = "Modification type is required")
    private CartModificationType type;

    public enum CartModificationType {
        ADD, REMOVE
    }
}
