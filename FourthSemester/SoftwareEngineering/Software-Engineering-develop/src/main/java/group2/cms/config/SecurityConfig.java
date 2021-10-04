package group2.cms.config;

import group2.cms.config.customAuthProviders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@ComponentScan("group2.cms")
@EnableGlobalMethodSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CMSUserAuthProvider userCustomAuthProvider;

    @Autowired
    private AuthorAuthProvider authorCustomAuthProvider;

    @Autowired
    private ChairAuthProvider chairCustomAuthProvider;

    @Autowired
    private CoChairAuthProvider coChairCustomAuthProvider;

    @Autowired
    private ListenerAuthProvider listenerCustomAuthProvider;

    @Autowired
    private PCMemberAuthProvider pcMemberCustomAuthProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userCustomAuthProvider);
        auth.authenticationProvider(authorCustomAuthProvider);
        auth.authenticationProvider(chairCustomAuthProvider);
        auth.authenticationProvider(coChairCustomAuthProvider);
        auth.authenticationProvider(listenerCustomAuthProvider);
        auth.authenticationProvider(pcMemberCustomAuthProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/login", "/user/createAdmin")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "ORIGINS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Basic", "Password", "Old", "New"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /*
    We use this bean to Expose our Authentication Manager globally,
    so we can access it anywhere in our application.
    */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
