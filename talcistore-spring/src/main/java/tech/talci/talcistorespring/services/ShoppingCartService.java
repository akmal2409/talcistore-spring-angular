package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.*;
import tech.talci.talcistorespring.dto.mappers.ProductMapper;
import tech.talci.talcistorespring.exceptions.NotInStockException;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.exceptions.TalciStoreException;
import tech.talci.talcistorespring.model.CartItem;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ShoppingCart;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.CartItemRepository;
import tech.talci.talcistorespring.repositories.ProductRepository;
import tech.talci.talcistorespring.repositories.ShoppingCartRepository;
import tech.talci.talcistorespring.util.PaginationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;
    private final CartItemRepository cartItemRepository;
    private final ProductMapper productMapper;

    public void modifyCart(ModifyCartRequest cartRequest) {
        switch (cartRequest.getType()) {
            case ADD:
                addProducts(cartRequest);
                break;
            case REMOVE:
                removeProducts(cartRequest);
                break;
        }
    }

    @Transactional
    public void addProducts(ModifyCartRequest cartRequest) {
        ShoppingCart fetchedCard = getCartForCurrentUser();
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product was not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartRequest.getQuantity());
        cartItem.setCart(fetchedCard);

        fetchedCard.getProducts().add(cartItem);

        cartItemRepository.save(cartItem);
        cartRepository.save(fetchedCard);
    }

    @Transactional
    public void removeProducts(ModifyCartRequest cartRequest) {
        ShoppingCart fetchedCart = getCartForCurrentUser();
        Product fetchedProduct = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product was not found"));

        CartItem fetchedCartItem = cartItemRepository.findByCartAndProduct(fetchedCart, fetchedProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item was not found"));

        if (cartRequest.getQuantity() >= fetchedCartItem.getQuantity()) {
            fetchedCart.getProducts().remove(fetchedCartItem);
            cartItemRepository.delete(fetchedCartItem);
            cartRepository.save(fetchedCart);
        } else {
            fetchedCartItem.setQuantity(fetchedCartItem.getQuantity() - cartRequest.getQuantity());
        }

        cartItemRepository.save(fetchedCartItem);
    }

    @Transactional(readOnly = true)
    public PageResponse<CartItemDto> getCartItems(Integer page, Integer size) {
        ShoppingCart fetchedCart = getCartForCurrentUser();
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CartItemDto> fetchedPage = cartItemRepository.findByCart(fetchedCart, pageRequest)
                .map(this::mapCartItemToDto);

        return PaginationUtil.convertToPageResponse(fetchedPage);
    }

    @Transactional(readOnly = true)
    public ShoppingCart getCartForCurrentUser() {
        User user = authService.getCurrentUser();
        ShoppingCart fetchedCart = cartRepository.findShoppingCartByUser(user)
                .orElseThrow(() -> new TalciStoreException("Shopping cart was not found"));

        return fetchedCart;
    }

    private CartItemDto mapCartItemToDto(CartItem item) {
        CartItemDto itemDto = new CartItemDto();
        itemDto.setId(item.getId());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setProductDto(productMapper.mapToProductDto(item.getProduct()));

        return itemDto;
    }
}
