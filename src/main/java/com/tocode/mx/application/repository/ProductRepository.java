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
* Nombre de archivo: ProductRepository.java 
* Autor: salvgonz 
* Fecha de creación: Mar 10, 2021 
*/

package com.tocode.mx.application.repository;

import com.tocode.mx.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * The Interface ProductRepository.
 */
public interface ProductRepository extends JpaRepository<Product, String> {
  
  /**
   * Find all.
   *
   * @return the list
   */
  List<Product> findAll();
}
