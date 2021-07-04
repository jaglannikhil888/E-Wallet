package com.gfg.jbdl12majorproject.TransactionManagementSystem;


import com.gfg.jbdl12majorproject.TransactionManagementSystem.manager.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userManager;

    @Bean
    PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userManager)
                .passwordEncoder(bCryptPasswordEncoder())
                .and()
                .inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("password"))
                .authorities("admin");
    }

    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/transaction/**")
                .authenticated()
                .antMatchers("/signUp/**")
                .permitAll()
                .antMatchers("/user/**")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable();
    }

}
