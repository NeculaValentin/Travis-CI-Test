package test;

import buildcar.*;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class BuildCarTest {

  @Test
  public void testPiccolo() throws Exception {
    test(7, 12);
  }

  @Test
  public void testMedio() throws Exception {
    test(13, 25);
  }

  @Test
  public void testGrande() throws Exception {
    test(25, 35);
  }

  @Test
  public void testBody() throws Exception {
    BuildCar car = new BuildCar(8, 1);
  } // TEST BODY

  @Test
  public void testChassis() throws Exception {
    BuildCar car = new BuildCar(8, 1);
  } // TEST CHASSIS

  @Test(expected = java.lang.Exception.class)
  public void testLengthException() throws Exception {
    BuildCar car = new BuildCar(2, 1);
  } // TEST LUNGHEZZA PICCOLA

  @Test(expected = java.lang.Exception.class)
  public void testNoDoorException() throws Exception {
    BuildCar car = new BuildCar(12, 0);
  } // TEST SENZA PORTE

  private int[] randomArgs(int minLength, int maxLength) {
    int randomLength = (int) Math.ceil(Math.random() * (maxLength - minLength)) + minLength;
    int maxDoors = (int) Math.ceil(Math.random() * Math.floor((randomLength - 3) / 2));
    return new int[] {randomLength, maxDoors};
  } // METODO CHE IMPLEMENTA I VALORI RANDOM

  public static class Car {
    Body body;
    Chassis chassis;

    Car(int length, int doors) throws Exception {
      if (length < 7 || doors < 1 || doors * 2 > length - 3) throw new Exception();
      body = new Body(length, doors);
      chassis = new Chassis(length, doors);
    }
  }

  static class Body {
    String component;

    Body(int length, int doors) {
      int left = doors / 2;
      int right = doors - left;
      component = " ";
      for (int i = 0; i < length - 3; i++) component += "_";
      component += "\n|";
      for (int i = 0; i < left; i++) component += "[]";
      for (int i = 0; i < length - 3 - doors * 2; i++) component += " ";
      for (int i = 0; i < right; i++) component += "[]";
      component += "\\\n";
    }
  }

  static class Chassis {
    String component;

    Chassis(int length, int doors) {
      component = "";
      int chassies = 2;
      if (length > 11) chassies += (length - 10) / 2;
      int right = chassies / 2;
      int left = chassies - right;
      for (int i = 0; i < left; i++) component += "-o";
      for (int i = 0; i < length - 2 * chassies - 2; i++) component += "-";
      for (int i = 0; i < right; i++) component += "-o";
      component += "-'";
    }
  }

  private void test(int min, int max) throws Exception {
    for (int i = 0; i < 10; i++) {
      int[] dati = randomArgs(min, max);
      Car carSolution = new Car(dati[0], dati[1]);
      BuildCar car = new BuildCar(dati[0], dati[1]);
      String carString = car.body.component + car.chassis.component;
      String carTestString = carSolution.body.component + carSolution.chassis.component;
      assertEquals(carString, carTestString); // test
      System.out.println();
      // String output = "\nMacchina reale:\n" + test + "\nLa tua macchina:\n" + value + "\n\n";
      // System.out.println(output);
    }
  }
}
