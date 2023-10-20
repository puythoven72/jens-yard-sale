package com.Uypiren.jensyardsale.service;


import com.Uypiren.jensyardsale.dto.UserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class UserAuthProvider {

    private final UserService userService;


    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

    }


    public String  createToken(String userName){
        Date now = new Date();
        Date validTill = new Date(now.getTime()+3_600_000);
        return JWT.create().withIssuer(userName)
                .withIssuedAt(now)
                .withExpiresAt(validTill)
                .sign(Algorithm.HMAC256(secretKey));
    }


    public Authentication validateToken(String token){
        JWTVerifier verifier= JWT.require(Algorithm.HMAC256(secretKey))
                .build();
        DecodedJWT decoded =verifier.verify(token);
        UserDto userDto = userService.findByUserName(decoded.getIssuer());
        return new UsernamePasswordAuthenticationToken(userDto,null, Collections.emptyList());

    }

}
