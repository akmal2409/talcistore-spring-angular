package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.ShoppingCartDto;
import tech.talci.talcistorespring.dto.mappers.ProductMapper;
import tech.talci.talcistorespring.exceptions.TalciStoreException;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ShoppingCart;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.ShoppingCartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;
    private final AuthService authService;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        ShoppingCart fetchedCart = getCartForCurrentUser();


    }

    @Transactional(readOnly = true)
    public ShoppingCartDto getCartLastFive() {
        ShoppingCart fetchedCart = getCartForCurrentUser();

        return mapToDtoWithFiveProducts(fetchedCart);
    }

    private ShoppingCartDto mapToDtoWithFiveProducts(ShoppingCart shoppingCart) {
        ShoppingCartDto cartDto = new ShoppingCartDto();

        cartDto.setId(shoppingCart.getId());
        cartDto.setUserId(shoppingCart.getUser().getUserId());
        List<ProductDto> productDtos = new ArrayList<>();

        if (shoppingCart.getProducts().size() >= 5) {
            for (int i = 0; i < 5; i++) {
                productDtos.add(productMapper.mapToProductDto(shoppingCart.getProducts().get(i)));
            }
        } else {
            for (Product product: shoppingCart.getProducts()) {
                productDtos.add(productMapper.mapToProductDto(product));
            }
        }

        cartDto.setProducts(productDtos);
        return cartDto;
    }

    @Transactional(readOnly = true)
    private ShoppingCart getCartForCurrentUser() {
        User user = authService.getCurrentUser();
        ShoppingCart fetchedCart = cartRepository.findShoppingCartByUser(user)
                .orElseThrow(() -> new TalciStoreException("Shopping cart was not found"));

        return fetchedCart;
    }

}
