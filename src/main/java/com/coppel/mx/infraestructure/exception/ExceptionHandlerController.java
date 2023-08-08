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
 * Nombre de archivo: ControllerExceptionHandler.java
 * Autor: gumaro
 * Fecha de creación: Ago 08, 2023
 */

package com.coppel.mx.infraestructure.exception;


import com.coppel.mx.model.dto.ErrorMessageDefaultDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class Controller exception handler.
 */
@ControllerAdvice
public class ExceptionHandlerController {

  /**
   * Not found request error exception.
   *
   * @param request   the request
   * @param exception the exception generated
   * @return The ErrorMessageDefault dto with the error details
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({HttpClientErrorException.class})
  @ResponseBody
  public ErrorMessageDefaultDto notFoundRequest(HttpServletRequest request, Exception exception) {
    return new ErrorMessageDefaultDto(exception, request.getRequestURI());
  }

  /**
   * Bad request error exception.
   *
   * @param request   the request
   * @param exception the exception generated
   * @return The ErrorMessageDefault dto with the error details
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      HttpRequestMethodNotSupportedException.class,
      MissingPathVariableException.class,
      MissingRequestHeaderException.class,
      MissingServletRequestParameterException.class,
      MethodArgumentTypeMismatchException.class,
      HttpMessageNotReadableException.class})
  @ResponseBody
  public ErrorMessageDefaultDto badRequest(HttpServletRequest request, Exception exception) {
    return new ErrorMessageDefaultDto(exception, request.getRequestURI());
  }

  /**
   * Bad request error validation exception.
   *
   * @param exception the exception generated
   * @return The ErrorMessageDefault dto with the error details
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public ErrorMessageDefaultDto badRequestValid(HttpServletRequest request, MethodArgumentNotValidException  exception) {
    Map<String, String> mapErros = new HashMap<>();
    exception.getBindingResult().getFieldErrors().forEach((error) ->{
      String fielName = error.getField();
      String message = error.getDefaultMessage();
      mapErros.put(fielName, message);
    });
    return new ErrorMessageDefaultDto(exception, request.getRequestURI(), mapErros);
  }


  /**
   * Forbidden request error exception.
   *
   * @param request   the request
   * @param exception the exception generated
   * @return The ErrorMessageDefault dto with the error details
   */
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({
     AccessDeniedException.class
  })
  @ResponseBody
  public ErrorMessageDefaultDto forbiddenRequest(HttpServletRequest request, Exception exception) {
    return new ErrorMessageDefaultDto(exception, request.getRequestURI());
  }


  /**
   * Fatal error error exception
   *
   * @param request   the request
   * @param exception the exception generated
   * @return The ErrorMessageDefault dto with the error details
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({
      Exception.class,
      BusinessRuleException.class,
      InternalServerException.class
  })
  @ResponseBody
  public ErrorMessageDefaultDto fatalError(HttpServletRequest request, Exception exception) {
    return new ErrorMessageDefaultDto(exception, request.getRequestURI());
  }

}
