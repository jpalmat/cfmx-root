/**
 * 
 */
package ec.com.smx.auth.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ec.com.smx.auth.ws.authentication.AuthAccessDeniedHandler;
import ec.com.smx.auth.ws.authentication.AuthEntryPoint;
import ec.com.smx.auth.ws.authentication.AuthProvider;

/**
 * @author gnolivos
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthProvider authProvider;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private AuthAccessDeniedHandler authAccessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.httpBasic();
		
		http.authorizeRequests()
	         .antMatchers("/auth/login/**").permitAll()
	         .antMatchers("/auth/forgotPassword/**").permitAll()
	         .antMatchers("/auth/validateUserAnswer/**").permitAll()
	         .antMatchers("/auth/changePassword/**").permitAll()
	         .antMatchers("/auth/checkToken/**").authenticated()
	         .antMatchers("/auth/getCorpToken/**").permitAll()
	         .antMatchers("/menu/tree/**").authenticated()
	         .antMatchers("/menu/addFavorite/**").authenticated()
	         .antMatchers("/menu/removeFavorite/**").authenticated();
	         
		http.exceptionHandling()
		.accessDeniedHandler(this.authAccessDeniedHandler)
		.authenticationEntryPoint(this.authEntryPoint);
		
		http.csrf().disable();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authProvider);
    }
    
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
