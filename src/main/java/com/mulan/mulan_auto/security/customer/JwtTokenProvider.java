package com.mulan.mulan_auto.security.customer;

import com.mulan.mulan_auto.entity.customer.Customer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.mulan.mulan_auto.security.SecurityConstants.*;

@Component
public class JwtTokenProvider {

    @Autowired
    private CustomUserDetailsService customerService;

    public String createToken(Customer customer) {
        Claims claims = Jwts.claims().setSubject(customer.getUsername());

        // Thêm các thông tin tùy chỉnh vào claims
        claims.put("username", customer.getUsername());
        claims.put("email", customer.getEmail());
        claims.put("phone", customer.getPhone());
        claims.put("money", customer.getMoney());
        claims.put("otp", customer.getOtp());
        claims.put("enabled", customer.isEnabled());
        claims.put("createdTime", customer.getCreatedTime());
        claims.put("authenticationType", customer.getAuthenticationType());
        claims.put("keyGens", customer.getKeyGens());

        Date now = new Date();
        Date validity = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customerService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid", e);
        }
    }
}