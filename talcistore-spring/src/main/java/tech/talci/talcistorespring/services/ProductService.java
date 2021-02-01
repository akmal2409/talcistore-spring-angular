package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.mappers.ProductMapper;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.CategoryRepository;
import tech.talci.talcistorespring.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final AuthService authService;

    @PreAuthorize("hasRole('ROLE_SELLER')")
    @Transactional
    public void save(ProductDto productDto) {
        User user = authService.getCurrentUser();
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category with id "
                        + productDto.getCategoryId() + " was not found"));

        Product product = productMapper.mapToProduct(productDto, category, user);
        category.getProducts().add(product);

        categoryRepository.save(category);
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long productId) {
        Product fetchedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product was not found"));

        return productMapper.mapToProductDto(fetchedProduct);
    }
}
