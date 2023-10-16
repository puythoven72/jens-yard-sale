package com.Uypiren.jensyardsale.controller;


import com.Uypiren.jensyardsale.service.UserAuthProvider;
import com.Uypiren.jensyardsale.dto.LoginDto;
import com.Uypiren.jensyardsale.dto.UserDto;
import com.Uypiren.jensyardsale.model.authentication.User;
import com.Uypiren.jensyardsale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
@Autowired
    private UserAuthProvider userAuthProvider;




    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getUserName());
        UserDto userDto = userService.login(loginDto);
        userDto.setToken(userAuthProvider.createToken(userDto.getUserName()));
        return ResponseEntity.ok(userDto);
    }




    @PostMapping("/registerSave")
    public ResponseEntity<UserDto>  registerSave(@RequestBody UserDto user){

        System.out.println(user.getFullName());
        User savedUser = userService.save(user);
        user.setToken(userAuthProvider.createToken(user.getUserName()));
        return ResponseEntity.created(URI.create("/users/)"+savedUser.getId())).body(user);
    }



}