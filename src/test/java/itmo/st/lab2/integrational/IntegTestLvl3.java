package itmo.st.lab2.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.anyInt;
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
import itmo.st.lab2.math.special.Factorial;
import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Secant;
import itmo.st.lab2.math.trigonometry.Sine;
import itmo.st.lab2.math.trigonometry.Tangent;

@ExtendWith(MockitoExtension.class)
public class IntegTestLvl3 {
    @Mock
    Factorial factorialMock;

    Sine sin;

    Cosine cos;

    Tangent tan;

    Cotangent cot;

    Cosecant csc;

    Secant sec;

    NaturalLog ln;

    LogBase log;

    Double eps = 10e-2;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(-5.68215, 4.39988),
            Map.entry(-5.56947, 5.12882),
            Map.entry(-3.95591, -2.11066),
            Map.entry(-3.88927, 0.0),
            Map.entry(-2.32167, 0.0),
            Map.entry(-2.27421, -0.2375),
            Map.entry(-1.5708, 0.0),
            Map.entry(0.34597, 0.0),
            Map.entry(3.53668, 0.0),
            Map.entry(-5.8, 6.008352855446355),
            Map.entry(-5.6, 5.006282373242017),
            Map.entry(-5.0, 0.6043034915513443),
            Map.entry(-4.5, -0.43623927648114386),
            Map.entry(-3.9, -0.972342279807566),
            Map.entry(-3.8, 202.06416382292076),
            Map.entry(-2.4, 17.48043125773551),
            Map.entry(-2.3, -0.19597159337319517),
            Map.entry(-2.0, -4.565141898308976e-2),
            Map.entry(-1.0, 398.37188654075703),
            Map.entry(0.3, -0.8142632471987838),
            Map.entry(0.5, 2.896984729815482),
            Map.entry(2.0, -4.021795927368153),
            Map.entry(4.0, 0.6058388607809142));

    @BeforeEach
    void injectMocks() {
        when(factorialMock.calc(anyInt())).thenAnswer(invocation -> {
            Integer x = invocation.getArgument(0);
            if (x == 1)
                return 1.0;
            if (x == 3)
                return 6.0;
            if (x == 5)
                return 120.0;
            if (x == 7)
                return 5040.0;
            if (x == 9)
                return 362880.0;
            if (x == 11)
                return 39916800.0;
            if (x == 13)
                return 6227020800.0;
            if (x == 15)
                return 1307674368000.0;
            if (x == 17)
                return 355687428096000.0;
            if (x == 19)
                return 121645100408832000.0;
            return -1;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        sin = new Sine(factorialMock);
        cos = new Cosine(sin);
        tan = new Tangent(sin, cos);
        cot = new Cotangent(sin, cos);
        sec = new Secant(cos);
        csc = new Cosecant(sin);
        ln = new NaturalLog();
        log = new LogBase(0, ln);
        CustomFunc f = new CustomFunc(sin, cos, tan, cot, csc, sec, ln, log);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
