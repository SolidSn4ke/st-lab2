package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.special.Factorial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sine implements MathFunction {

    Factorial factorial;

    public Sine() {
        this(new Factorial());
    }

    public Sine(Factorial factorial) {
        this.factorial = factorial;
    }

    @Override
    public Double calc(Number arg) {
        Double x = Math.abs(arg.doubleValue());

        while (x > Math.PI) {
            x -= 2 * Math.PI;
        }

        Integer n = -1;
        Double res = 0.0;
        while (++n <= 9) {
            res += Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / (factorial.calc(2 * n + 1));
        }
        return Math.signum(arg.doubleValue()) * res;
    }

}
