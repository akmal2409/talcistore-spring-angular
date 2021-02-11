package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import tech.talci.talcistorespring.dto.DiscountDto;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Discount;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.User;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private DiscountMapper discountMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "orderCount", ignore = true)
    @Mapping(target = "addedOn", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "seller", source = "seller")
    @Mapping(target = "description", source = "productDto.description")
    @Mapping(target = "discount", ignore = true)
    public abstract Product mapToProduct(ProductDto productDto, Category category, User seller);

    @Mapping(target = "discount", expression = "java(mapDiscount(product.getDiscount()))")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "sellerId", source = "seller.userId")
    public abstract ProductDto mapToProductDto(Product product);

    public DiscountDto mapDiscount(Discount discount) {
        return discountMapper.mapToDiscountDto(discount);
    }

}
