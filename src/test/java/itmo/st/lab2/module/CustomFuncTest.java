package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
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
public class CustomFuncTest {

    @Mock
    Sine sinMock;

    @Mock
    Cosine cosMock;

    @Mock
    Tangent tanMock;

    @Mock
    Cotangent cotMock;

    @Mock
    Cosecant cscMock;

    @Mock
    Secant secMock;

    @Mock
    NaturalLog lnMock;

    @Mock
    LogBase logMock;

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
            if (Math.abs(x + 5.68215) < eps) {
                return 0.5654966465209895;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 0.6546468130711489;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return 0.7272572185491452;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return 0.679937463149254;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return -0.7310930601785698;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return -0.7626385864868016;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return -0.9999999999932537;
            }
            return Double.NaN;
        });

        when(cosMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x + 5.68215) < eps) {
                return 0.824750594285033;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 0.7559348848517233;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return -0.6863650180976307;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return -0.7332701045359459;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return -0.6822777567521413;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return -0.6468248498638663;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return -3.6732051033465756e-6;
            }
            return Double.NaN;
        });

        when(tanMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x + 5.68215) < eps) {
                return 0.6856577618003564;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 0.8660095283200985;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return -1.05957792045529;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return -0.9272673997524503;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return 1.071547552215105;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return 1.1790496092525045;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return 272241.80840927624;
            }
            return Double.NaN;
        });

        when(cotMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x + 5.68215) < eps) {
                return 1.4584535546921598;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 1.1547217060530717;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return -0.9437720253460077;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return -1.0784375685664858;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return 0.9332296993565972;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return 0.8481407331401275;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return 3.6732051033713543e-6;
            }
            return Double.NaN;
        });

        when(cscMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x + 5.68215) < eps) {
                return 1.768357082490467;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 1.5275412329721632;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return 1.3750293218057952;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return 1.470723491787492;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return -1.3678149259900643;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return -1.311237088863594;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return -1.0000000000067464;
            }
            return Double.NaN;
        });

        when(secMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x + 5.68215) < eps) {
                return 1.212487759244222;
            }
            if (Math.abs(x + 5.56947) < eps) {
                return 1.3228652626557247;
            }
            if (Math.abs(x + 3.95591) < eps) {
                return -1.4569507093640324;
            }
            if (Math.abs(x + 3.88927) < eps) {
                return -1.363753947984632;
            }
            if (Math.abs(x + 2.32167) < eps) {
                return -1.4656787358279382;
            }
            if (Math.abs(x + 2.27421) < eps) {
                return -1.5460135772620123;
            }
            if (Math.abs(x + 1.5708) < eps) {
                return -272241.80841111275;
            }
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
            return Double.NaN;
        });

        when(logMock.calcWithBase(anyDouble(), eq(2))).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -1.531281151614012;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 1.8223956894097282;
            }
            return Double.NaN;
        });

        when(logMock.calcWithBase(anyDouble(), eq(3))).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -0.9661308396364459;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 1.1498036632289659;
            }
            return Double.NaN;
        });

        when(logMock.calcWithBase(anyDouble(), eq(5))).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -0.6594868958197841;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 0.78486310296277;
            }
            return Double.NaN;
        });

        when(logMock.calcWithBase(anyDouble(), eq(10))).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -0.460961558430702;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 0.5485957664810684;
            }
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        CustomFunc f = new CustomFunc(sinMock, cosMock, tanMock, cotMock, cscMock, secMock, lnMock, logMock);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("Custom func test: x = %.2f", e.getKey()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
