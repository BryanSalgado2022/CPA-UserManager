package com.cpausermanager.cpa_user_manager.Config;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    //TODO esto debe ir en un .env
    private static final String SECRET_KEY = "d4af9223b6496d641013dc3af9529f509b30ca7aadc8cc0ba7de7f6b3eaa110d64b69c2d3e664e949bd01323b9aa75aa8502a165807d1588fdc4fa9689a9ac8113d043c3b50cffd52551e14babd35d56dcb0210ea2dab5324cbb76bba0908742608ee22262d74c9421740b28e5e65e362eca1111a8fdcce4b8328f28fc35e4ab29014ebb6b053ffd296179b5c7dd3c09a1b69bd41bebeeadd162a1c83e3cb5a672a2eb5a5d6d111856d16d0128b19f50c048f0617d7fdb50938addce25b3bbd855230dace9377629884520f3e13d7ff8c5ab8f57f66b09738426ff2e290b2de68392076b9758791dbd6a9736e828c5454f4f1bc69e10f43cf86e957300b53f5b";

    public String getUser(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUser(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {return getExpiration(token).before(new Date());}

    private Date getExpiration(String token) {return getClaim(token, Claims::getExpiration);}
}
