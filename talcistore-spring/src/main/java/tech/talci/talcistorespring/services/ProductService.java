package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tech.talci.talcistorespring.dto.ProductDto;
import tech.talci.talcistorespring.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @PreAuthorize("hasRole(SELLER)")
    public void save(ProductDto productDto) {

    }
}
