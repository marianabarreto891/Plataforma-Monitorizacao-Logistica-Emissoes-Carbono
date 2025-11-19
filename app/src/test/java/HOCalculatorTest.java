import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HOCalculatorTest {

    @Test
    public void testCalculateEmissionsHO() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1000;
        double Ei = 1000;

        double expectedValue = 1000000;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOZeroInputs() {
        HOCalculator calculator = new HOCalculator();

        double EF = 0;
        double Ei = 0;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOZeroInputs1() {
        HOCalculator calculator = new HOCalculator();

        double EF = 0;
        double Ei = 1;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOZeroInputs2() {
        HOCalculator calculator = new HOCalculator();

        double EF = 0;
        double Ei = 1000;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOZeroInputs3() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1000;
        double Ei = 0;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHONegativeInputs() {
        HOCalculator calculator = new HOCalculator();

        double EF = -1;
        double Ei = -1;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHONegativeInputs1() {
        HOCalculator calculator = new HOCalculator();

        double EF = -1;
        double Ei = 0;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsNegative2() {
        HOCalculator calculator = new HOCalculator();

        double EF = 0;
        double Ei = -1;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsNegative3() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1000;
        double Ei = -1;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsNegative4() {
        HOCalculator calculator = new HOCalculator();

        double EF = -1;
        double Ei = 1000;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1001;
        double Ei = 1001;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax1() {
        HOCalculator calculator = new HOCalculator();

        double EF = 0;
        double Ei = 1001;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax2() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1001;
        double Ei = 0;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax3() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1001;
        double Ei = -1;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax4() {
        HOCalculator calculator = new HOCalculator();

        double EF = -1;
        double Ei = 1001;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax5() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1000;
        double Ei = 1001;

        double expectedValue = -1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsMax6() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1000;
        double Ei = 1;

        double expectedValue = 1000;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsOne() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1;
        double Ei = 1000;

        double expectedValue = 1000;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCalculateEmissionsHOInputsOne1() {
        HOCalculator calculator = new HOCalculator();

        double EF = 1;
        double Ei = 1;

        double expectedValue = 1;
        double actualValue = calculator.calculateEmissonsHO(EF, Ei);
        assertEquals(expectedValue, actualValue);
    }

}
