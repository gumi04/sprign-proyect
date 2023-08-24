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
 * Nombre de archivo: HttpStatus.java
 * Autor: gumaro
 * Fecha de creación: Ago 17, 2023
 */

package com.coppel.mx.application.constants;

/**
 * The class Http status.
 */

public class HttpStatus {

  /**
   * The constant OK.
   */
  public static final String OK = "200";

  /**
   * The constant BAD_REQUEST.
   */
  public static final String BAD_REQUEST = "400";

  /**
   * The constant UNAUTHORIZED.
   */
  public static final String UNAUTHORIZED = "401";

  /**
   * The constant FORBIDDEN.
   */
  public static final String FORBIDDEN = "403";

  /**
   * The constant NOT_FOUND.
   */
  public static final String NOT_FOUND = "404";

  /**
   * The constant MANY_REQUEST.
   */
  public static final String MANY_REQUEST = "429";

  /**
   * The constant SERVER_ERROR.
   */
  public static final String SERVER_ERROR = "500";

  /**
   * Instantiates a new Http status.
   */
  private HttpStatus() {

  }
}
