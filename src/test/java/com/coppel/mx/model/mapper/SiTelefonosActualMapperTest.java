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
 * Nombre de archivo: SiTelefonosActualMapperTest.java
 * Autor: 319207
 * Fecha de creación: agosto 17, 2023
 */

package com.coppel.mx.model.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.coppel.mx.model.dto.output.ResponseCurrentPhonesDto;
import com.coppel.mx.model.entity.SiTelefonosActual;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SiTelefonosActualMapperTest {


  @Test
  void toModel() {
    ResponseCurrentPhonesDto dto = new ResponseCurrentPhonesDto();
    dto.setPhone("1");
    dto.setId(BigInteger.ONE);
    dto.setTypeTel("Type");
    dto.setStatusTel("A");

    SiTelefonosActual responseMapper = SiTelefonosActualMapper.toModel(dto);

    assertNotNull(responseMapper);
    assertEquals(dto.getPhone(), responseMapper.getTelefono());
    assertEquals(dto.getId(), responseMapper.getId());
    assertEquals(dto.getTypeTel(), responseMapper.getTipoTel());
    assertEquals(dto.getStatusTel(), responseMapper.getEstatusTel());

  }

  @Test
  void toDto() {
    SiTelefonosActual entity = new SiTelefonosActual();
    entity.setTelefono("1");
    entity.setId(BigInteger.ONE);
    entity.setTipoTel("Type");
    entity.setEstatusTel("A");

    ResponseCurrentPhonesDto responseMapper = SiTelefonosActualMapper.toDto(entity);

    assertNotNull(responseMapper);
    assertEquals(entity.getTelefono(), responseMapper.getPhone());
    assertEquals(entity.getId(), responseMapper.getId());
    assertEquals(entity.getTipoTel(), responseMapper.getTypeTel());
    assertEquals(entity.getEstatusTel(), responseMapper.getStatusTel());

  }
}