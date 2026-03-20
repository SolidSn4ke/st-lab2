package itmo.st.lab2.module;

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

import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Sine;

@ExtendWith(MockitoExtension.class)
public class CosecantTest {

    @Mock
    Sine sinMock;

    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(Math.PI / 6, 2.0),
            Map.entry(Math.PI / 4, Math.sqrt(2)),
            Map.entry(Math.PI / 3, 2 / Math.sqrt(3)),
            Map.entry(Math.PI / 2, 1.0),
            Map.entry(Math.PI * 2 / 3, 2 / Math.sqrt(3)),
            Map.entry(Math.PI * 3 / 4, Math.sqrt(2)),
            Map.entry(Math.PI * 5 / 6, 2.0),
            Map.entry(-Math.PI / 6, -2.0),
            Map.entry(-Math.PI / 4, -Math.sqrt(2)),
            Map.entry(-Math.PI / 3, -2 / Math.sqrt(3)),
            Map.entry(-Math.PI / 2, -1.0),
            Map.entry(0.0, Double.POSITIVE_INFINITY),
            Map.entry(Math.PI, Double.POSITIVE_INFINITY),
            Map.entry(2 * Math.PI, Double.POSITIVE_INFINITY),
            Map.entry(-Math.PI, Double.POSITIVE_INFINITY),
            Map.entry(Math.PI * 8 / 3, 2 / Math.sqrt(3)));

    @BeforeEach
    void injectMocks() {
        when(sinMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x) % Math.PI == 0.0)
                return 0.0;
            if (Math.abs(x) % (Math.PI / 2) == 0.0)
                return Math.signum(x) * 1.0;
            if (Math.abs(x) % (Math.PI / 4) == 0.0)
                return Math.signum(x) * Math.pow(2, 0.5) / 2;
            if (Math.abs(x) - Math.PI / 6 < eps)
                return Math.signum(x) * 0.5;
            if (Math.abs(x) - Math.PI / 3 < eps)
                return Math.signum(x) * Math.pow(3, 0.5) / 2;
            if (Math.abs(x) - Math.PI * 2 / 3 < eps)
                return Math.signum(x) * Math.pow(3, 0.5) / 2;
            if (Math.abs(x) - Math.PI * 5 / 6 < eps)
                return Math.signum(x) * 0.5;
            if (Math.abs(x) - Math.PI * 8 / 3 < eps)
                return Math.pow(3, 0.5) / 2;
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnCsc() {
        Cosecant csc = new Cosecant(sinMock);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("csc %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), csc.calc(e.getKey()), eps);
                }));
    }
}
