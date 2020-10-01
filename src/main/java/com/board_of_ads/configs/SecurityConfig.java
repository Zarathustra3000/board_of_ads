package com.board_of_ads.configs;


import com.board_of_ads.models.Role;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
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
                .antMatchers("/", "/google_auth", "/facebook_auth", "/vk_auth", "/login**", "/webjars/**", "/error**", "/api/**").permitAll()
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
//                    ).oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.oidcUserService(this.oidcUserService())));
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

//    public Map<String, Object>  getPrincipal() {
//        Authentication authentication = new Authentication() {
//        }
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        OAuth2User user = token.getPrincipal();
//        Map<String, Object> attributes = user.getAttributes();
//    }



//    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
//        final OidcUserService delegate = new OidcUserService();
//
//        return (userRequest) -> {
//            // Delegate to the default implementation for loading a user
//            OidcUser oidcUser = delegate.loadUser(userRequest);
//
//            OAuth2AccessToken accessToken = userRequest.getAccessToken();
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//            mappedAuthorities.add(new Role("ADMIN"));
//
//            // TODO
//            // 1) Fetch the authority information from the protected resource using accessToken
//            // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities
//
//            // 3) Create a copy of oidcUser but use the mappedAuthorities instead
//            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
//            System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
//            System.out.println(oidcUser.getUserInfo());
//            System.out.println(oidcUser);
//            System.out.println((String) oidcUser.getClaim("sub"));
//            oAuth2Service.auth(oidcUser);
//            return oidcUser;
//        };
//    }
}
