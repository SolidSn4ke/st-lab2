package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.special.Factorial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sine implements MathFunction {

    Factorial factorial;

    public Sine(Factorial factorial) {
        this.factorial = factorial;
    }

    @Override
    public Double calc(Number arg) {
        Double x = arg.doubleValue();

        Integer n = 0;
        Double res = 0.0;
        Double oldRes = -1.0;
        while (Math.abs(res - oldRes) > Double.MIN_VALUE) {
            oldRes = res;
            res += Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / (factorial.calc(2 * n++ + 1));
        }
        return res;
    }

}
