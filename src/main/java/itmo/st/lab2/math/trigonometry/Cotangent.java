package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.special.Factorial;

public class Cotangent implements MathFunction {

    Sine sin;
    Cosine cos;

    public Cotangent() {
        this.sin = new Sine(new Factorial());
        this.cos = new Cosine(this.sin);
    }

    public Cotangent(Sine sin, Cosine cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double calc(Number arg) {
        Double x = arg.doubleValue();
        if (x % Math.PI == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        x %= Math.PI;
        return cos.calc(x) / sin.calc(x);
    }

}
