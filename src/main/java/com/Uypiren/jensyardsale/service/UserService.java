package com.Uypiren.jensyardsale.service;

import com.Uypiren.jensyardsale.dto.LoginDto;
import com.Uypiren.jensyardsale.dto.UserDto;
import com.Uypiren.jensyardsale.model.authentication.User;

public interface UserService {
    UserDto findByUserName(String userName);

    User save(UserDto userDto);


    UserDto login(LoginDto loginDto);
}
