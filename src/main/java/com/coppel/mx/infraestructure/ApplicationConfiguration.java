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

package com.coppel.mx.infraestructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class ApplicationConfiguration.
 */
@Configuration
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {

  /**
   * The allow origin.
   */
  @Value("{allow.origin}")
  private String allowOrigin;

  /**
   * The allow mapping.
   */
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
  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
            .info(new Info()
                    .title("Coppel API")
                    .description("Java application for microservices")
                    .version("V 1.0")
                    .license(
                            new License()
                                    .name("Apache License Version 2.0")
                                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
                    .contact(new Contact()
                            .name("coppel.com")
                            .url("https://www.coppel.com/")
                            .email("atencion@coppel.com")));
  }

}
