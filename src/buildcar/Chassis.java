package buildcar;

public class Chassis {

    public String component;

    public Chassis(int length, int doors) {
        component = "";
        int chassies = 2;
        if (length > 11) {
            chassies += (length - 10) / 2;
        }
        int right = chassies / 2;
        int left = chassies - right;
        for (int i = 0; i < left; i++) {
            component += "-o";
        }
        for (int i = 0; i < length - 2 * chassies - 2; i++) {
            component += "-";
        }
        for (int i = 0; i < right; i++) {
            component += "-o";
        }
        component += "-'";
        System.out.print(component);
    }
}
