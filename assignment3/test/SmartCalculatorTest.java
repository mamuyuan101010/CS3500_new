import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SmartCalculator;

import static org.junit.Assert.assertEquals;

public class SmartCalculatorTest {

  Calculator c1;
  Calculator c2;
  Calculator step1;
  Calculator step2;
  Calculator step3;
  Calculator step4;
  Calculator step5;
  Calculator step6;
  Calculator step7;
  Calculator step8;
  Calculator step9;
  Calculator step10;
  Calculator step11;
  Calculator step12;
  Calculator step13;
  Calculator step14;
  Calculator step15;
  Calculator step16;
  Calculator step17;
  Calculator step18;
  Calculator step19;
  Calculator step20;

  @Before
  public void setUp() {
    c1 = new SmartCalculator();
  }

  // test for inputting multiple = signs
  @Test
  public void testMultipleEquals() throws Exception {
    assertEquals("", c1.getResult());
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('2');
    assertEquals("32+2", step4.getResult());
    step5 = step3.input('4');
    assertEquals("32+24", step5.getResult());
    step6 = step3.input('=');
    assertEquals("56", step6.getResult());
    step7 = step3.input('=');
    assertEquals("80", step7.getResult());
    step8 = step3.input('=');
    assertEquals("104", step8.getResult());
  }

  @Test
  public void testSkipOperand2() throws Exception {
    assertEquals("", c1.getResult());
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('=');
    assertEquals("64", step4.getResult());
    step5 = step3.input('=');
    assertEquals("96", step5.getResult());
  }

  @Test
  public void testFirstOpertorIgnore() throws Exception {
    step1 = c1.input('3');
    assertEquals("3", step1.getResult());
    step2 = step1.input('2');
    assertEquals("32", step2.getResult());
    step3 = step2.input('+');
    assertEquals("32+", step3.getResult());
    step4 = step3.input('-');
    assertEquals("32-", step4.getResult());
    step5 = step3.input('2');
    assertEquals("32-2", step5.getResult());
    step6 = step3.input('4');
    assertEquals("32-24", step6.getResult());
    step7 = step3.input('=');
    assertEquals("8", step7.getResult());
  }

  // Begin with operator
  @Test
  public void testBeginWithOperator() throws Exception{
    assertEquals("", c1.getResult());
    step1 = c1.input('+');
    assertEquals("+", step1.getResult());
    step2 = step1.input('3');
    assertEquals("3", step2.getResult());
    step3 = step2.input('2');
    assertEquals("32", step3.getResult());
    step4 = step3.input('-');
    assertEquals("32-", step4.getResult());
    step5 = step4.input('2');
    assertEquals("32-2", step5.getResult());
    step6 = step5.input('4');
    assertEquals("32-24", step6.getResult());
    step7 = step6.input('=');
    assertEquals("8", step7.getResult());
  }

}