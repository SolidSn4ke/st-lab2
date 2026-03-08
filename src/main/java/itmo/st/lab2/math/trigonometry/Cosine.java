package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.special.Factorial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cosine implements MathFunction {

    Sine sin;

    public Cosine() {
        this.sin = new Sine(new Factorial());
    }

    public Cosine(Sine sin) {
        this.sin = sin;
    }

    @Override
    public Double calc(Number arg) {
        double x = Math.abs(arg.doubleValue());

        x = x % (2 * Math.PI);

        Double res = Math.pow(1 - Math.pow(sin.calc(x), 2), 0.5);
        return x > Math.PI / 2 && x < Math.PI * 3 / 2 ? -res : res;
    }

}
