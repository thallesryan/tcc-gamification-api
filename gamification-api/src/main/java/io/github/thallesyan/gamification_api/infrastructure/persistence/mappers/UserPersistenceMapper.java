package io.github.thallesyan.gamification_api.infrastructure.persistence.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", expression = "java(convertDateToLocalDate(user.getDateOfBirth()))")
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserJPA toUserJPA(User user);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dateOfBirth", expression = "java(convertLocalDateToDate(userJPA.getDateOfBirth()))")
    User toUser(UserJPA userJPA);

    default LocalDate convertDateToLocalDate(Date date) {
        return date != null ? date.toInstant().atZone(java.time.ZoneOffset.UTC).toLocalDate() : null;
    }

    default Date convertLocalDateToDate(LocalDate localDate) {
        return localDate != null ? Date.from(localDate.atStartOfDay(java.time.ZoneOffset.UTC).toInstant()) : null;
    }
}
