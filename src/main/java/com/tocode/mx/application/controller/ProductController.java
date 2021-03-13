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
* Nombre de archivo: ProductController.java 
* Autor: salvgonz 
* Fecha de creación: Mar 13, 2021 
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
