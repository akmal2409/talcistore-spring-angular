package tech.talci.talcistorespring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.talcistorespring.dto.CustomerDetailsDto;
import tech.talci.talcistorespring.dto.mappers.CustomerDetailsMapper;
import tech.talci.talcistorespring.exceptions.ResourceNotFoundException;
import tech.talci.talcistorespring.model.CustomerDetails;
import tech.talci.talcistorespring.model.User;
import tech.talci.talcistorespring.repositories.CustomerDetailsRepository;
import tech.talci.talcistorespring.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomerDetailsRepository detailsRepository;
    private final AuthService authService;
    private final CustomerDetailsMapper detailsMapper;

    @Transactional
    public void updateCustomerDetails(CustomerDetailsDto detailsDto) {
        User currentUser = authService.getCurrentUser();

        if (!currentUser.isProfileComplete()) {
            currentUser.setProfileComplete(true);
        }

        CustomerDetails customerDetails = detailsMapper.map(detailsDto, currentUser);

        CustomerDetails savedDetails = detailsRepository.save(customerDetails);

        currentUser.setCustomerDetails(savedDetails);

        userRepository.save(currentUser);
    }

    @Transactional(readOnly = true)
    public CustomerDetailsDto getDetails() {
        User currentUser = authService.getCurrentUser();
        CustomerDetails fetchedDetails = detailsRepository.findByUser(currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Customer details were not found"));


        return detailsMapper.mapToDto(fetchedDetails);
    }
}
