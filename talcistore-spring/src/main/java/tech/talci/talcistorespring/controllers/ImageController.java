package tech.talci.talcistorespring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ImageController.BASE_URL)
public class ImageController {

    public final static String BASE_URL = "/api/images";

    @PostMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addImage(@PathVariable Long productId) {

    }
}
