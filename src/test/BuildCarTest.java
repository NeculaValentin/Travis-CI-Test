package test;
import buildcar.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.*;

public class BuildCarTest {


    @Test
    public void testBodyComponent() throws Exception {
        BuildCar car = new BuildCar(7, 1);
        assertNotNull("'Car.body.component' is not defined", car.body.component);
    }

    // test chassis.component
    @Test
    public void testChassisComponent() throws Exception {
        BuildCar car = new BuildCar(7, 1);
        assertNotNull("'Car.chassis.component' is not defined", car.chassis.component);
    }

    // test length-exception
    @Test(expected = java.lang.Exception.class)
    public void testLengthException() throws Exception {
        BuildCar car = new BuildCar(3, 1);
    }

    ;

    // test no-door-exceptions
    @Test(expected = java.lang.Exception.class)
    public void testNoDoorException() throws Exception {
        BuildCar car = new BuildCar(7, 0);
    }

    ;

    // test too-many-doors-exception
    @Test(expected = java.lang.Exception.class)
    public void testManyDoorsException() throws Exception {
        int[] args = randomArgs(7, 30);
        BuildCar car = new BuildCar(args[0], args[0] / 2);
    }

    ;

    // test small cars
    @Test
    public void testSmallCars() {
        testCars(7, 11);
    }

    // test medium cars
    @Test
    public void testMediumCars() {
        testCars(12, 20);
    }

    // test large cars
    @Test
    public void testLargeCars() {
        testCars(20, 30);
    }

    // method for random arguments
    private int[] randomArgs(int minLength, int maxLength) {
        int randomLength = (int) Math.ceil(Math.random() * (maxLength - minLength)) + minLength;
        int maxDoors = (int) Math.ceil(Math.random() * Math.floor((randomLength - 3) / 2));
        return new int[]{randomLength, maxDoors};
    }

    // testing method
    public void testCars(int min, int max) {
        int[] args;
        BuildCar car;
        CarSolution carSolution;
        String value, test, output;
        for (int i = 0; i < 5; i++) {
            args = randomArgs(min, max);
            try {
                car = new BuildCar(args[0], args[1]);
                carSolution = new CarSolution(args[0], args[1]);
            } catch (Exception err) {
                System.out.println("Exception caught! Failed! " + err);
                return;
            }
            value = car.body.component + car.chassis.component;
            test = carSolution.body.component + carSolution.chassis.component;
            output = "\nExpected car:\n" + test + "\nYour car:\n" + value + "\n\n";
            assertEquals(output, value, test); // test
            System.out.println(output);
        }
    }

    // CarSolution
    private static class CarSolution {
        public final Body body;
        public final Chassis chassis;
        private final int doors;
        private final int length;

        public CarSolution(int length, int doors) throws Exception {
            if (doors < 1) throw new Exception("How can I enter it?");
            if ((doors * 2) > length - 3) throw new Exception("Too many doors.");
            if (length < 7) throw new Exception("I cannot build that small car.");

            this.doors = doors;
            this.length = length;
            this.body = new Body(getBody());
            this.chassis = new Chassis(getChassis());
        }

        private String getBody() {
            final String[] PARTS = new String[]{"_", "|", "[]", "\\"};
            String bodyLeft = PARTS[1], bodyRight = "", roof = " ";
            boolean left = false;
            int door = 0;

            for (int i = 1; i < length - 2; i++) roof += PARTS[0];
            for (int i = 0; i < length - 3; i++) {
                if (i % 2 == 0) {
                    door++;
                    i++;
                    if (left == true) bodyLeft += PARTS[2];
                    else bodyRight += PARTS[2];
                    left = !left;
                }
                if (door == doors) {
                    while (bodyRight.length() + bodyLeft.length() != (length - 2)) bodyLeft += " ";
                    break;
                }
            }
            return (roof + "\n" + bodyLeft + bodyRight + PARTS[3] + "\n");
        }

        private String getChassis() {
            final String[] PARTS = new String[]{"-o", "o-", "-", "'"};
            String componentRear = PARTS[0], componentMiddle = "", componentFront = PARTS[1];
            boolean rear = true;

            for (int i = length - 5; i > 0; i--) {
                if (i >= 7) {
                    i--;
                    if (rear) componentRear += PARTS[0];
                    else componentFront += PARTS[1];
                    rear = !rear;
                } else componentMiddle += PARTS[2];
            }
            return componentRear + componentMiddle + componentFront + PARTS[3];
        }

        public class Body {
            public final String component;

            public Body(String component) {
                this.component = component;
            }
        }

        public class Chassis {
            public final String component;

            public Chassis(String component) {
                this.component = component;
            }
        }
    }
}