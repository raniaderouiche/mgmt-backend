package tn.telecom.mgmtbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().antMatchers(HttpMethod.GET,"/sector/**").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET,"/organization/**").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users/**").permitAll();
//
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/sector/**").hasAnyAuthority("SUPER_ADMIN","ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.POST,"/sector/**").hasAnyAuthority("ADMIN","SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/sector/**").hasAnyAuthority("ADMIN","SUPER_ADMIN");
//
//        http.authorizeRequests().antMatchers(HttpMethod.POST,"/organization/**").hasAnyAuthority("SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/organization/**").hasAnyAuthority("SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/organization/**").hasAnyAuthority("SUPER_ADMIN");
//
//        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/users/**").hasAnyAuthority("SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/users/**").hasAnyAuthority("SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/users/**").hasAnyAuthority("SUPER_ADMIN");
//
//        http.authorizeRequests().antMatchers("/profession").hasAnyAuthority("SUPER_ADMIN","ADMIN");
//        http.authorizeRequests().antMatchers("/type").hasAnyAuthority("SUPER_ADMIN","ADMIN");

        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
        configuration.setAllowCredentials(false);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}