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

package com.coppel.mx.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * The class Error message default.
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDefaultDto {

  /**
   * The name of the exception.
   */
  private String exceptionName;
  /**
   * The Message of the exception.
   */
  private String message;
  /**
   * The Path of the controller.
   */
  private String path;

  /**
   * Map with the erros of validation.
   */
  private Map<String, String> erros;

  /**
   * Instantiates a new Error message default.
   *
   * @param exception the exception
   * @param path      the path
   */
  public ErrorMessageDefaultDto(Exception exception, String path) {
    this.exceptionName = exception.getClass().getSimpleName();
    this.message = exception.getMessage();
    this.path = path;
  }

  /**
   * Instantiates a new Error message default.
   *
   * @param exception the exception
   * @param path      the path
   * @param mapErrors the errors of validation
   */
  public ErrorMessageDefaultDto(Exception exception, String path, Map<String, String> mapErrors) {
    this.exceptionName = exception.getClass().getSimpleName();
    this.path = path;
    this.erros = mapErrors;
  }
}
