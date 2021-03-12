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
* Nombre de archivo: Product.java 
* Autor: salvgonz 
* Fecha de creación: Mar 10, 2021 
*/

package com.tocode.mx.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The Class Product.
 */
@Entity
@Table(name = "stub_product")
@Setter
@Getter
public class Product {

  /** The id. */
  @Id  
  @Column(name = "id")
  private String id;

  /** The name. */
  @Column(name = "name", length = 350)
  private String name;

  /** The description. */
  @Column(name = "description", length = 2500)
  private String description;

  /** The image. */
  @Column(name = "image", length = 350)
  private String image;

  /** The price. */
  @Column(name = "price")
  private Double price;

}
