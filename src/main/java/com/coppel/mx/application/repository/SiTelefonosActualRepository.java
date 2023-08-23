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
 * Nombre de archivo: SiTelefonosActualRepository.java
 * Autor: gumaro
 * Fecha de creación: Ago 17, 2023
 */

package com.coppel.mx.application.repository;

import com.coppel.mx.model.entity.SiTelefonosActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * The interface Si telefonos actual repository.
 */
@Repository
public interface SiTelefonosActualRepository extends JpaRepository<SiTelefonosActual, BigInteger> {

  /**
   * Count phone matches.
   *
   * @param typeTel the type telephone
   * @param phone   the phone
   * @return the number of matches
   */
  @Query(value =
          " SELECT COUNT(*) FROM si_telefonos_actual "
                  + " WHERE tipo_tel = :typeTel "
                  + " AND telefono = :phone "
                  + " AND status_tel = 'A' ",
          nativeQuery = true)
  Integer countPhone(@Param("typeTel") String typeTel,
                     @Param("phone") String phone);
}
