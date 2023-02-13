package com.back.support;

import com.back.admin.domain.LoginEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUtils {

    @Autowired
    private JwtUtils jwtUtils;

    private String secretKey = "reolino";

    @Value("${token.long}")
    String tokenLong;

    // 토큰 유효시간 4시간
    private long tokenValidTime = 4 * 60 * 60 * 1000L;

    public String generateToken(LoginEntity loginEntity) {
        // 토큰 유효시간 1년으로 처리
        if("Y".equals(tokenLong)){
            tokenValidTime = 30 * 24 * 60 * 60 * 12 * 1000L;
        }
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loginEntity);
    }

    public String generateRefreshToken(LoginEntity loginEntity) {
        Map<String, Object> claims = new HashMap<>();
        return createRefreshToken(claims, loginEntity);
    }

    private String createToken(Map<String, Object> claims, LoginEntity loginEntity) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(loginEntity.login_id)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
            .claim("user_id", loginEntity.user_id)
            .claim("login_id", loginEntity.login_id)
            .claim("auth_id", loginEntity.auth_id)
            .claim("user_nm", loginEntity.user_nm)
            .claim("user_pw", loginEntity.user_pw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private String createRefreshToken(Map<String, Object> claims, LoginEntity loginEntity) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(loginEntity.login_id)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime * 6))
            .claim("user_id", loginEntity.user_id)
            .claim("login_id", loginEntity.login_id)
            .claim("auth_id", loginEntity.auth_id)
            .claim("user_nm", loginEntity.user_nm)
            .claim("user_pw", loginEntity.user_pw)
            .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    public String getTokenInfo(String token, String field) {
        return (String) getClaims(token).get(field);
    }


    public Authentication getAuthentication(String token) {
        String loginId = jwtUtils.getTokenInfo(token,"login_id");
        String userPw = jwtUtils.getTokenInfo(token,"user_pw");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(loginId, userPw, new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidTime(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().toString();
        } catch (Exception e) {
            return "";
        }
    }
}

