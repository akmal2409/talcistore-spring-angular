package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.ProductImageDto;
import tech.talci.talcistorespring.dto.mappers.ProductImageMapper;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.exceptions.UnauthorizedActionException;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.ProductImage;
import tech.talci.talcistorespring.model.Role;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.ProductImageRepository;
import tech.talci.talcistorespring.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ProductImageRepository imageRepository;
    private final ProductImageMapper productImageMapper;
    private final ProductRepository productRepository;
    private final AuthService authService;

    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
    @Transactional
    public void addImage(ProductImageDto productImageDto) {
        Product product = fetchProduct(productImageDto.getProductId());

        User currentUser = authService.getCurrentUser();

        if (!product.getSeller().getUserId().equals(currentUser.getUserId())
                && !Role.isAdmin(currentUser)) {
            throw new UnauthorizedActionException("You are not authorized to modify products images");
        }

        ProductImage productImage = productImageMapper
                .mapToProductImage(productImageDto, product);

        imageRepository.save(productImage);
    }

    private Product fetchProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product with id: "
                                + productId + " was not found"));
    }

    @Transactional(readOnly = true)
    public List<ProductImageDto> findAllImages(Long productId) {
        Product fetchedProduct = fetchProduct(productId);

        List<ProductImage> images = imageRepository.findAllByProduct(fetchedProduct);

        return images.stream()
                .map(productImageMapper::mapToProductImageDto)
                .collect(Collectors.toList());
    }
}
