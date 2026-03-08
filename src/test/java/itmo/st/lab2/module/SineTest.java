package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import itmo.st.lab2.math.special.Factorial;
import itmo.st.lab2.math.trigonometry.Sine;

@ExtendWith(MockitoExtension.class)
public class SineTest {

    @Mock
    Factorial factorialMock;

    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(0.0, 0.0),
            Map.entry(Math.PI / 6, 0.5),
            Map.entry(Math.PI / 4, Math.pow(2, 0.5) / 2),
            Map.entry(Math.PI / 3, Math.pow(3, 0.5) / 2),
            Map.entry(Math.PI / 2, 1.0),
            Map.entry(Math.PI * 2 / 3, Math.pow(3, 0.5) / 2),
            Map.entry(Math.PI * 3 / 4, Math.pow(2, 0.5) / 2),
            Map.entry(Math.PI * 5 / 6, 0.5),
            Map.entry(Math.PI, 0.0),
            Map.entry(-Math.PI / 6, -0.5),
            Map.entry(-Math.PI / 4, -Math.pow(2, 0.5) / 2),
            Map.entry(-Math.PI / 3, -Math.pow(3, 0.5) / 2),
            Map.entry(-Math.PI / 2, -1.0),
            Map.entry(Math.PI * 8 / 3, Math.pow(3, 0.5) / 2));

    @BeforeEach
    public void injectMocks() {
        doAnswer(invocation -> {
            Integer n = invocation.getArgument(0);
            return switch (n) {
                case 0, 1 -> 1.0;
                case 2 -> 2.0;
                case 3 -> 6.0;
                case 4 -> 24.0;
                case 5 -> 120.0;
                case 6 -> 720.0;
                case 7 -> 5040.0;
                case 8 -> 40320.0;
                case 9 -> 362880.0;
                case 10 -> 3628800.0;
                case 11 -> 39916800.0;
                case 12 -> 479001600.0;
                case 13 -> 6227020800.0;
                case 14 -> 87178291200.0;
                case 15 -> 1307674368000.0;
                case 16 -> 20922789888000.0;
                case 17 -> 355687428096000.0;
                case 18 -> 6402373705728000.0;
                case 19 -> 121645100408832000.0;
                case 20 -> 2432902008176640000.0;
                default -> throw new IllegalArgumentException(String.format("Factorial of %d is not defined", n));
            };
        }).when(factorialMock).calc(anyInt());
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnSin() {
        Sine sin = new Sine(factorialMock);
        return testArgs.entrySet().stream()
                .map(e -> DynamicTest.dynamicTest(String.format("Sine test: x = %.2f", e.getKey()), () -> {
                    assertEquals(e.getValue(), sin.calc(e.getKey()), eps);
                }));
    }
}
