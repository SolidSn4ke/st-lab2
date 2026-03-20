package itmo.st.lab2.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import itmo.st.lab2.math.log.LogBase;
import itmo.st.lab2.math.log.NaturalLog;

@ExtendWith(MockitoExtension.class)
public class LogBaseTest {

    @Mock
    NaturalLog lnMock;

    Double eps = 10e-6;

    Map<Double, Map<Double, Double>> testArgs = Map.ofEntries(
            Map.entry(10.0, Map.ofEntries(
                    Map.entry(1.0, 0.0),
                    Map.entry(10.0, 1.0),
                    Map.entry(100.0, 2.0),
                    Map.entry(0.1, -1.0))),
            Map.entry(2.0, Map.ofEntries(
                    Map.entry(1.0, 0.0),
                    Map.entry(2.0, 1.0),
                    Map.entry(4.0, 2.0),
                    Map.entry(0.5, -1.0))),
            Map.entry(5.0, Map.ofEntries(
                    Map.entry(1.0, 0.0),
                    Map.entry(5.0, 1.0),
                    Map.entry(25.0, 2.0),
                    Map.entry(0.2, -1.0))),
            Map.entry(1.0, Map.ofEntries(
                    Map.entry(10.0, Double.POSITIVE_INFINITY),
                    Map.entry(0.5, Double.NEGATIVE_INFINITY),
                    Map.entry(1.0, Double.NaN))),
            Map.entry(-1.0, Map.ofEntries(
                    Map.entry(10.0, Double.NaN))));

    @BeforeEach
    void injectMocks() {
        when(lnMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == 1.0)
                return 0.0;
            if (x == 10.0)
                return 2.302585;
            if (x == 100.0)
                return 4.60517;
            if (x == 0.1)
                return -2.302585;
            if (x == 2.0)
                return 0.693147;
            if (x == 4.0)
                return 1.386294;
            if (x == 0.5)
                return -0.693147;
            if (x == 5.0)
                return 1.609437;
            if (x == 25.0)
                return 3.218875;
            if (x == 0.2)
                return -1.609437;
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicContainer> givenArgAndBase_whenCalc_thenReturnLogBase() {
        LogBase log = new LogBase(0.0, lnMock);
        return testArgs.entrySet().stream().map(e -> dynamicContainer(String.format("log_%s tests", e.getKey()),
                e.getValue().entrySet().stream().map(
                        entry -> dynamicTest(String.format("log_%s %.2f = %.2f", e.getKey(), entry.getKey(), entry.getValue()), () -> {
                            assertEquals(entry.getValue(), log.calcWithBase(entry.getKey(), e.getKey()), eps);
                        }))));
    }
}
