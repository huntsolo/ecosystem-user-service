package com.careydevelopment.ecosystem.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.careydevelopment.ecosystem.user.service.IpLogService;
import com.careydevelopment.ecosystem.user.service.UserService;
import com.careydevelopment.ecosystem.user.util.JwtUtil;

import us.careydevelopment.ecosystem.jwt.config.CredentialsAndJwtSecurityConfig;
import us.careydevelopment.ecosystem.jwt.util.RecaptchaUtil;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends CredentialsAndJwtSecurityConfig {
    
    @Value("${recaptcha.project.id}")
    private String projectID;
    
    @Value("${recaptcha.site.key}")
    private String siteKey;
    
    
    @Override
    protected String[] permitAllUrls() {
        String[] permitAll = { 
                                "/registrant", 
                                "/simpleSearch", 
                                "/emailVerificationStatus", 
                                "/smsVerificationStatus", 
                                "/", 
                                "/session"
                              };
        return permitAll;
    }
    
    
    @Bean
    public RecaptchaUtil recaptchaUtil() {
        return new RecaptchaUtil(projectID, siteKey);
    }
    
    
    public WebSecurityConfig(@Autowired UserService jwtUserDetailsService, 
            @Autowired JwtAuthenticationProvider jwtAuthenticationProvider, @Autowired JwtUtil jwtUtil,
            @Autowired IpLogService ipLogService) {
        
        this.authenticationProvider = jwtAuthenticationProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.ipTracker = ipLogService;
    }        
        
}