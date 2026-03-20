package itmo.st.lab2.math.trigonometry;

import itmo.st.lab2.math.MathFunction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cosine implements MathFunction {

    Sine sin;

    public Cosine() {
        this.sin = new Sine();
    }

    public Cosine(Sine sin) {
        this.sin = sin;
    }

    @Override
    public Double calc(Number arg) {
        double x = arg.doubleValue();

        Double res = Math.pow(1 - Math.pow(sin.calc(x), 2), 0.5);
        return (Math.abs(x) % (2 * Math.PI)) > Math.PI / 2 && (Math.abs(x) % (2 * Math.PI)) < Math.PI * 3 / 2 ? -res : res;
    }
}
