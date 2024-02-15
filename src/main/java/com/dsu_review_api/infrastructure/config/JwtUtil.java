package com.dsu_review_api.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    // 시크릿 키? 같은거
    private final String SECRET = "secret";

    /**
     * 토큰 생성
     *
     */
//    public String generateToken(UserDetails userDetails){
//        Claims claims = Jwts.claims();
//        claims.put("username", userDetails.getUsername());
//        return createToken(claims, userDetails.getUsername());
//
//    }

    public String generateToken(String user_number){
        Claims claims = Jwts.claims();
        claims.put("username", user_number);
        return createToken(claims, user_number);

    }


//    private String createToken(Claims claims, String subject){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                .compact();
//    }

    private String createToken(Claims claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 3))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
    /**
     * 토큰 유효 여부 확인
     */
    public Boolean isValidToken(String token){
        log.info("isValidToken token = {}", token);
//        String username=  getUsernameFromToken(token);
        log.info("?? {}",!isTokenExpired(token));

        return (!isTokenExpired(token));
    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token){
        log.info("getAllClaims token = {}", token);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 username 가져오기
     */
    public String getUsernameFromToken(String token){
        String username=  String.valueOf(getAllClaims(token).get("username"));
        log.info("getUsernameFromToken subject = {}", username);
        return  username;
    }


    /**
     * 토큰 만료기한 가져오기
     */
    public Date getExpirationDate(String token){
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }


    /**
     * 토큰이 만료되었는지
     */
    private boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }


}
