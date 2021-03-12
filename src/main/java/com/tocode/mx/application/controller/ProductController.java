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
* Nombre de archivo: ProductController.java 
* Autor: salvgonz 
* Fecha de creación: Mar 10, 2021 
*/

package com.tocode.mx.application.controller;

import com.tocode.mx.application.repository.ProductRepository;
import com.tocode.mx.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * The Class ProductController.
 */
@RestController
@Api(
  value = "Produt API", 
  description = "It controls the creation, update, delete and retrieving of products",
  tags = {"Products"})
@RequestMapping(value = "/api")
@CrossOrigin
public class ProductController {

  /** The product repository. */
  @Autowired
  private ProductRepository productRepository;
  
  /**
   * Gets the products.
   *
   * @return the products
   */
  @ApiOperation(value = "Retrieves all products")
  @GetMapping(value = "/products", produces = "application/json")
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> products = productRepository.findAll();    
    return new ResponseEntity<>(products, HttpStatus.OK);
  }
  
  /**
   * Gets the product.
   *
   * @param id the id
   * @return the product
   */
  @ApiOperation(value = "Find a product by Id")
  @GetMapping(value = "/products/{productId}", produces = "application/json")
  public ResponseEntity<Product> getProduct(@PathVariable("productId") String id) {
    Product product = productRepository.findById(id).orElse(null);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }
  
  /**
   * Post product.
   *
   * @param product the product
   * @return the response entity
   */
  @ApiOperation(value = "Create and update a product")
  @PostMapping(value = "/products", produces = "application/json")
  public ResponseEntity<?> postProduct(@RequestBody Product product) {
    productRepository.save(product);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
  
  /**
   * Delete product.
   *
   * @param id the id
   * @return the response entity
   */
  @ApiOperation(value = "Delete a product by Id")
  @DeleteMapping(value = "/products/{productId}", produces = "application/json")
  public ResponseEntity<?> deleteProduct(@PathVariable("productId") String id) {
    productRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
