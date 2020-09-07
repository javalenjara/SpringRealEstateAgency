package co.com.udem.rea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import co.com.udem.rea.security.jwt.JwtSecurityConfigurer;
import co.com.udem.rea.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
    	httpSecurity
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/users/addUser**").permitAll().and()
                .authorizeRequests().antMatchers("/auth/signin").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll() //This is disabled to allow access to H2 web console.
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    	
        //This is disabled to allow access to H2 web console. Shall not be configured this way in production environment.
    	//To-Do Delete this when deploying in production. 
        httpSecurity.headers().frameOptions().disable();
    }
}