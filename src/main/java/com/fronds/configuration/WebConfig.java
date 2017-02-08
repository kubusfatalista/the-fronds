package com.fronds.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan("com.fronds")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setSuffix(".jsp");
    	resolver.setPrefix("/WEB-INF/views/");
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    /**
     * 
 	 *@Override
	 *public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
     *	configurer.enable();
	 *}
     * 
     * 	trzeba zdisablowac DefaultServletHandlerConfigurera, jak chcemy dorzucic swoja strone do handlowania wyjatkow (np 404)
     *  ta ktora dodalem w {@link PageNotFoundExceptionController}
     * 	
     */
}