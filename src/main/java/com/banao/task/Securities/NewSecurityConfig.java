package com.banao.task.Securities;

import com.banao.task.JwtUtils.JwtFilter;
import com.banao.task.Services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class NewSecurityConfig {

    // Security Config for API ENDPOINTS
    @Order(1)
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        JwtFilter jwtFilter;

        @Autowired
        MyUserDetailService myUserDetailService;
        @Autowired
        EntryPoint entryPoint;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(myUserDetailService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").csrf().disable()
                    .authorizeRequests().antMatchers("/api/auth/login").permitAll().
                    anyRequest().authenticated().and().
                    exceptionHandling().authenticationEntryPoint(entryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    // Security Config for WEB MVC ROUTES
    @Order(2)
    @Configuration
    public static class MVCSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Autowired
        MyUserDetailService myUserDetailService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(myUserDetailService);
        }

        @Autowired
        SuccessAuthHandler successAuthHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**").csrf().and().authorizeRequests()
                    .antMatchers("/signup", "/resources/**", "/process-user", "/verify-user")
                    .permitAll()
                    .antMatchers("/user/**")
                    .hasAuthority("user")
                    .antMatchers("/**")
                    .hasAuthority("admin")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").successHandler(successAuthHandler).permitAll()
                    .and()
                    .logout().and().exceptionHandling();
        }
    }
}
