package com.spring.restfulApiLearning.service.impl;

import com.spring.restfulApiLearning.dto.UserDto;
import com.spring.restfulApiLearning.entity.User;
import com.spring.restfulApiLearning.exception.EmailAlreadyExistsException;
import com.spring.restfulApiLearning.exception.ResourceNotFoundException;
import com.spring.restfulApiLearning.mapper.AutoUserMapper;
import com.spring.restfulApiLearning.mapper.UserMapper;
import com.spring.restfulApiLearning.repository.UserRepository;
import com.spring.restfulApiLearning.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //User user = UserMapper.mapToUser(userDto);
        //User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists for user");
        }

        User user = AutoUserMapper.mapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        //return UserMapper.mapToUserDto(savedUser);
        //return modelMapper.map(savedUser, UserDto.class);

        return AutoUserMapper.mapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        //Optional<User> userById = userRepository.findById(id);

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );

        //return UserMapper.mapToUserDto(userById.get());
        //return modelMapper.map(userById.get(), UserDto.class);

        //return AutoUserMapper.mapper.mapToUserDto(userById.get());

        return AutoUserMapper.mapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        //return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        //return allUsers.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return allUsers.stream().map(AutoUserMapper.mapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {

        //User exsistingUser = userRepository.findById(user.getId()).get();
        User exsistingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", user.getId())
        );
        exsistingUser.setFirstName(user.getFirstName());
        exsistingUser.setLastName(user.getLastName());
        exsistingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(exsistingUser);

        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);

        return AutoUserMapper.mapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User exsistingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
        userRepository.deleteById(id);
    }


}
