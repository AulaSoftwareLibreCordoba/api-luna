package software_libre.api_luna.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import software_libre.api_luna.entity.Usuario;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpire;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpire;


    public String generateToken(Usuario user){
        List<String> roles = user.getRoles().stream()
                .filter(r-> r.getActivo().equals("Y"))
                .map(r-> r.getNombre()).toList();

        HashMap<String, Object> claims = new HashMap<>();

        claims.put("roles", roles);
        claims.put("email", user.getEmail());
        claims.put("name", user.getUsername());

        return generateToken(claims, user.getUsername());
    }

    private String generateToken(Map<String, Object> customClaim, String username) {
        return Jwts.builder()
                .claims(customClaim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] KeyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);



        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    public boolean isValidRefreshToken(String token, Usuario user) {
        String username = extractUsername(token);


        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
