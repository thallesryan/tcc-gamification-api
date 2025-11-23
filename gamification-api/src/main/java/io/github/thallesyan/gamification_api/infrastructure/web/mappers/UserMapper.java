package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.UserRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.Date;

@Mapper(componentModel = "spring", uses = {PlatformMapper.class})
public interface UserMapper {

    @Mapping(target = "identifier", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", expression = "java(convertLocalDateToDate(userRequestDTO.getDateOfBirth()))")
    User toUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "platform", source = "platform.name")
    UserResponseDTO toUserResponseDTO(User user);

    default Date convertLocalDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(java.time.ZoneOffset.UTC).toInstant());
    }
}
