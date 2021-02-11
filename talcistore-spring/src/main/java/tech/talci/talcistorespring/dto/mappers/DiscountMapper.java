package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.talcistorespring.dto.DiscountDto;
import tech.talci.talcistorespring.model.Discount;
import tech.talci.talcistorespring.model.Product;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    Discount mapToDiscount(DiscountDto discountDto, Product product);

    @Mapping(target = "productId", source = "product.id")
    DiscountDto mapToDiscountDto(Discount discount);
}
