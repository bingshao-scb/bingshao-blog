package com.bs.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bs.common.bo.LoginUserBo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * @author bingshao
 * @date 2021/8/19
 **/
@Configuration
public class JwtTokenUtils {

    public static String privateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA2lEcXBBfWfZm+qom" +
            "Fc3/3vkSNH88pj4jFoF5TEag3pGhGjICtkluh2QLCO+Vsk6yknBrIeY8njWBHnV6" +
            "fhfiwQIDAQABAkAdKUxyd7Vk3ObwgJ3Jt9R/wiFOZdiCFDa3oN/E8oI2eFonmOdy" +
            "OvskxfdNtTrANgClIX8ZqnSlc8P4KCqdLvYBAiEA9sLKZCduQYs9ivD7tN8obkHr" +
            "YAEqr57y5BhU6LB8xHECIQDifa7imrd1SGK9g9i6AWQfJEyJqTJyY1IDNDYg/+rr" +
            "UQIhAMC4ATmXUhT+mmIZM3xfjUQPV7TzNFNU4H4kQqw4qjkBAiEArJ631LOwkW3a" +
            "+6/qVVHMGkQTJXRUI4R5NEHt41bJmBECIQC2gdZpUHsjaw0rvJltPLFil2Q5I5bj" +
            "tuu56vI93dTuJA==";

    public static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANpRHFwQX1n2ZvqqJhXN/975EjR/PKY+" +
            "IxaBeUxGoN6RoRoyArZJbodkCwjvlbJOspJwayHmPJ41gR51en4X4sECAwEAAQ==";

    public static RSAPublicKey rsaPublicKey;
    public static RSAPrivateKey rsaPrivateKey;


    /**
     * 获取公钥实例
     * @return 公钥实例
     * @throws NoSuchAlgorithmException 算法不可用
     * @throws InvalidKeySpecException 无效的key
     */
    public static RSAPublicKey getRSAPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] buffer = Base64.decodeBase64(publicKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        X509EncodedKeySpec keySpec =new X509EncodedKeySpec(buffer);

        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);

        return publicKey;
    }

    /**
     * 获取私钥实例
     * @return 私钥实例
     * @throws NoSuchAlgorithmException 算法不可用
     * @throws InvalidKeySpecException 无效的key
     */
    public static RSAPrivateKey getRsaPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] buffer = Base64.decodeBase64(privateKey);

        PKCS8EncodedKeySpec keySpec =new PKCS8EncodedKeySpec(buffer);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    /**
     * 加密获取token
     * @param loginUserBo 登录用户信息
     * @return token
     */
    public static String creatToken(LoginUserBo loginUserBo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        JwtTokenUtils.rsaPrivateKey = JwtTokenUtils.getRsaPrivateKey();
        JwtTokenUtils.rsaPublicKey = JwtTokenUtils.getRSAPublicKey();
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey,rsaPrivateKey);
        return JWT.create()
                .withExpiresAt(DateUtils.addDays(new Date(), 7))
                .withClaim("userId",loginUserBo.getUserId())
                .withClaim("userType",loginUserBo.getUserType())
                .sign(algorithm);
    }

    /**
     * 验证jwt并获取userId
     * @param token
     */
    public static LoginUserBo verifierToken(String token) throws InvalidKeySpecException, NoSuchAlgorithmException {
        JwtTokenUtils.rsaPrivateKey = JwtTokenUtils.getRsaPrivateKey();
        JwtTokenUtils.rsaPublicKey = JwtTokenUtils.getRSAPublicKey();
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey,rsaPrivateKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Long userId = jwt.getClaim("userId").asLong();
        Integer userType = jwt.getClaim("userType").asInt();
        LoginUserBo loginUserBo = new LoginUserBo();
        loginUserBo.setUserId(userId);
        loginUserBo.setUserType(userType);
        return loginUserBo;
    }
}
