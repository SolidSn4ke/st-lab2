package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;

public class Cosecant implements MathFunction {

    Sine sin;

    public Cosecant() {
        this.sin = new Sine();
    }

    public Cosecant(Sine sin) {
        this.sin = sin;
    }

    @Override
    public Double calc(Number arg) {
        Double x = arg.doubleValue();
        if (x % Math.PI == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        x %= Math.PI;
        return 1 / sin.calc(x);
    }
}
