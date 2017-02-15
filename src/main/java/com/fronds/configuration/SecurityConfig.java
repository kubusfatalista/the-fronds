package com.fronds.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by Qbek on 2016-12-12.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	/**  bo http://forum.thymeleaf.org/UTF8-charset-problem-td3608879.html 
    	 *   oraz http://stackoverflow.com/questions/20863489/characterencodingfilter-dont-work-together-with-spring-security-3-2-0
    	 *   
    	 *   Jak probowalem rejestrowac typa z polskimi znakami to sie wykrzaczalo BO TAK, mimo ze wszystkie resolvery i widoki mialy
    	 *   ustawiony charset utf-8. Trzeba zrobic takie czary - mozna je wsadzic gdzies indziej, ale wtedy moga nie dzialac hasla
    	 *   z polskimi znakami. CHYBA ? Wsadzenie tego tutaj dziala w kazdym razie
    	 * 
    	 */
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
    	
        http.formLogin().loginPage("/login")
        .usernameParameter("login").passwordParameter("password")
                .defaultSuccessUrl("/myProfile")
                .and().csrf()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                    .anyRequest().permitAll();

    }
}
