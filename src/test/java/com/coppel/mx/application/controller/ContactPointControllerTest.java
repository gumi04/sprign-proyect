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
 * Nombre de archivo: ContactPointControllerTest.class
 * Autor: 319207
 * Fecha de creación: agosto 17, 2023
 */

package com.coppel.mx.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coppel.mx.application.repository.SiTelefonosActualRepository;
import com.coppel.mx.model.dto.input.ContactPointDto;
import com.coppel.mx.model.dto.input.CountryDto;
import com.coppel.mx.model.dto.input.EvaluateContactDto;
import com.coppel.mx.model.dto.output.ErrorMessageDefaultDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * The class Contact point controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock(port = 0)
@AutoConfigureMockMvc
@Slf4j
class ContactPointControllerTest {

  /**
   * The constant CONSUMER_REQUEST_ID.
   */
  private static final String HEDAER_CONSUMER_REQUEST_ID = "consumerRequestId";

  /**
   * The constant ZERO.
   */
  private static final int ZERO = 0;
  /**
   * The constant ONE.
   */
  private static final int ONE = 1;

  /**
   * The constant FAILED.
   */
  private static final String FAILED = "FAILED";
  /**
   * The constant CONTAC_POINT_PATH.
   */
  private static final String CONTAC_POINT_PATH = "/PartyReferenceDataDirectory/ContactPoint/Evaluate";


  /**
   * The mvc.
   */
  @Autowired
  private MockMvc mvc;

  /**
   * The Si telefonos actual repository.
   */
  @MockBean
  private SiTelefonosActualRepository siTelefonosActualRepository;

  /**
   * The Mapper.
   */
  private ObjectMapper mapper = new ObjectMapper();

  /**
   * Evaluate contact with matchers on the BD.
   *
   * @throws Exception the exception
   */
  @Test
  void evalueteContactWithMatchers() throws Exception {
    ContactPointDto contact = new ContactPointDto();

    contact.setContactPointDetail("detail");
    contact.setContactPointType("type");
    contact.setContactPointValue("value");
    contact.setContactPointStatus("status");

    CountryDto country = new CountryDto();
    country.setCode("code");
    country.setName("name");

    EvaluateContactDto request = new EvaluateContactDto();
    request.setContactPoint(contact);
    request.setCountry(country);
    request.setSourceChannel("channel");

    Mockito.when(
                    siTelefonosActualRepository.countPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(ONE);

    MvcResult result = mvc.perform(post(CONTAC_POINT_PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header(HEDAER_CONSUMER_REQUEST_ID, "1")
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andReturn();

    String responseService = result.getResponse().getContentAsString();

    assertNotNull(responseService);
  }

  /**
   * Evaluate contact with no matchers on the BD.
   *
   * @throws Exception the exception
   */
  @Test
  void evalueteContactWithNoMatchers() throws Exception {
    ContactPointDto contact = new ContactPointDto();

    contact.setContactPointDetail("detail");
    contact.setContactPointType("type");
    contact.setContactPointValue("value");
    contact.setContactPointStatus("status");

    CountryDto country = new CountryDto();
    country.setCode("code");
    country.setName("name");

    EvaluateContactDto request = new EvaluateContactDto();
    request.setContactPoint(contact);
    request.setCountry(country);
    request.setSourceChannel("channel");

    Mockito.when(
                    siTelefonosActualRepository.countPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(ZERO);

    MvcResult result = mvc.perform(post(CONTAC_POINT_PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header(HEDAER_CONSUMER_REQUEST_ID, "1")
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andReturn();

    String responseService = result.getResponse().getContentAsString();

    assertNotNull(responseService);
  }

  /**
   * Evaluate contact with the header not sent.
   *
   * @throws Exception the exception
   */
  @Test
  void evalueteContactBadRequestNoHeader() throws Exception {

    ContactPointDto contact = new ContactPointDto();

    contact.setContactPointDetail("detail");
    contact.setContactPointType("type");
    contact.setContactPointValue("value");
    contact.setContactPointStatus("status");

    CountryDto country = new CountryDto();
    country.setCode("code");
    country.setName("name");

    EvaluateContactDto request = new EvaluateContactDto();
    request.setContactPoint(contact);
    request.setCountry(country);
    request.setSourceChannel("channel");

    Mockito.when(siTelefonosActualRepository.countPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(ZERO);

    MvcResult result = mvc.perform(post(CONTAC_POINT_PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andReturn();

    ErrorMessageDefaultDto errorExpected =
            mapper.readValue(result.getResponse().getContentAsString(), ErrorMessageDefaultDto.class);

    assertNotNull(errorExpected);
    assertEquals(FAILED, errorExpected.getStatus());
    assertEquals(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorExpected.getStatusCode());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorExpected.getMessage());
  }

  /**
   * Evaluate contact with fields nulls.
   *
   * @throws Exception the exception
   */
  @Test
  void evalueteContactBadRequestBody() throws Exception {

    ContactPointDto contact = new ContactPointDto();

    contact.setContactPointDetail("detail");
    contact.setContactPointType("type");
    contact.setContactPointStatus("status");

    CountryDto country = new CountryDto();
    country.setCode("code");
    country.setName("name");

    EvaluateContactDto request = new EvaluateContactDto();
    request.setContactPoint(contact);
    request.setCountry(country);
    request.setSourceChannel("source");

    Mockito.when(siTelefonosActualRepository.countPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(ZERO);

    MvcResult result = mvc.perform(post(CONTAC_POINT_PATH)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .header(HEDAER_CONSUMER_REQUEST_ID, "1")
                    .content(mapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andReturn();

    ErrorMessageDefaultDto errorExpected =
            mapper.readValue(result.getResponse().getContentAsString(), ErrorMessageDefaultDto.class);

    String errorMessageExpected = "field contactPoint.contactPointValue: error -> must not be null, ";

    assertNotNull(errorExpected);
    assertEquals(FAILED, errorExpected.getStatus());
    assertEquals(String.valueOf(HttpStatus.BAD_REQUEST.value()), errorExpected.getStatusCode());
    assertEquals(errorMessageExpected, errorExpected.getMessage());


  }
}