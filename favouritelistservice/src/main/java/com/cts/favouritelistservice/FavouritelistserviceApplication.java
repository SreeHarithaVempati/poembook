package com.cts.favouritelistservice;


import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cts.favouritelistservice.filter.JwtFilter;
import com.cts.favouritelistservice.repository.PoemRepository;



@SpringBootApplication
public class FavouritelistserviceApplication {

	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean<Filter> registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(FavouritelistserviceApplication.class, args);
	}

}
