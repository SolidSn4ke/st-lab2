package itmo.st.lab2.math.log;

import itmo.st.lab2.math.MathFunction;

public class NaturalLog implements MathFunction {

    @Override
    public Double calc(Number arg) throws IllegalArgumentException {
        Double x = arg.doubleValue();

        if (x == 1)
            return 0.0;

        if (x == 0)
            return Double.NEGATIVE_INFINITY;

        if (x < 0)
            throw new IllegalArgumentException("NaturalLog does not accept negative args");

        Integer n = 1;
        Double res = 0.0;
        Double oldRes = -1.0;
        while (Math.abs(res - oldRes) > Double.MIN_VALUE) {
            oldRes = res;
            res += Math.pow(-1, n - 1) * Math.pow((x < 1 ? x : 1 / x) - 1, n) / n++;
        }
        return x < 1 ? res : -res;
    }

}
