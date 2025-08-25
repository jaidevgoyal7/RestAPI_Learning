package com.spring.restfulApiLearning.mapper;

import com.spring.restfulApiLearning.dto.UserDto;
import com.spring.restfulApiLearning.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper mapper = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
