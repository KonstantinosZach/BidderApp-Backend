package com.bidder.BidderApp.Security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bidder.BidderApp.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateAccessToken(User user) {
        List<String> roles = new ArrayList<>();
        roles.add(SecurityConstants.USER_ROLE);
        if(user.getAdmin())
            roles.add(SecurityConstants.ADMIN_ROLE);
        if(user.getSeller() != null)
            roles.add(SecurityConstants.SELLER_ROLE);
        if(user.getBidder() != null)
            roles.add(SecurityConstants.BIDDER_ROLE);

        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getUsername(), user.getEmail()))
                .setIssuer("BidderApp")
                .claim("roles", roles.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {return parseClaims(token).getSubject();}

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
