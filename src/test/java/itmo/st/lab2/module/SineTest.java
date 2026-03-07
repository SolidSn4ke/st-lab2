package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab2.math.special.Factorial;
import itmo.st.lab2.math.trigonometry.Sine;

public class SineTest {

    Double[] arg = { 0.0, Math.PI / 6, Math.PI / 4, Math.PI / 3, Math.PI / 2, Math.PI, -Math.PI / 6, -Math.PI / 4,
            -Math.PI / 3, -Math.PI / 2, Math.PI * 8 / 3 };
    Double[] expected = { 0.0, 0.5, Math.pow(2, 0.5) / 2, Math.pow(3, 0.5) / 2, 1.0, 0.0, -0.5, -Math.pow(2, 0.5) / 2,
            -Math.pow(3, 0.5) / 2, -1.0, Math.pow(3, 0.5) / 2 };

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnSin() {
        Factorial factorial = new Factorial();
        Sine sin = new Sine(factorial);
        return IntStream.iterate(0, i -> i + 1).limit(arg.length)
                .mapToObj(i -> DynamicTest.dynamicTest(String.format("Sine test: %d", i), () -> {
                    assertEquals(expected[i], sin.calc(arg[i]));
                }));
    }
}
