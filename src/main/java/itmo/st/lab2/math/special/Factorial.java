package itmo.st.lab2.math.special;

import itmo.st.lab2.math.MathFunction;

public class Factorial implements MathFunction {

    @Override
    public Double calc(Number arg) throws IllegalArgumentException {
        Integer n = arg.intValue();
        if (n < 0) {
            throw new IllegalArgumentException(
                    String.format("%d has been passed as an argument for fqactorial function", n));
        }

        return factorial(n, 1).doubleValue();
    }

    private Integer factorial(Integer n, Integer res) {
        if (n <= 1)
            return res;
        else
            return factorial(n - 1, res * n);
    }

}
