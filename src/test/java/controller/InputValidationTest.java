package controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test some InputValidation functionallity.
 */
public class InputValidationTest {

  private InputValidation sut;

  @Test
  public void testMemberNameValidation() {
    sut = InputValidation.FULL_NAME;
    assertTrue(sut.test("Michael Scott"));
    assertFalse(sut.test("MS"));
    assertFalse(sut.test("M"));
    assertFalse(sut.test("123"));
    assertFalse(sut.test("1Michael Scott"));
    assertFalse(sut.test(""));
    assertFalse(sut.test(" "));
  }

  @Test
  public void testBoatLengthValidation() {
    sut = InputValidation.LENGTH;
    assertTrue(sut.test("5.0"));
    assertFalse(sut.test("12228338383393939393939399.6"));
    assertFalse(sut.test(""));
    assertFalse(sut.test(" "));
    assertFalse(sut.test("string5.0"));
  }

  @Test
  public void testMemberPnrValidation() {
    sut = InputValidation.PNR;
    assertTrue(sut.test("124567893455"));
    assertFalse(sut.test("5673"));
    assertFalse(sut.test("12k567893q76"));
    assertFalse(sut.test("12456789345 "));
    assertFalse(sut.test(""));
    assertFalse(sut.test(" "));
  }

}
