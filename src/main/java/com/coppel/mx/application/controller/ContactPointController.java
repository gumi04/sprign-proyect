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
 * Nombre de archivo: ContactPointController.java
 * Autor: gumaro
 * Fecha de creación: Ago 17, 2023
 */

package com.coppel.mx.application.controller;

import com.coppel.mx.application.constants.HttpStatus;
import com.coppel.mx.application.service.SiTelefonosActualService;
import com.coppel.mx.model.dto.input.EvaluateContactDto;
import com.coppel.mx.model.dto.output.ErrorMessageDefaultDto;
import com.coppel.mx.model.dto.output.ResponseEvaluateContactDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class HealthController.
 */
@Api(
        value = "ContactPoint",
        description = "Evaluate contact point",
        tags = {"ContactPoint"})
@RestController
@RequestMapping("/PartyReferenceDataDirectory")
@CrossOrigin
public class ContactPointController {


  @Autowired
  private SiTelefonosActualService siTelefonosActualService;

  /**
   * Evaluete contact the veracity of a menas of contact.
   *
   * @param consumerRequestId the consumer request id
   * @param contactDto        the contact dto
   * @return the contact validation
   */
  @ApiOperation(code = HttpStatus.OK, value = "Evaluate the veracity of a means of contact",
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(value = {
          @ApiResponse(code = HttpStatus.OK, message = "OK", response = ResponseEvaluateContactDto.class),
          @ApiResponse(code = HttpStatus.BAD_REQUEST, message = "Bad Request",
                  response = ErrorMessageDefaultDto.class),
          @ApiResponse(code = HttpStatus.UNAUTHORIZED, message = "Unauthorized",
                  response = ErrorMessageDefaultDto.class),
          @ApiResponse(code = HttpStatus.FORBIDDEN, message = "Forbidden", response = ErrorMessageDefaultDto.class),
          @ApiResponse(code = HttpStatus.NOT_FOUND, message = "NotFound", response = ErrorMessageDefaultDto.class),
          @ApiResponse(code = HttpStatus.MANY_REQUEST, message = "Too many request",
                  response = ErrorMessageDefaultDto.class),
          @ApiResponse(code = HttpStatus.SERVER_ERROR, message = "Internal Server Error",
                  response = ErrorMessageDefaultDto.class)
  })
  @PostMapping(value = "/ContactPoint/Evaluate", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = org.springframework.http.HttpStatus.OK)
  public ResponseEntity<ResponseEvaluateContactDto> evalueteContact(
          final @RequestHeader String consumerRequestId, final @RequestBody @Valid EvaluateContactDto contactDto) {

    return ResponseEntity.ok(siTelefonosActualService.evaluateContactPoint(contactDto));
  }
}
