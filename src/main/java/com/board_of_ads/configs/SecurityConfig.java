package com.board_of_ads.configs;


import com.board_of_ads.service.impl.OAuth2Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final OAuth2Service oAuth2Service;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, OAuth2Service oAuth2Service) {
        this.userDetailsService = userDetailsService;
        this.oAuth2Service = oAuth2Service;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/css/*.css",
                "/js/*.js",
                "/images/*.jpg"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/auth", "/google_auth", "/facebook_auth", "/vk_auth", "/yandex_auth", "/login**", "/webjars/**", "/error**", "/api/**").permitAll()
                .antMatchers("/admin_page").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                .and()
                    .logout().permitAll()
                .and()
                    .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                ).oauth2Login();
    }
    //при проверки для внесения в БД зашифрованного пароля используйте: https://bcrypt-generator.com/

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
