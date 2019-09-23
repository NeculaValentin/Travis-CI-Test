package buildcar;

public class Body {

    public String component;

    public Body(int length, int doors) {
        int left = doors / 2;
        int right = doors - left;

        component = " ";
        for (int i = 0; i < length - 3; i++) {
            component += "_";
        }
        component += "\n|";
        for (int i = 0; i < left; i++) {
            component += "[]";
        }
        for (int i = 0; i < length - 3 - doors * 2; i++) {
            component += " ";
        }
        for (int i = 0; i < right; i++) {
            component += "[]";
        }
        component += "\\\n";
        System.out.print(component);
    }
}
