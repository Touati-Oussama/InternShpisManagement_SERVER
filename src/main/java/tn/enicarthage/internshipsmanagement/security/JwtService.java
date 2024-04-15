package tn.enicarthage.internshipsmanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    //private static final String SECRET_KEY= "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(au ->{
            roles.add(au.getAuthority());
        });
        extraClaims.put("role", roles.get(0));
        return  Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ SecParams.EXP_TIME))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername()) && !isTokenExpired(token) && userDetails.isEnabled();
    }

    private boolean isTokenExpired(String token) {
        return expirationToken(token).before(new Date());
    }

    private Date expirationToken(String token){
        return  extractClaim(token, Claims::getExpiration);
    }

    public <T>T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.
                parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecParams.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
