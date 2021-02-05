package tech.talci.talcistorespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.talci.talcistorespring.dto.CustomerDetailsDto;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    public static final String BASE_URL = "/api/users";
//    private final UserService userService;

    @PostMapping
    public void updateCustomerDetails(@RequestBody @Valid CustomerDetailsDto detailsDto) {

    }
}
