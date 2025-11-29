package Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestCalculateActivityData {

    private final CalculateActivityData calc = new CalculateActivityData();

    /*VALID VALUES */

    @Test
    void testActivityData_Valid() {
        double result = calc.calculateActivityData(0.8, 0.2, 100, 10);

        assertEquals(960.0, result, 0.0001);
    }

    @Test
    void testActivityData_LowerValidLF() {
        double result = calc.calculateActivityData(0.01, 0.2, 100, 10);

        assertEquals(12.0, result, 0.001);
    }

    @Test
    void testActivityData_UpperValidLF() {
        double result = calc.calculateActivityData(1, 0.2, 100, 10);

        assertEquals(1200, result, 0.0001);
    }

    @Test
    void testActivityData_LowerValidER() {
        double result = calc.calculateActivityData(0.8, 0.0, 100, 10);

        assertEquals(800.0, result, 0.0001);
    }

    @Test
    void testActivityData_UpperValidER() {
        double result = calc.calculateActivityData(0.8, 1, 100, 10);

        assertEquals(1600.0, result, 0.0001);
    }

    @Test
    void testActivityData_LowerValidDistance() {
        double result = calc.calculateActivityData(0.8, 0.2, 0.01, 10);

        assertEquals(0.096, result, 0.0001);
    }

    @Test
    void testActivityData_LowerValidLoad() {
        double result = calc.calculateActivityData(0.8, 0.2, 100, 0.01);

        assertEquals(0.96, result, 0.0001);
    }


    /*INVALID VALUES */

    @Test
    void testActivityData_UpperInvalidLF() {
        double result = calc.calculateActivityData(1.01, 0.2, 100, 10);

        assertEquals(-1, result, 0.0001);
    }

    @Test
    void testActivityData_LowerInvalidLF() {
        double result = calc.calculateActivityData(0.0, 0.2, 100, 10);

        assertEquals(-1, result, 0.0001);
    }

    @Test
    void testActivityData_UpperInvalidER() {
        double result = calc.calculateActivityData(0.8, 1.01, 100, 10);

        assertEquals(-1, result, 0.0001);
    }

    @Test
    void testActivityData_LowerInvalidER() {
        double result = calc.calculateActivityData(0.8, -0.01, 100, 10);

        assertEquals(-1, result, 0.0001);
    }

    @Test
    void testActivityData_LowerInvalidDistance() {
        double result = calc.calculateActivityData(0.8, 0.2, -0.01, 10);

        assertEquals(-1, result, 0.0001);
    }

    @Test
    void testActivityData_LowerInvalidLoad() {
        double result = calc.calculateActivityData(0.8, 0.2, 100, -1);

        assertEquals(-1, result, 0.0001);
    }

}