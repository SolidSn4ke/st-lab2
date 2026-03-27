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
    Double eps = 10e-2;

    Map<Double, Double> testArgs = Map.ofEntries(
            Map.entry(-5.68215, 4.39988),
            Map.entry(-5.56947, 5.12882),
            Map.entry(-3.95591, -2.11066),
            Map.entry(-3.88927, 0.0),
            Map.entry(-2.32167, 0.0),
            Map.entry(-2.27421, -0.2375),
            Map.entry(-1.5708, 0.0),
            Map.entry(0.34597, 0.0),
            Map.entry(3.53668, 0.0),
            Map.entry(-5.8, 6.008352855446355),
            Map.entry(-5.6, 5.006282373242017),
            Map.entry(-5.0, 0.6043034915513443),
            Map.entry(-4.5, -0.43623927648114386),
            Map.entry(-3.9, -0.972342279807566),
            Map.entry(-3.8, 202.06416382292076),
            Map.entry(-2.4, 17.48043125773551),
            Map.entry(-2.3, -0.19597159337319517),
            Map.entry(-2.0, -4.565141898308976e-2),
            Map.entry(-1.0, 398.37188654075703),
            Map.entry(0.3, -0.8142632471987838),
            Map.entry(0.5, 2.896984729815482),
            Map.entry(2.0, -4.021795927368153),
            Map.entry(4.0, 0.6058388607809142));

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        CustomFunc f = new CustomFunc();
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
