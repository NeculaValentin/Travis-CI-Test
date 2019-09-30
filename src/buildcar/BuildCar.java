package buildcar;

/** @author vale3 */
public class BuildCar {

  public static void main(String[] args) throws Exception {
    BuildCar car = new BuildCar(10, 2);
  }

  public Body body;
  public Chassis chassis;

  public BuildCar(int length, int doors) throws Exception {
    if (length < 7 || doors < 1 || doors * 2 > length - 3) {
      throw new Exception();
    }
    body = new Body(length, doors);
    chassis = new Chassis(length, doors);
  }
}
