package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.service.UserPrinciple;

import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
	
	/*private static final Logger Logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	 
	@Value("${jwtSecret}")
	
	private String jwtSecret;
	
	@Value("${jwtExpirationMs}")
	
	private int jwtExpirationMs;
	
	
	
	public String generateJwtToken(Authentication authentication) {
		System.out.println(jwtSecret);
		System.out.println(jwtExpirationMs);
		MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipale.getEmail()))
                .setIssuedAt(new Date())
                		.setExpiration(new Date()).getTime() + jwtExpirationMs))
.signWith(SignatureAlgorithm.HSS12, jwtSecret)
.compact();
		}
	
	
	public String getUserNameFromJwtToken(String token)
	{
		return Jwts.parser().setSigninkey(jwtSecret).parseClaimsJws(token).getBody()
	}
	
	
	public boolean ValidateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigninKey(jwtSecret).parseClaimsJws(authToken);
			return true;
			
		}catch (SignatureException e) {
			Logger.error("Invalid JWT signature: {}", e.getMessage());
			
		}catch(MalformedJwtException e ) {
			Logger.error("Invalid JWT Token: {}", e.getMessage());
		}
		catch(ExpiredJwtException e ) {
			Logger.error("Jwt Token is expired: {}", e.getMessage());
			
		}catch(UnsupportedJwtException e ) {
			Logger.error("Jwt Token is unsupported: {}", e.getMessage());
			
		}catch(IllegalArgumentException e ) {
			Logger.error("Jwt claims string is empty: {}", e.getMessage());
		}
		
		
		
	}*/
	

}
