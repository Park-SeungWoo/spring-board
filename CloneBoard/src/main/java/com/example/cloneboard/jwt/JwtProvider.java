package com.example.cloneboard.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secreKey;
    @Value("${jwt.access-token-validity}")
    private long accessTokenValidTime;
    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidTime;

    public String createAccessToken(String email) {
        return this.createToken(email, accessTokenValidTime);
    }

    private String createToken(String email, long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(email);  // create a claim and set payload
        // must set a role

        Key key = Keys.hmacShaKeyFor(secreKey.getBytes());  // encrypt secret with HS256
        Date date = new Date();  // current time

        return "bearer " + Jwts.builder()
                .setClaims(claims)  // set issued user info(email)
                .setIssuedAt(date)  // literally to set issued time(current time)
                .setExpiration(new Date(date.getTime() + tokenValidTime))  // set token validation time
                .signWith(key, SignatureAlgorithm.HS256)  // set hashing algorithm, key (HS256)
                .compact();  // create
    }

    // get email from token
    public String getUserEmail(String token){
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secreKey.getBytes()))
                .build();
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
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

}
