package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import itmo.st.lab2.math.log.NaturalLog;

public class NaturalLogTest {

    Double eps = 10e-6;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(0.0, Double.NEGATIVE_INFINITY),
            Map.entry(0.25, -1.386294),
            Map.entry(0.5, -0.693147),
            Map.entry(0.75, -0.287682),
            Map.entry(1.0, 0.0),
            Map.entry(1.5, 0.405465),
            Map.entry(2.0, 0.693147),
            Map.entry(2.5, 0.916291),
            Map.entry(Math.E, 1.0),
            Map.entry(10.0, 2.302585),
            Map.entry(100.0, 4.605170),
            Map.entry(Math.exp(0.5), 0.5),
            Map.entry(Math.exp(2.0), 2.0));

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_thenReturnLn() {
        NaturalLog ln = new NaturalLog();
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("NaturalLog test: x = %.2f", e.getKey()), () -> {
                    assertEquals(e.getValue(), ln.calc(e.getKey()), eps);
                }));
    }
}
