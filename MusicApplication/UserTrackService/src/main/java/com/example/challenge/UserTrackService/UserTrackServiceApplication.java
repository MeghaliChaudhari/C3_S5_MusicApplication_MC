package com.example.challenge.UserTrackService;

import com.example.challenge.UserTrackService.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserTrackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTrackServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterBeand(){
		FilterRegistrationBean filterreg = new FilterRegistrationBean();
		filterreg.setFilter(new JwtFilter());
		filterreg.addUrlPatterns("/usertrack/app/addtrack/*");
		return filterreg;
	}
}
