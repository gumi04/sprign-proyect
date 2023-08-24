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
 * Nombre de archivo: HealthControllerTest.java
 * Autor: gumaro
 * Fecha de creación: Ago 07, 2023
 */

package com.coppel.mx.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * The Class IntegrationTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
@AutoConfigureMockMvc
@Slf4j
class HealthControllerTest {

  /**
   * The Constant MESSAGE.
   */
  private final static String MESSAGE_EXPECTED = "SERVICE UP.";

  /**
   * The mvc.
   */
  @Autowired
  private MockMvc mvc;


  /**
   * <h3>Should retrieve the information of the healt of the app.</h3>
   *
   * <p>
   * <strong>Given</strong> a response to check the healt<br/>
   * <strong>When</strong>  I need to know the healt of the api<br/>
   * <strong>Then</strong>  an API should be retrive a message with the healt<br/>
   * </p>
   *
   * @throws Exception
   */
  @Test
  void healthCheckTest() throws Exception {
    log.info("healthcheck");

    String productApi = "/health";

    MvcResult result = mvc.perform(get(productApi)
                    .contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn();

    String responseService = result.getResponse().getContentAsString();

    assertNotNull(responseService);
    assertEquals(MESSAGE_EXPECTED, responseService);
  }
}