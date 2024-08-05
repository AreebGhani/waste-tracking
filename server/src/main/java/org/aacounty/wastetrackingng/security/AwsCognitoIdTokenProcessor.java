package org.aacounty.wastetrackingng.security;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
//import jakarta.servlet.http.HttpServletRequest; // Use jakarta for java 17
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import org.aacounty.wastetrackingng.configurations.JwtConfiguration;
import org.aacounty.wastetrackingng.entity.alluserdetails.AllUserDetails;
import org.aacounty.wastetrackingng.entity.alluserdetails.AllUserDetailsRepository;

@Component
public class AwsCognitoIdTokenProcessor {

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @SuppressWarnings("rawtypes")
    @Autowired
    private ConfigurableJWTProcessor configurableJWTProcessor;

    @Autowired
    private AllUserDetailsRepository repo;

    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        if (idToken != null) {
            @SuppressWarnings("unchecked")
            JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken), null);
            validateIssuer(claims);
            verifyIfIdToken(claims);
            String username = getUserNameFrom(claims);
            List<GrantedAuthority> grantedAuthorities = getAuthoritiesFromUserRoles(username);
            User user = new User(username, "", grantedAuthorities);
            System.out.print("User: ");
            System.out.println(user);
            return new JwtAuthentication(username, claims, grantedAuthorities);
        }
        return null;
    }

    private List<GrantedAuthority> getAuthoritiesFromUserRoles(String username) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("username here");
        System.out.println(username);
        AllUserDetails user = repo.findAllByAuEmailAddress(username);

        if (user.getRoleName().equalsIgnoreCase("Admin")) {

            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // Map isCommissioner to "ROLE_ADMIN" authority
        }
        if (user.getRoleName().equalsIgnoreCase("Power User")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_Power_User"));
        }
        if (user.getRoleName().equalsIgnoreCase("Supervisor")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_Supervisor"));
        }
        if (user.getRoleName().equalsIgnoreCase("Guest")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_Guest"));
        }

        return authorities;
    }

    public String extractUsername(HttpServletRequest request) throws Exception {
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        // System.out.println("idToken: " + idToken);
        @SuppressWarnings("unchecked")
        JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken), null);
        String username = getUserNameFrom(claims); // is email
        String[] usernameArr = username.split("@", 2);
        System.out.println("username: " + usernameArr[0]);
        return usernameArr[0];

    }

    private String getUserNameFrom(JWTClaimsSet claims) {
        return claims.getClaims().get(this.jwtConfiguration.getEmailField()).toString();
    }

    private void verifyIfIdToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception("JWT Token is not an ID Token");
        }
    }

    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(),
                    this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }

    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }
}