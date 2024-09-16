package com.booleanuk.api.videoGames;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoGameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "loanHistory", ignore = true)
    VideoGame toEntity(VideoGameDTO videoGameDTO);

    VideoGameDTO toDTO(VideoGame videoGame);
}
