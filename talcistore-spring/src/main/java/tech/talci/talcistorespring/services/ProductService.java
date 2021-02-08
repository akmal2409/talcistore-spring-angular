package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.mappers.ProductMapper;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.exceptions.UnauthorizedActionException;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.Role;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.CategoryRepository;
import tech.talci.talcistorespring.repositories.ProductRepository;
import tech.talci.talcistorespring.repositories.UserRepository;
import tech.talci.talcistorespring.util.PaginationUtil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
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
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductDto> getAll(Integer page, Integer size, boolean sortByPrice, boolean desc) {
        PageRequest pageRequest = PaginationUtil
                .productPageRequest(page, size, sortByPrice, desc);

        Page<ProductDto> fetchedPage = productRepository
                .findAll(pageRequest)
                .map(productMapper::mapToProductDto);


        return PaginationUtil.convertToPageResponse(fetchedPage);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long productId) {
        Product fetchedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product was not found"));

        return productMapper.mapToProductDto(fetchedProduct);
    }


    @Transactional(readOnly = true)
    public PageResponse<ProductDto> searchByKeyword(String nameOrDescription, Integer page, Integer size,
                                                    boolean sortByPrice, boolean desc) {
        PageRequest pageRequest = PaginationUtil
                .productPageRequest(page, size, sortByPrice, desc);

        Page<ProductDto> fetchedPage = productRepository
                .findByProductNameOrDescriptionContaining(nameOrDescription, pageRequest)
                .map(productMapper::mapToProductDto);

        return PaginationUtil.convertToPageResponse(fetchedPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductDto> findAllByCategoryId(Long categoryId, Integer page, Integer size,
                                                        boolean sortByPrice, boolean desc) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category was not found"));

        PageRequest pageRequest = PaginationUtil
                .productPageRequest(page, size, sortByPrice, desc);

        Page<ProductDto> fetchedPage = productRepository
                .findByCategory(category, pageRequest)
                .map(productMapper::mapToProductDto);

        return PaginationUtil.convertToPageResponse(fetchedPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductDto> findAllBySellerId(Long sellerId, Integer page, Integer size,
                                                      boolean sortByPrice, boolean desc) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller was not found"));

        Set<Role> roles = seller.getRoles()
                .stream()
                .filter(role -> role.getName().equals("SELLER"))
                .collect(Collectors.toSet());

        if (roles.isEmpty()) {
            throw new ResourceNotFoundException("Seller was not found!");
        }

        PageRequest pageRequest = PaginationUtil
                .productPageRequest(page, size, sortByPrice, desc);

        Page<ProductDto> fetchedPage = productRepository.findBySeller(seller, pageRequest)
                .map(productMapper::mapToProductDto);

        return PaginationUtil.convertToPageResponse(fetchedPage);
    }

    @Transactional
    @PreAuthorize("hasRole('SELLER')")
    public void deleteById(Long id) {
        Product fetchedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        User seller = authService.getCurrentUser();

        if (fetchedProduct.getSeller().getUserId() != seller.getUserId()
                && !Role.isAdmin(seller)) {
            throw new UnauthorizedActionException("It must be your product or you must be an administrator");
        }

        Optional<Category> fetchedCategory = categoryRepository
                .findById(fetchedProduct.getCategory().getId());

        if (fetchedCategory.isPresent()) {
            Category category = fetchedCategory.get();
            category.getProducts().remove(fetchedProduct);
            categoryRepository.save(category);
        }

        productRepository.delete(fetchedProduct);
    }
}
