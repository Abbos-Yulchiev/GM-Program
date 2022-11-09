import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Add {

    public int add(int a, int b) {
        System.out.println("This is add method for 2 params");

        return a + b;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.stream().count();
    }

    public int add(int a, int b, int c) {
        System.out.println("This is add method for 3 params");
        return a + b + c;
    }

    public int add(int a, int b, int c, int d) {
        System.out.println("This is add method for 4 params");
        return a + b + c + d;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int division(int a, int b) {
        return a / b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }

    public double sqrt(double number) {
        return Math.sqrt(number);
    }


}
