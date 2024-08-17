package com.sipc.clockin.utils;




import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.sipc.clockin.pojo.domain.PO.User;
import com.sipc.clockin.pojo.model.TokenModel;

import java.util.Date;

public class JwtUtils {
    public static final long EXPIRE_TIME = (long) 1000 * 60 * 60 * 24 * 30;
    public static final String SECRET = "SIPC115";

    public static String sign(User userInfo) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withClaim("workId",userInfo.getWorkId())
                .withClaim("email",userInfo.getEmail())
                .withClaim("name",userInfo.getName())
                .withClaim("role",userInfo.getRole())
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static TokenModel getTokenModelByToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setWorkId(decodedJWT.getClaim("workId").asInt());
        tokenModel.setEmail(decodedJWT.getClaim("email").asString());
        tokenModel.setName(decodedJWT.getClaim("name").asString());
        tokenModel.setRole(decodedJWT.getClaim("role").asString());
        return tokenModel;
    }

}