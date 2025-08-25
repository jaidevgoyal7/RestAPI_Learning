package com.spring.restfulApiLearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "User First_Name should not be empty")
    private String firstName;

    @NotEmpty(message = "User Last_Name should not be empty")
    private String lastName;

    @NotEmpty(message = "User Email should not be empty")
    @Email(message = "Email address should be valid")
    private String email;

}
