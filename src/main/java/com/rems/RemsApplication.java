package com.rems;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableWebMvc
@SpringBootApplication
// @ComponentScan(basePackages="com.rems")
public class RemsApplication extends SpringBootServletInitializer {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RemsApplication.class);
	}

	public static void main(String[] args) {
		//SpringApplication.run(RemsApplication.class, args);
		
		SpringApplication sa = new SpringApplication(RemsApplication.class);
        sa.setLogStartupInfo(false);
        sa.run(args);
	}

/*	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
*/
	@Bean
	SessionFactory getSessionFactory() {
		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		return entityManagerFactory.unwrap(SessionFactory.class);
	}

}
