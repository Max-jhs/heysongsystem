package com.heysong.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.heysong.domain.LoginUser;
import com.heysong.domain.TUser;


import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class JWTUtils {
    public static final String SECRET = "A$VS#HH";
    public static String createJwt(String data) {

        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                .withHeader(header)
                .withClaim("user", data)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean checkToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim user = decodedJWT.getClaim("user");
        return user.asString();
    }

    public static Integer parseTokenUserId(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Claim user = decodedJWT.getClaim("user");

        LoginUser loginUser = JSONUtils.jsonToBean(user.asString(), LoginUser.class);
        return loginUser.getId();
    }

    public static LoginUser parseTokenUser(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        Claim user = decodedJWT.getClaim("user");
        return JSONUtils.jsonToBean(user.asString(), LoginUser.class);
    }
}
