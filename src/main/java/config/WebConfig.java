package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
		// Allow CORS for all endpoints
		registry.addMapping("/**") // Enable CORS for all API endpoints
				.allowedOrigins("http://localhost:3000") // Allow requests from React app (localhost:3000)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
				.allowedHeaders("Authorization", "Content-Type", "Accept") // Allow these headers
				.allowCredentials(true) // Allow sending credentials (cookies)
				.maxAge(3600); // Cache preflight request for 1 hour
    }

}
