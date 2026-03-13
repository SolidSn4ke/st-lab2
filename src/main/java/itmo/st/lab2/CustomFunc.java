package itmo.st.lab2;

import itmo.st.lab2.math.MathFunction;
import itmo.st.lab2.math.log.LogBase;
import itmo.st.lab2.math.log.NaturalLog;
import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Secant;
import itmo.st.lab2.math.trigonometry.Sine;
import itmo.st.lab2.math.trigonometry.Tangent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomFunc implements MathFunction {

    private Sine sin;
    private Cosine cos;
    private Tangent tan;
    private Cotangent cot;
    private Cosecant csc;
    private Secant sec;

    private NaturalLog ln;
    private LogBase log;

    public CustomFunc() {
        this.sin = new Sine();
        this.cos = new Cosine(sin);
        this.tan = new Tangent(sin, cos);
        this.cot = new Cotangent(sin, cos);
        this.csc = new Cosecant(sin);
        this.sec = new Secant(cos);
        this.ln = new NaturalLog();
        this.log = new LogBase(0, ln);
    }

    @Override
    public Double calc(Number arg) {
        Double x = arg.doubleValue();
        if (x <= 0) {
            return (Math
                    .pow(Math.pow(
                            Math.pow((((((((cos.calc(x) / csc.calc(x)) / csc.calc(x)) / (cot.calc(x) / cot.calc(x)))
                                    + ((sec.calc(x) - csc.calc(x)) * cos.calc(x))) + cos.calc(x))
                                    / (tan.calc(x) / cos.calc(x))) * sec.calc(x)), 2),
                            3), 2)
                    + (((sin.calc(x) * Math.pow(csc.calc(x), 2)) * cot.calc(x)) + cot.calc(x)));
        } else {
            return (((((log.calcWithBase(x, 10) + log.calcWithBase(x, 2)) + log.calcWithBase(x, 3))
                    - ((log.calcWithBase(x, 5) + log.calcWithBase(x, 10))
                            / (log.calcWithBase(x, 10) + log.calcWithBase(x, 2))))
                    - (((log.calcWithBase(x, 2) / log.calcWithBase(x, 5)) / log.calcWithBase(x, 5)) + ln.calc(x)))
                    + ln.calc(x));
        }
    }

}
