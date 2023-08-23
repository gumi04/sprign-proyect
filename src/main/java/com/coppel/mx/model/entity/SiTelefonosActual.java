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
 * Nombre de archivo: SiTelefonosActual.java
 * Autor: gumaro
 * Fecha de creación: Ago 17, 2023
 */

package com.coppel.mx.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * The class Si telefonos actual.
 */
@Entity
@Getter
@Setter
@Table(name = "si_telefonos_actual")
public class SiTelefonosActual {

  /**
   * The Id.
   */
  @Id
  @Column(name = "id")
  private BigInteger id;

  /**
   * The Tipo tel.
   */
  @Column(name = "tipo_tel")
  private String tipoTel;
  /**
   * The Telefono.
   */
  @Column(name = "telefono")
  private String telefono;

  /**
   * The Estatus tel.
   */
  @Column(name = "status_tel")
  private String estatusTel;

}
