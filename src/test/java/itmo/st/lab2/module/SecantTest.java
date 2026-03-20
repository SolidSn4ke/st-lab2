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

import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Secant;

@ExtendWith(MockitoExtension.class)
public class SecantTest {

    @Mock
    Cosine cosMock;

    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(0.0, 1.0),
            Map.entry(Math.PI / 6, 2.0 / Math.sqrt(3)),
            Map.entry(Math.PI / 4, Math.sqrt(2)),
            Map.entry(Math.PI / 3, 2.0),
            Map.entry(Math.PI * 2 / 3, -2.0),
            Map.entry(Math.PI * 3 / 4, -Math.sqrt(2)),
            Map.entry(Math.PI * 5 / 6, -2.0 / Math.sqrt(3)),
            Map.entry(Math.PI, -1.0),
            Map.entry(-Math.PI / 6, 2.0 / Math.sqrt(3)),
            Map.entry(-Math.PI / 4, Math.sqrt(2)),
            Map.entry(-Math.PI / 3, 2.0),
            Map.entry(-Math.PI / 2, Double.POSITIVE_INFINITY),
            Map.entry(-Math.PI * 2 / 3, -2.0),
            Map.entry(-Math.PI, -1.0),
            Map.entry(Math.PI / 2, Double.POSITIVE_INFINITY),
            Map.entry(5 * Math.PI / 2, Double.POSITIVE_INFINITY),
            Map.entry(2 * Math.PI, 1.0),
            Map.entry(Math.PI * 8 / 3, -2.0));

    @BeforeEach
    void injectMocks() {
        when(cosMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            x = Math.abs(x);
            if (x < eps)
                return 1.0;
            if (Math.abs(x - Math.PI / 6) < eps)
                return Math.pow(3, 0.5) / 2;
            if (Math.abs(x - Math.PI / 4) < eps)
                return Math.pow(2, 0.5) / 2;
            if (Math.abs(x - Math.PI / 3) < eps)
                return 0.5;
            if (Math.abs(x - Math.PI / 2) < eps)
                return 0.0;
            if (Math.abs(x - Math.PI * 2 / 3) < eps)
                return -0.5;
            if (Math.abs(x - Math.PI * 3 / 4) < eps)
                return -Math.pow(2, 0.5) / 2;
            if (Math.abs(x - Math.PI * 5 / 6) < eps)
                return -Math.pow(3, 0.5) / 2;
            if (Math.abs(x - Math.PI) < eps)
                return -1.0;
            if (Math.abs(x - 5 * Math.PI / 2) < eps) {
                return 0.0;
            }
            if (Math.abs(x - 2 * Math.PI) < eps) {
                return 1.0;
            }
            if (Math.abs(x - 8 * Math.PI / 3) < eps) {
                return -0.5;
            }
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnSec() {
        Secant sec = new Secant(cosMock);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("Sec %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), sec.calc(e.getKey()), eps);
                }));
    }
}
