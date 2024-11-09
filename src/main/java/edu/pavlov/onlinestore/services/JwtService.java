package edu.pavlov.onlinestore.services;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.Map;

//@ConfigurationProperties(prefix = "jwt")

//@Service
//@Configuration
public class JwtService {

    /**
     * The secret used to sign the JWT.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * The expiration time of the token in milliseconds.
     */
    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * The key used to sign the JWT.
     */
    private SecretKey key;

    /*
    @PostConstruct
    protected void init() {
        // key creation
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    */

    /*
    public String generateJWTToken(Map<String, Object> extraClaims,
        String username) {

        // create the JWT token
        String jwt = Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();

        return jwt;
    }
*/

}
