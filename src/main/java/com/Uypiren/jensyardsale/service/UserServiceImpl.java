package com.Uypiren.jensyardsale.service;


import com.Uypiren.jensyardsale.dto.LoginDto;
import com.Uypiren.jensyardsale.dto.UserDto;
import com.Uypiren.jensyardsale.exception.AppException;
import com.Uypiren.jensyardsale.model.authentication.User;
import com.Uypiren.jensyardsale.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public UserDto findByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));
        UserDto userDto = new UserDto(user.getUserName(), user.getPassword(), user.getRole(), user.getFullName());
        return userDto;
    }

    @Override
    public User save(UserDto userDto) {
        System.out.println(userDto.getUserName());
        Optional<User> optionalUser = userRepository.findByUserName(userDto.getUserName());
        if (optionalUser.isPresent()) {
            throw new AppException("User Already Exists", HttpStatus.CONFLICT);
        }

        User user = new User(userDto.getUserName(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(), userDto.getFullName());
        System.out.println(user.getUserName());
        return userRepository.save(user);

    }


    @Override
    public UserDto login(LoginDto loginDto) {
        System.out.println(loginDto.getUserName() + loginDto.getPassword());
        UserDto userDto = findByUserName(loginDto.getUserName());
        if (passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
            return userDto;
        }
        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }
}
