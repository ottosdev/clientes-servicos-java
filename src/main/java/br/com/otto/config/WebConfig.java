package br.com.otto.config;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;



//@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		
		List<String> all = Arrays.asList("*");
		CorsConfiguration corsConf = new CorsConfiguration();
		corsConf.setAllowedOrigins(all);
		corsConf.setAllowedHeaders(all);
		corsConf.setAllowedMethods(all);
		corsConf.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConf);
		
		CorsFilter corsFilter = new CorsFilter();
		FilterRegistrationBean<CorsFilter> filter= new FilterRegistrationBean<>(corsFilter);
		
		
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return filter;
		
	
	}
	
	
}
