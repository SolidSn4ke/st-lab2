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
import itmo.st.lab2.math.trigonometry.Sine;

@ExtendWith(MockitoExtension.class)
public class CosineTest {

    @Mock
    Sine sineMock;
    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(0.0, 1.0),
            Map.entry(Math.PI / 6, Math.pow(3, 0.5) / 2),
            Map.entry(Math.PI / 4, Math.pow(2, 0.5) / 2),
            Map.entry(Math.PI / 3, 0.5),
            Map.entry(Math.PI / 2, 0.0),
            Map.entry(Math.PI * 2 / 3, -0.5),
            Map.entry(Math.PI * 3 / 4, -Math.pow(2, 0.5) / 2),
            Map.entry(Math.PI * 5 / 6, -Math.pow(3, 0.5) / 2),
            Map.entry(Math.PI, -1.0),
            Map.entry(Math.PI * 8 / 3, -0.5));

    @BeforeEach
    public void injectMocks() {
        when(sineMock.calc(anyDouble())).thenAnswer(invocation -> {
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

            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCals_thenReturnCosine() {
        Cosine cos = new Cosine(sineMock);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("Cosine test: x = %.2f", e.getKey()), () -> {
                    assertEquals(e.getValue(), cos.calc(e.getKey()), eps);
                }));
    }
}