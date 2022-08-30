package com.mieggo.demoapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends
        WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity
                // Disable csrf protection
                .csrf().disable()

                // don't create session
                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                .antMatchers(
                        HttpMethod.OPTIONS
                ).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();;

        // disable page caching
        httpSecurity.headers().cacheControl().disable();
    }
}