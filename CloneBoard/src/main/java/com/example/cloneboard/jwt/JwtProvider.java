package com.example.cloneboard.jwt;

import com.example.cloneboard.service.jwt.CustomUserDetailService;
import com.example.enums.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final CustomUserDetailService customUserDetailService;
    @Value("${jwt.secret}")
    private String secreKey;
    @Value("${jwt.access-token-validity}")
    private long accessTokenValidTime;
    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidTime;

    public String createAccessToken(String email, UserRole role) {
        return this.createToken(email, role, accessTokenValidTime);
    }

    private String createToken(String email, UserRole role, long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(email);  // create a claim and set payload
        claims.put("roles", role.toString());  // set role

        Key key = Keys.hmacShaKeyFor(secreKey.getBytes());  // encrypt secret with HS256
        Date date = new Date();  // current time

        return "bearer " + Jwts.builder()
                .setClaims(claims)  // set issued user info(email)
                .setIssuedAt(date)  // literally to set issued time(current time)
                .setExpiration(new Date(date.getTime() + tokenValidTime))  // set token validation time
                .signWith(key, SignatureAlgorithm.HS256)  // set hashing algorithm, key (HS256)
                .compact();  // create
    }

    // resolve access token from header
    public String resolveAccessToken(HttpServletRequest reqest){
        if(reqest.getHeader("Authorization") != null)
            return reqest.getHeader("Authorization").substring(7);  // remove "bearer " from token (bearer token)
        return null;
    }

    public boolean validateToken(String token) {
        try{
            Key key = Keys.hmacShaKeyFor(secreKey.getBytes());  // get encrypted secret key to validate token
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)  // to validate token
                    .build()
                    // get claims(body, header, signature) [if signature is different with server's secret key, it will throw a SignatureException]
                    .parseClaimsJws(token);

            // if expiration date is before current time return false or return true
            return !claims.getBody().getExpiration().before(new Date());
        } catch(MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            // We have to pass header and claims also with exception message to init ExpiredJwtException.
            // So to simplify it, throw a JwtException.
            throw new JwtException("JWT has Expired", e);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT is unsupported", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty", e);
        } catch (SignatureException e) {
            throw new SignatureException("JWT signature verification failed", e);
        }
    }

    // 유저의 권한 정보를 security context에 저장하기 위해 token에서 권한정보 빼오기
    // 지금까지는 token의 유효성만 검사했음 따라서 이제 토큰에 저장되어있는 유저정보를 가지고 유저 존재 확인, 권한 확인 진행해야함
    // 그 과정을 이 메서드에서 수행하고 우리가 만든 필터에서 이 함수를 통해 인증된 유저 정보(권한 등등)를 받아 security context에 저장함
    // 저장되어있는 정보를 바탕으로 spring security에서 권한 확인 후 인가 과정 수행
    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // get email from token
    public String getUserEmail(String token){
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secreKey.getBytes()))
                .build();
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }
}
