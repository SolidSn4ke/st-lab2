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
public class IntegTestLvl1 {

    @Mock
    Sine sinMock;

    @Mock
    Cosine cosMock;

    Tangent tan;

    Cotangent cot;

    @Mock
    Cosecant cscMock;

    Secant sec;

    @Mock
    NaturalLog lnMock;

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

        when(cosMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 0.824750594285033;
            if (x == -5.56947)
                return 0.7559348848517233;
            if (x == -3.95591)
                return -0.6863650180976307;
            if (x == -3.88927)
                return -0.7332701045359459;
            if (x == -2.32167)
                return -0.6822777567521413;
            if (x == -2.27421)
                return -0.6468248498638663;
            if (x == -1.5708)
                return -3.6732051033465756e-6;
            return Double.NaN;
        });

        when(cscMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 1.768357082490467;
            if (x == -5.56947)
                return 1.5275412329721632;
            if (x == -3.95591)
                return 1.3750293218057952;
            if (x == -3.88927)
                return 1.470723491787492;
            if (x == -2.32167)
                return -1.3678149259900643;
            if (x == -2.27421)
                return -1.311237088863594;
            if (x == -1.5708)
                return -1.0000000000067464;
            return Double.NaN;
        });

        when(lnMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -1.0614032128858384;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 1.2631884339789508;
            }
            if (Math.abs(x - 2) < eps) {
                return 0.6931471805599453;
            }
            if (Math.abs(x - 3) < eps) {
                return 1.0986122886681098;
            }
            if (Math.abs(x - 5) < eps) {
                return 1.6094379124341003;
            }
            if (Math.abs(x - 10) < eps) {
                return 2.302585092994046;
            }
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        tan = new Tangent(sinMock, cosMock);
        cot = new Cotangent(sinMock, cosMock);
        sec = new Secant(cosMock);
        log = new LogBase(0, lnMock);
        CustomFunc f = new CustomFunc(sinMock, cosMock, tan, cot, cscMock, sec, lnMock, log);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
