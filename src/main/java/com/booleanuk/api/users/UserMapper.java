package com.booleanuk.api.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "rentedVideoGames", ignore = true)
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);
}