package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab2.math.special.Factorial;

public class FactorialTest {
    Factorial factorial = new Factorial();

    Integer[] expected = { 1, 2, 24, 720 };

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnFacto() {
        return IntStream.iterate(-2, n -> n + 2).limit(5)
                .mapToObj(n -> DynamicTest.dynamicTest(String.format("Factorial test: n = %d", n), () -> {
                    if (n < 0)
                        assertThrows(IllegalArgumentException.class, () -> factorial.calc(n));
                    else
                        assertTrue(expected[(n % 10) / 2] == factorial.calc(n).intValue());
                }));
    }
}
