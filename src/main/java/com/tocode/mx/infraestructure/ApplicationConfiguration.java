/*
*                     GNU GENERAL PUBLIC LICENSE
*                        Version 3, 29 June 2007
* 
*  Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
*  Everyone is permitted to copy and distribute verbatim copies
*  of this license document, but changing it is not allowed.
* 
*                             Preamble
* 
*   The GNU General Public License is a free, copyleft license for
* software and other kinds of works.
* 
*   The licenses for most software and other practical works are designed
* to take away your freedom to share and change the works.  By contrast,
* the GNU General Public License is intended to guarantee your freedom to
* share and change all versions of a program--to make sure it remains free
* software for all its users.  We, the Free Software Foundation, use the
* GNU General Public License for most of our software; it applies also to
* any other work released this way by its authors.  You can apply it to
* your programs, too.
*
* Nombre de archivo: ApplicationConfiguration.java 
* Autor: salvgonz 
* Fecha de creación: Mar 13, 2021 
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
