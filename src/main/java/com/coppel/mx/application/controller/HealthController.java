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
* Nombre de archivo: HealthController.java 
* Autor: salvgonz 
* Fecha de creación: Mar 13, 2021 
*/

package com.coppel.mx.application.controller;

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
