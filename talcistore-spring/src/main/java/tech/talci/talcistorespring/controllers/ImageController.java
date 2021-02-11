package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.ProductImageDto;
import tech.talci.talcistorespring.services.ImageService;

import java.util.List;

@RestController
@RequestMapping(ImageController.BASE_URL)
@RequiredArgsConstructor
public class ImageController {

    public final static String BASE_URL = "/api/images";
    private final ImageService imageService;

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public void addImage(@RequestBody ProductImageDto imageDto) {
        imageService.addImage(imageDto);
    }

    @GetMapping("/product/{productId}")
    public List<ProductImageDto> findAllImages(@PathVariable Long productId) {
        return imageService.findAllImages(productId);
    }
}
