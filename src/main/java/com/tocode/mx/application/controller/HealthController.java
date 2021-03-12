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
* Nombre de archivo: HealthController.java 
* Autor: salvgonz 
* Fecha de creación: Mar 10, 2021 
*/

package com.tocode.mx.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class HealthController.
 */
@Api(
    value = "Health API", 
    description = "A service that gives information about service status",
    tags = {"Health"})
@RestController
public class HealthController {
  
  /** The Constant MESSAGE. */
  private final static String MESSAGE = "SERVICE UP.";

  /**
   * Health.
   *
   * @return the response entity
   */
  @ApiOperation(value = "Check health")
  @GetMapping(value = "/health")
  public ResponseEntity<String> health() {
    return new ResponseEntity<String>(MESSAGE, HttpStatus.OK);
  }

}
