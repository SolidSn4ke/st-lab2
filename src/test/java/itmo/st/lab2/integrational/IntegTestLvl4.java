package itmo.st.lab2.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import itmo.st.lab2.CustomFunc;

@ExtendWith(MockitoExtension.class)
public class IntegTestLvl4 {
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

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        CustomFunc f = new CustomFunc();
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
