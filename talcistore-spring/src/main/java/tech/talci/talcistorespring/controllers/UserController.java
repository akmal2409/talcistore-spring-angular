package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.talci.talcistorespring.dto.CustomerDetailsDto;
import tech.talci.talcistorespring.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    public static final String BASE_URL = "/api/users";
    private final UserService userService;

    @PostMapping
    public void updateCustomerDetails(@RequestBody @Valid CustomerDetailsDto detailsDto) {
        userService.updateCustomerDetails(detailsDto);
    }

    @GetMapping
    public CustomerDetailsDto getCustomerDetails() {
        return userService.getDetails();
    }
}
