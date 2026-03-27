package itmo.st.lab2.integrational;

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

import itmo.st.lab2.CustomFunc;
import itmo.st.lab2.math.log.LogBase;
import itmo.st.lab2.math.log.NaturalLog;
import itmo.st.lab2.math.trigonometry.Cosecant;
import itmo.st.lab2.math.trigonometry.Cosine;
import itmo.st.lab2.math.trigonometry.Cotangent;
import itmo.st.lab2.math.trigonometry.Secant;
import itmo.st.lab2.math.trigonometry.Sine;
import itmo.st.lab2.math.trigonometry.Tangent;

@ExtendWith(MockitoExtension.class)
public class IntegTestLvl1 {

    @Mock
    Sine sinMock;

    @Mock
    Cosine cosMock;

    Tangent tan;

    Cotangent cot;

    @Mock
    Cosecant cscMock;

    Secant sec;

    @Mock
    NaturalLog lnMock;

    LogBase log;

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

    @BeforeEach
    void injectMocks() {
        when(sinMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 0.5654966465209895;
            if (x == -5.56947)
                return 0.6546468130711489;
            if (x == -3.95591)
                return 0.7272572185491452;
            if (x == -3.88927)
                return 0.679937463149254;
            if (x == -2.32167)
                return -0.7310930601785698;
            if (x == -2.27421)
                return -0.7626385864868016;
            if (x == -1.5708)
                return -0.9999999999932537;
            if (x == -5.8)
                return 0.46460217941375737;
            if (x == -5.6)
                return 0.6312666378723216;
            if (x == -5.0)
                return 0.9589242746631385;
            if (x == -4.5)
                return 0.977530117665097;
            if (x == -3.9)
                return 0.6877661591839738;
            if (x == -3.8)
                return 0.6118578909427189;
            if (x == -2.4)
                return -0.6754631805511511;
            if (x == -2.3)
                return -0.7457052121767204;
            if (x == -2.0)
                return -0.9092974268256817;
            if (x == -1.0)
                return -0.8414709848078965;
            return Double.NaN;
        });

        when(cosMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 0.824750594285033;
            if (x == -5.56947)
                return 0.7559348848517233;
            if (x == -3.95591)
                return -0.6863650180976307;
            if (x == -3.88927)
                return -0.7332701045359459;
            if (x == -2.32167)
                return -0.6822777567521413;
            if (x == -2.27421)
                return -0.6468248498638663;
            if (x == -1.5708)
                return -3.6732051033465756e-6;
            if (x == -5.8)
                return 0.8855195169413189;
            if (x == -5.6)
                return 0.7755658785102496;
            if (x == -5.0)
                return 0.28366218546322625;
            if (x == -4.5)
                return -0.2107957994307797;
            if (x == -3.9)
                return -0.7259323042001402;
            if (x == -3.8)
                return -0.7909677119144168;
            if (x == -2.4)
                return -0.7373937155412454;
            if (x == -2.3)
                return -0.6662760212798241;
            if (x == -2.0)
                return -0.4161468365471424;
            if (x == -1.0)
                return 0.5403023058681398;
            return Double.NaN;
        });

        when(cscMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (x == -5.68215)
                return 1.768357082490467;
            if (x == -5.56947)
                return 1.5275412329721632;
            if (x == -3.95591)
                return 1.3750293218057952;
            if (x == -3.88927)
                return 1.470723491787492;
            if (x == -2.32167)
                return -1.3678149259900643;
            if (x == -2.27421)
                return -1.311237088863594;
            if (x == -1.5708)
                return -1.0000000000067464;

            if (x == -5.8)
                return 2.1523790552636157;
            if (x == -5.6)
                return 1.5841166632383596;
            if (x == -5.0)
                return 1.0428352127714058;
            if (x == -4.5)
                return 1.022986383671302;
            if (x == -3.9)
                return 1.453982558819829;
            if (x == -3.8)
                return 1.6343664350871603;
            if (x == -2.4)
                return -1.4804655957472617;
            if (x == -2.3)
                return -1.3410124854578807;
            if (x == -2.0)
                return -1.0997501702946164;
            if (x == -1.0)
                return -1.1883951057781212;
            return Double.NaN;
        });

        when(lnMock.calc(anyDouble())).thenAnswer(invocation -> {
            Double x = invocation.getArgument(0);
            if (Math.abs(x - 0.34597) < eps) {
                return -1.0614032128858384;
            }
            if (Math.abs(x - 3.53668) < eps) {
                return 1.2631884339789508;
            }
            if (Math.abs(x - 2) < eps) {
                return 0.6931471805599453;
            }
            if (Math.abs(x - 3) < eps) {
                return 1.0986122886681098;
            }
            if (Math.abs(x - 5) < eps) {
                return 1.6094379124341003;
            }
            if (Math.abs(x - 10) < eps) {
                return 2.302585092994046;
            }
            if (Math.abs(x - 0.3) < eps) {
                return -1.2039728043259361;
            }
            if (Math.abs(x - 0.5) < eps) {
                return -0.6931471805599453;
            }
            if (Math.abs(x - 2) < eps) {
                return 0.6931471805599453;
            }
            if (Math.abs(x - 4) < eps) {
                return 1.3862943611198906;
            }
            return Double.NaN;
        });
    }

    @TestFactory
    Stream<DynamicTest> givenArg_whenCalc_returnCustomFunc() {
        tan = new Tangent(sinMock, cosMock);
        cot = new Cotangent(sinMock, cosMock);
        sec = new Secant(cosMock);
        log = new LogBase(0, lnMock);
        CustomFunc f = new CustomFunc(sinMock, cosMock, tan, cot, cscMock, sec, lnMock, log);
        return testArgs.entrySet().stream()
                .map(e -> dynamicTest(String.format("f %.2f = %.2f", e.getKey(), e.getValue()), () -> {
                    assertEquals(e.getValue(), f.calc(e.getKey()), eps);
                }));
    }
}
