import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.*;

public class SimpleCalculatorTest {
  Calculator c1;
  Calculator c2;

  @Before
  public void setUp() {
    c1 = new SimpleCalculator();
    c2 = new SimpleCalculator();
  }

  @Test
  public void testSimpleCalculator1() {
    c1.input('1');
    assertEquals("1", c1.getResult());
    c1.input('+');
    assertEquals("1+", c1.getResult());
    c1.input('1');
    assertEquals("1+1", c1.getResult());
    c1.input('=');
    assertEquals("2", c1.getResult());
    assertEquals(c1.getOperand2(), 1);
    c1.input('+');
    assertEquals("2+", c1.getResult());
    assertEquals(c1.getOperand2(), -1);
    c1.input('3');
    //assertEquals(3,c1.getOperand2());
    assertEquals("2+3", c1.getResult());
    c1.input('=');
    assertEquals("5", c1.getResult());
    c1.input('C');
    assertEquals("", c1.getResult());
  }

  @Test
  public void testSimpleCalculator2() {
    c2.input('2').input('+').input('3');
    assertEquals("2+3", c2.getResult());
    assertEquals(2, c2.getOperand1());
    assertEquals(3,c2.getOperand2());
    c2.input('+');
    assertEquals("5+", c2.getResult());
    assertEquals(5, c2.getOperand1());
    assertEquals(-1,c2.getOperand2());
    c2.input('1');
    assertEquals("5+1", c2.getResult());
    assertEquals(c2.getOperand1(),5);
    assertEquals(c2.getOperand2(),1);
    c2.input('=');


    assertEquals("6", c2.getResult());
  }
}