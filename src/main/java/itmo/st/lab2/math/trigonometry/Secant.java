package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;

public class Secant implements MathFunction {

    Cosine cos;

    public Secant() {
        this.cos = new Cosine();
    }

    public Secant(Cosine cos) {
        this.cos = cos;
    }

    @Override
    public Double calc(Number arg) {
        Double x = Math.abs(arg.doubleValue());

        return 1 / cos.calc(x);
    }
}
