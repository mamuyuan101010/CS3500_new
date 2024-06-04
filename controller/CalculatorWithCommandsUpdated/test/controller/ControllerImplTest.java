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
 * Created by vidojemihajlovikj on 5/20/24.
 */
public class ControllerImplTest {


  @Test
  public void assertTestCalcHelper(){
    InputStream testInput = new ByteArrayInputStream("+ 3 2".getBytes());
    OutputStream testOutput =  new ByteArrayOutputStream();
    IController controller = new ControllerImpl( testInput, testOutput );
    controller.go(new CalculatorImpl());

    assertEquals("5", testOutput.toString());
  }

  @Test
  public void testControllerWiring(){
    InputStream testInput = new ByteArrayInputStream("+ 3 2".getBytes());
    OutputStream testOutput =  new ByteArrayOutputStream();
    IController controller = new ControllerImpl( testInput, testOutput );


    StringBuilder receivedInput = new StringBuilder();
    ICalculator mockModel = new MockModel(receivedInput);
    controller.go(mockModel);

    assertEquals("3 2", receivedInput.toString());
  }
}