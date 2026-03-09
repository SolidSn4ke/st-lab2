package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.special.Factorial;

public class Tangent implements MathFunction {

    Sine sin;
    Cosine cos;

    public Tangent() {
        this.sin = new Sine(new Factorial());
        this.cos = new Cosine(this.sin);
    }

    public Tangent(Sine sin, Cosine cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double calc(Number arg) {
        Double x = arg.doubleValue();
        if (x % (Math.PI / 2) == 0.0 && x % Math.PI != 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        x %= Math.PI / 2;
        return sin.calc(x) / cos.calc(x);
    }
}
