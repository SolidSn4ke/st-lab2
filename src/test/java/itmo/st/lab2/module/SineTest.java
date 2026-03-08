package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import java.util.stream.IntStream;
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

    Double[] arg = { 0.0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2, Math.PI * 2 / 3, Math.PI * 3 / 4,
            Math.PI * 5 / 6, Math.PI, -Math.PI / 6, -Math.PI / 4, -Math.PI / 3, -Math.PI / 2, Math.PI * 8 / 3 };
    Double[] expected = { 0.0, 0.5, Math.pow(2, 0.5) / 2, Math.pow(3, 0.5) / 2, 1.0, Math.pow(3, 0.5) / 2,
            Math.pow(2, 0.5) / 2, 0.5, 0.0, -0.5, -Math.pow(2, 0.5) / 2,
            -Math.pow(3, 0.5) / 2, -1.0, Math.pow(3, 0.5) / 2 };
    Double eps = 10e-6;

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
        return IntStream.iterate(0, i -> i + 1).limit(arg.length)
                .mapToObj(i -> DynamicTest.dynamicTest(String.format("Sine test: %d", i), () -> {
                    assertEquals(expected[i], sin.calc(arg[i]), eps);
                }));
    }
}
