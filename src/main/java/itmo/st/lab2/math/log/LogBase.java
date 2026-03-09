package itmo.st.lab2.math.log;

import itmo.st.lab2.math.MathFunction;

public class LogBase implements MathFunction {
    double base;
    NaturalLog ln;

    public LogBase() {
        this.ln = new NaturalLog();
        this.base = 10.0;
    }

    public LogBase(double base, NaturalLog ln) {
        this.base = base;
        this.ln = ln;
    }

    @Override
    public Double calc(Number arg) {
        return Math.pow(ln.calc(base), -1) * ln.calc(arg);
    }

    public Double calcWithBase(Number arg, Number newBase) {
        this.base = newBase.doubleValue();
        return calc(arg);
    }

}
