/*
* All rights reserved by ToCode. Copyright © 2020
*
* The Company is not bound by any obligation of result regarding the availability
* this source code. The Company reserves the right 
* (i)  to modify, without any prior notice, the features of the code and/or 
* (ii) to suspend, interrupt or limit the access to all or part of the code 
*      without any prior notice, particularly for maintenance purposes.
* This source is protected by intellectual property rights including but not limited 
* to trademarks, copyright, designs, sui generis right of the database producer, etc. 
* and is the exclusive property of the Company.
*
* Nombre de archivo: ApplicationConfiguration.java 
* Autor: salvgonz 
* Fecha de creación: Mar 10, 2021 
*/

package com.tocode.mx.infraestructure;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

/**
 * The Class ApplicationConfiguration.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class ApplicationConfiguration implements WebMvcConfigurer {

  /** The allow origin. */
  @Value("{allow.origin}")
  private String allowOrigin;
  
  /** The allow methods. */
  @Value("allow.methods")
  private String allowMethods;
  
  /** The allow mapping. */
  @Value("allow.mapping")
  private String allowMapping;
  
  
  /**
   * Adds the view controllers.
   *
   * @param registry the registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
      registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
      registry.addRedirectViewController("/api/swagger-resources/configuration/ui", 
          "/swagger-resources/configuration/ui");
      registry.addRedirectViewController("/api/swagger-resources/configuration/security", 
          "/swagger-resources/configuration/security");
      registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
  }

  /**
   * Adds the resource handlers.
   *
   * @param registry the registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/api/swagger-ui.html**")
        .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
      registry.addResourceHandler("/api/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
  
  /**
   * Adds the cors mappings.
   *
   * @param registry the registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(allowMapping)
        .allowedOrigins(allowOrigin)
        .allowedMethods("GET", "POST", "DELETE", "OPTIONS")
        .allowedHeaders("content-type", "Content-Type")         
        .allowCredentials(false).maxAge(3600);
    
  }
  
  
  /**
   * Api info.
   *
   * @return the api info
   */
  ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Stub Products API")
        .description(
            "This API is intended to respond basic HTTP method over a single resource: Product")
        .license("No license").termsOfServiceUrl("Free use")
        .version("V 1.0")
        .contact(new Contact("2code.com.mx", "2code.com.mx", "chava.gnolasco@gmail.com"))
        .build();
  }

  /**
   * Swagger springfox docket.
   *
   * @return the docket
   */
  @Bean
  public Docket swaggerSpringfoxDocket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo()).pathMapping("/")
        .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .ignoredParameterTypes(Pageable.class).ignoredParameterTypes(java.sql.Date.class)
        .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
        .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
        .useDefaultResponseMessages(false);
    docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.tocode.mx")).build();
    return docket;
  }

}
