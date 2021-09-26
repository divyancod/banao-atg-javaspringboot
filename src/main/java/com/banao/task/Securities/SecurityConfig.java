//package com.banao.task.Securities;
//
//import com.banao.task.JwtUtils.JwtFilter;
//import com.banao.task.Services.MyUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity(debug = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    JwtFilter jwtFilter;
//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    SuccessAuthHandler successAuthHandler;
//    @Autowired
//    MyUserDetailService myUserDetailService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication().dataSource(dataSource)
////                .passwordEncoder(passwordEncoder)
////                .usersByUsernameQuery("select email,password,enabled from users where email=?")
////                .authoritiesByUsernameQuery("select u.email,r.role from users u,roles r where r.id=u.id AND u.email=?");
//        auth.userDetailsService(myUserDetailService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/signup", "/resources/**", "/process-user", "/verify-user", "/api/login")
//                .permitAll()
//                .antMatchers("/user/**")
//                .hasAuthority("user")
//                .antMatchers("/**")
//                .hasAuthority("admin")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").successHandler(successAuthHandler).permitAll()
//                .and()
//                .logout().and().exceptionHandling().and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
////        http.csrf().disable()
////                .authorizeRequests().antMatchers("/test").permitAll().
////                anyRequest().authenticated().and().
////                exceptionHandling().and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
