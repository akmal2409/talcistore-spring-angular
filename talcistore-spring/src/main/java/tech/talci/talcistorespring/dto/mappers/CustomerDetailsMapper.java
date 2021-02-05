package tech.talci.talcistorespring.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.talcistorespring.dto.CustomerDetailsDto;
import tech.talci.talcistorespring.model.CustomerDetails;
import tech.talci.talcistorespring.model.User;

@Mapper(componentModel = "spring")
public interface CustomerDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    CustomerDetails map(CustomerDetailsDto detailsDto, User user);

    @Mapping(target = "userId", source = "details.user.userId")
    CustomerDetailsDto mapToDto(CustomerDetails details);
}
