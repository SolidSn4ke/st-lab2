package itmo.st.lab2.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import itmo.st.lab2.CustomFunc;
import itmo.st.lab2.math.log.LogBase;
import itmo.st.lab2.math.log.NaturalLog;
import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Secant;
import itmo.st.lab2.math.trigonometry.Sine;
import itmo.st.lab2.math.trigonometry.Tangent;

@ExtendWith(MockitoExtension.class)
public class IntegTestLvl2 {

    @Mock
    Sine sinMock;

    Cosine cos;

    Tangent tan;

    Cotangent cot;

    Cosecant csc;

    Secant sec;

    NaturalLog ln;

    LogBase log;

    Double eps = 10e-4;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(-5.68215, 4.39988),
            Map.entry(-5.56947, 5.12882),
            Map.entry(-3.95591, -2.11066),
            Map.entry(-3.88927, 0.0),
            Map.entry(-2.32167, 0.0),
            Map.entry(-2.27421, -0.2375),
            Map.entry(-1.5708, 0.0),
            Map.entry(0.34597, 0.0),
            Map.entry(3.53668, 0.0));

    @BeforeEach
    void injectMocks() {
        when(sinMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 0.5654966465209895;
            if (x == -5.56947)
                return 0.6546468130711489;
            if (x == -3.95591)
                return 0.7272572185491452;
            if (x == -3.88927)
                return 0.679937463149254;
            if (x == -2.32167)
                return -0.7310930601785698;
            if (x == -2.27421)
                return -0.7626385864868016;
            if (x == -1.5708)
                return -0.9999999999932537;
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        cos = new Cosine(sinMock);
        tan = new Tangent(sinMock, cos);
        cot = new Cotangent(sinMock, cos);
        sec = new Secant(cos);
        csc = new Cosecant(sinMock);
        ln = new NaturalLog();
        log = new LogBase(0, ln);
        CustomFunc f = new CustomFunc(sinMock, cos, tan, cot, csc, sec, ln, log);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
