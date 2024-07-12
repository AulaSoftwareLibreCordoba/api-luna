package software_libre.api_luna.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import software_libre.api_luna.dto.UserDto;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRET = "4321secretoequiposoftwarelibreperonoEsSuficientementeLargo1234";

    public String generateToken(UserDto user){
        return generateToken(new HashMap<>(), user.getEmail());
    }

    private String generateToken(Map<String, Object> customClaims, String username) {
        return Jwts.builder()
                .claim("email",username)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] KeyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
}
