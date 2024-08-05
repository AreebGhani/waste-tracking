package org.aacounty.wastetrackingng.security;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class AwsCognitoJwtAuthFilter extends GenericFilter {

    private static final Log logger = LogFactory.getLog(AwsCognitoJwtAuthFilter.class);
    private AwsCognitoIdTokenProcessor cognitoIdTokenProcessor;

    public AwsCognitoJwtAuthFilter(AwsCognitoIdTokenProcessor cognitoIdTokenProcessor) {
        this.cognitoIdTokenProcessor = cognitoIdTokenProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication;

        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse response2=(HttpServletResponse)response;
        if(httpServletRequest.getHeader("Authorization")!=null) {
        	
        System.out.println(httpServletRequest.getHeader("Authorization"));
        
        }
        try {
            authentication = this.cognitoIdTokenProcessor.authenticate(httpServletRequest);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                response2.addHeader("Authorization",
                		httpServletRequest.getHeader("Authorization"));
            }
        } catch (Exception var6) {
            logger.error("Cognito ID Token processing error", var6);
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}

