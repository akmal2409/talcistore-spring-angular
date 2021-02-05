package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.PageResponse;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.dto.mappers.ProductMapper;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.model.Category;
import tech.talci.talcistorespring.model.Product;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.CategoryRepository;
import tech.talci.talcistorespring.repositories.ProductRepository;
import tech.talci.talcistorespring.util.PaginationUtil;

@Service
@RequiredArgsConstructor
@Transactional
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

    public PageResponse<ProductDto> findAllByCategoryId(Long categoryId, Integer page, Integer size,
                                                        boolean sortByPrice, boolean desc) {
        PageRequest pageRequest = PageRequest.of(page, size);

    }


}
