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
 * Nombre de archivo: SiTelefonosActualMapper.java
 * Autor: gumaro
 * Fecha de creación: Ago 17, 2023
 */

package com.coppel.mx.model.mapper;


import com.coppel.mx.model.dto.output.ResponseCurrentPhonesDto;
import com.coppel.mx.model.entity.SiTelefonosActual;

/**
 * The class Si telefonos actual mapper.
 */
public final class SiTelefonosActualMapper {

  /**
   * Instantiates a new Si telefonos actual mapper.
   */
  private SiTelefonosActualMapper() {
    super();
  }

  /**
   * Convert a dto to an entity.
   *
   * @param dto the dto
   * @return the corresponding entity
   */
  public static SiTelefonosActual toModel(final ResponseCurrentPhonesDto dto) {
    SiTelefonosActual entity = new SiTelefonosActual();
    entity.setId(dto.getId());
    entity.setTipoTel(dto.getTypeTel());
    entity.setTelefono(dto.getPhone());
    entity.setEstatusTel(dto.getStatusTel());
    return entity;
  }

  /**
   * Convert a entity to dto.
   *
   * @param entity the entity
   * @return the corresponding dto
   */
  public static ResponseCurrentPhonesDto toDto(final SiTelefonosActual entity) {
    ResponseCurrentPhonesDto dto = new ResponseCurrentPhonesDto();
    dto.setId(entity.getId());
    dto.setTypeTel(entity.getTipoTel());
    dto.setPhone(entity.getTelefono());
    dto.setStatusTel(entity.getEstatusTel());
    return dto;
  }


}
