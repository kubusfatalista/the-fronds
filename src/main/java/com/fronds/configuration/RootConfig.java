package com.fronds.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Qbek on 2016-12-11.
 */
@Configuration
//@ComponentScan(basePackages = {"com.fronds.web"},
//        excludeFilters = { @ComponentScan.Filter(type= FilterType.ANNOTATION, value= EnableWebMvc.class)})
@ComponentScan(basePackages = {"com.fronds"})
public class RootConfig {
}