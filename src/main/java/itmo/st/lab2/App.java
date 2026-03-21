package itmo.st.lab2;

import java.util.Arrays;

import itmo.st.lab2.math.log.LogBase;
import itmo.st.lab2.math.log.NaturalLog;
import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Secant;
import itmo.st.lab2.math.trigonometry.Sine;
import itmo.st.lab2.math.trigonometry.Tangent;

public class App {
    public static void main(String[] args) {
        Sine sin = new Sine();
        Cosine cos = new Cosine(sin);
        Tangent tan = new Tangent(sin, cos);
        Cotangent cot = new Cotangent(sin, cos);
        Secant sec = new Secant(cos);
        Cosecant csc = new Cosecant(sin);
        NaturalLog ln = new NaturalLog();
        LogBase log = new LogBase(0, ln);
        sin.toCSV("-3.1415", "3.1415", "0.01", "./export/", "sin_export.csv");
        cos.toCSV("-3.1415", "3.1415", "0.01", "./export/", "cos_export.csv");
        tan.toCSV(Double.toString(-Math.PI / 3), Double.toString(Math.PI / 3), "0.05", "./export/", "tan_export.csv");
        cot.toCSV(Double.toString(Math.PI / 6), Double.toString(Math.PI * 5 / 6), "0.05", "./export/",
                "cot_export.csv");
        sec.toCSV(Double.toString(-Math.PI / 3), Double.toString(Math.PI * 4 / 3), "0.05", "./export/",
                "sec_export.csv");
        csc.toCSV(Double.toString(-Math.PI * 5 / 6), Double.toString(Math.PI * 5 / 6), "0.05", "./export/",
                "csc_export.csv");
        ln.toCSV("0", "5", "0.01", "./export/", "ln_export.csv");
        Integer[] bases = { 2, 3, 5, 10 };
        Arrays.stream(bases).forEach(b -> {
            log.setBase(b);
            log.toCSV("0", "10", "0.01", "./export/", String.format("log%s_export.csv", b.toString()));
        });
        CustomFunc f = new CustomFunc(sin, cos, tan, cot, csc, sec, ln, log);
        f.toCSV("-5.9", "-3.86", "0.01", "./export/", "custom1_export.csv");
        f.toCSV("-2.38", "-1.1", "0.01", "./export/", "custom2_export.csv");
        f.toCSV("0.05", "12", "0.15", "./export/", "custom3_export.csv");
    }
}
