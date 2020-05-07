/**
 * 
 */
package ec.com.smx.auth.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.com.smx.auth.ws.authentication.AuthLoginService;
import ec.com.smx.auth.ws.authentication.AuthManager;

/**
 * @author gnolivos
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={
		DataSourceAutoConfiguration.class, 
		HibernateJpaAutoConfiguration.class, 
		JooqAutoConfiguration.class})
public class CfmxSystemStarter extends SpringBootServletInitializer {
	
	@Bean
    public ObjectMapper objectMapper() {
    	return new ObjectMapper(); 
    }
    
    @Bean
	public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
	@Bean
	public AuthManager authManager() {
	    return new AuthManager();
	}
	
	@Bean
	public AuthLoginService authLoginService() {
	    return new AuthLoginService();
	}
	
	 /**
	   * Start spring boot for external server.
	   *
	   * @param builder The builder
	   * @return
	   */
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(CfmxSystemStarter.class);
	  }
	  
	  /**
	   * Start spring boor for embed server.
	   *
	   * @param args the args of main
	   */
	  public static void main(String[] args) {
		  SpringApplication.run(CfmxSystemStarter.class, args);
	  }
	  
	  /**
	   * Custom path.
	   *
	   * @return EmbeddedServletContainerCustomizer
	   * @since 1.0.0
	   */
	  @Bean
	  public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//	      return container -> container.setContextPath("/authProvider");
		  return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setContextPath("/authProvider");
			}
		};
	  }

}
