package controller;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import model.CalculatorImpl;
import model.ICalculator;

import static org.junit.Assert.*;

/**
 * Created by vidojemihajlovikj on 5/21/24.
 */
public class ControllerImpl2Test {
  @Test
  public void go() throws Exception {
    ICalculator model = new CalculatorImpl();
    InputStream testInput = new ByteArrayInputStream("+ 3 2 + 2 4 + 5 6".getBytes());
    OutputStream testOutput =  new ByteArrayOutputStream();



    IController controller = new ControllerImpl( testInput, testOutput );
    controller.go(model);

    assertEquals("5611", testOutput.toString());
  }

}