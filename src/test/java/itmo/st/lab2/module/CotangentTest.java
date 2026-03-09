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
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Sine;

@ExtendWith(MockitoExtension.class)
public class CotangentTest {

    @Mock
    Sine sinMock;

    @Mock
    Cosine cosMock;

    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(0.0, Double.POSITIVE_INFINITY),
            Map.entry(Math.PI / 6, Math.sqrt(3)),
            Map.entry(Math.PI / 4, 1.0),
            Map.entry(Math.PI / 3, Math.sqrt(3) / 3),
            Map.entry(-Math.PI / 6, -Math.sqrt(3)),
            Map.entry(-Math.PI / 4, -1.0),
            Map.entry(-Math.PI / 3, -Math.sqrt(3) / 3),
            Map.entry(Math.PI / 2, 0.0),
            Map.entry(-Math.PI / 2, 0.0),
            Map.entry(Math.PI * 7 / 3, Math.sqrt(3) / 3),
            Map.entry(-Math.PI * 7 / 3, -Math.sqrt(3) / 3),
            Map.entry(Math.PI, Double.POSITIVE_INFINITY),
            Map.entry(-Math.PI, Double.POSITIVE_INFINITY));

    @BeforeEach
    void injectMocks() {
        when(sinMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x) < eps)
                return 0.0;
            if (Math.abs(Math.PI / 6 - x) < eps)
                return 0.5;
            if (Math.abs(Math.PI / 4 - x) < eps)
                return Math.pow(2, 0.5) / 2;
            if (Math.abs(Math.PI / 3 - x) < eps)
                return Math.pow(3, 0.5) / 2;
            if (Math.abs(Math.PI / 2 - x) < eps)
                return 1.0;
            if (Math.abs(Math.PI * 2 / 3 - x) < eps)
                return Math.pow(3, 0.5) / 2;
            if (Math.abs(Math.PI * 3 / 4 - x) < eps)
                return Math.pow(2, 0.5) / 2;
            if (Math.abs(Math.PI * 5 / 6 - x) < eps)
                return 0.5;
            if (Math.abs(Math.PI - x) < eps)
                return 0.0;
            if (Math.abs(-Math.PI / 6 - x) < eps)
                return -0.5;
            if (Math.abs(-Math.PI / 4 - x) < eps)
                return -Math.pow(2, 0.5) / 2;
            if (Math.abs(-Math.PI / 3 - x) < eps)
                return -Math.pow(3, 0.5) / 2;
            if (Math.abs(-Math.PI / 2 - x) < eps)
                return -1.0;
            if (Math.abs(-Math.PI * 2 / 3 - x) < eps)
                return -Math.pow(3, 0.5) / 2;
            if (Math.abs(-Math.PI * 3 / 4 - x) < eps)
                return -Math.pow(2, 0.5) / 2;
            if (Math.abs(-Math.PI * 5 / 6 - x) < eps)
                return -0.5;
            if (Math.abs(-Math.PI - x) < eps)
                return 0.0;
            return Double.NaN;
        });

        when(cosMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x) < eps)
                return 1.0;
            if (Math.abs(Math.PI / 6 - x) < eps)
                return Math.pow(3, 0.5) / 2;
            if (Math.abs(Math.PI / 4 - x) < eps)
                return Math.pow(2, 0.5) / 2;
            if (Math.abs(Math.PI / 3 - x) < eps)
                return 0.5;
            if (Math.abs(Math.PI / 2 - x) < eps)
                return 0.0;
            if (Math.abs(Math.PI * 2 / 3 - x) < eps)
                return -0.5;
            if (Math.abs(Math.PI * 3 / 4 - x) < eps)
                return -Math.pow(2, 0.5) / 2;
            if (Math.abs(Math.PI * 5 / 6 - x) < eps)
                return -Math.pow(3, 0.5) / 2;
            if (Math.abs(Math.PI - x) < eps)
                return -1.0;
            if (Math.abs(-Math.PI / 6 - x) < eps)
                return Math.pow(3, 0.5) / 2;
            if (Math.abs(-Math.PI / 4 - x) < eps)
                return Math.pow(2, 0.5) / 2;
            if (Math.abs(-Math.PI / 3 - x) < eps)
                return 0.5;
            if (Math.abs(-Math.PI / 2 - x) < eps)
                return 0.0;
            if (Math.abs(-Math.PI * 2 / 3 - x) < eps)
                return -0.5;
            if (Math.abs(-Math.PI * 3 / 4 - x) < eps)
                return -Math.pow(2, 0.5) / 2;
            if (Math.abs(-Math.PI * 5 / 6 - x) < eps)
                return -Math.pow(3, 0.5) / 2;
            if (Math.abs(-Math.PI - x) < eps)
                return -1.0;
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnCot() {
        Cotangent cot = new Cotangent(sinMock, cosMock);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("Cotangent test: x = %.2f", e.getKey()), () -> {
                    assertEquals(e.getValue(), cot.calc(e.getKey()), eps);
                }));
    }
}
