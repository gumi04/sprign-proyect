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
* Nombre de archivo: IntegrationTest.java 
* Autor: salvgonz 
* Fecha de creación: Mar 13, 2021 
*/

package com.tocode.mx;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tocode.mx.model.Product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
//import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;



/**
 * The Class IntegrationTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 9999)
@AutoConfigureMockMvc
@Slf4j
public class IntegrationTest {

  /** The mvc. */
  @Autowired
  private MockMvc mvc;
  

  /**
   * <h3>Should retrieve all products.</h3>
   * 
   * <p>
   * <strong>Given</strong> a products portal<br/>
   * <strong>When</strong>  a user wants to view all products<br/>
   * <strong>Then</strong>  an API should be used in order to retrieve a product list<br/>
   * </p>
   * 
   * @throws Exception 
   *
   */
  @Test
  //@Sql({"/my-test-script.sql"})
  public void shouldRetrieveAllProducts() throws Exception {
    log.info("Retrieving all products");
    
    String productApi = "/api/products";
    
    MvcResult result = mvc.perform(get(productApi)
        .contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn();
    
    ObjectMapper mapper = new ObjectMapper();
    List<Product> products =  mapper.readValue(
        result.getResponse().getContentAsByteArray(),
        new TypeReference<List<Product>>() {});
    
    Product product = products.get(0);
    
    assertEquals(3, products.size());
    assertNotNull(product);
    assertNotNull(product.getId());    
    assertNotNull(product.getDescription());
    assertNotNull(product.getName());
    assertNotNull(product.getImage());
  }
  
  
  /**
   * <h3>Should retrieve one product.</h3>
   * 
   * <p>
   * <strong>Given</strong> a products portal<br/>
   * <strong>When</strong>  a user wants to view a product detail<br/>
   * <strong>Then</strong>  an API should be used in order to retrieve the product information <br/>
   * </p>
   *
   * @throws Exception the exception
   */
  @Test
  public void shouldRetrieveOneProduct() throws Exception {
    
    String productApi = "/api/products/B0002CZV1E";
    
    MvcResult result = mvc.perform(get(productApi)
        .contentType("application/json"))
        .andExpect(status().isOk())
        .andReturn();
    
    ObjectMapper mapper = new ObjectMapper();
    Product product =  mapper.readValue(
        result.getResponse().getContentAsByteArray(), Product.class);
    
    Product productExpected = new Product();
    productExpected.setId("B0002CZV1E");
    productExpected.setDescription("Tamaño:Large.");
    productExpected.setName("BOSS BCB-60 Funda/Estuche");
    productExpected.setPrice(Double.valueOf(5199.00));
    productExpected
      .setImage("https://images-na.ssl-images-amazon.com/images/I/51PeCTvXfiL._AC_.jpg");
     
    assertNotNull(product);
    assertEquals(productExpected.getId(), product.getId());
    assertEquals(productExpected.getName(), product.getName());
    assertEquals(productExpected.getDescription(), product.getDescription());
    assertEquals(productExpected.getImage(), product.getImage());
    assertEquals(productExpected.getPrice(), product.getPrice());
  }
}

