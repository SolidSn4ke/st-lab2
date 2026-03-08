package itmo.st.lab2.math.special;

import itmo.st.lab2.math.MathFunction;

public class Factorial implements MathFunction {

    @Override
    public Double calc(Number arg) throws IllegalArgumentException {
        Long n = arg.longValue();
        if (n < 0) {
            throw new IllegalArgumentException(
                    String.format("%d has been passed as an argument for factorial function", n));
        }

        return factorial(n, 1l).doubleValue();
    }

    private Long factorial(Long n, Long res) {
        if (n <= 1)
            return res;
        else
            return factorial(n - 1, res * n);
    }

}
