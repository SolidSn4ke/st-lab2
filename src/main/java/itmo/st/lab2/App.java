package itmo.st.lab2;

import itmo.st.lab2.math.special.Factorial;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        System.out.println(factorial.calc(1.5));
    }
}
