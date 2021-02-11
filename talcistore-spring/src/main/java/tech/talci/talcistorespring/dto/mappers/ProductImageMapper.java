package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.talcistorespring.dto.ProductImageDto;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    ProductImage mapToProductImage(ProductImageDto productImageDto, Product product);

    @Mapping(target = "productId", source = "product.id")
    ProductImageDto mapToProductImageDto(ProductImage productImage);
}
