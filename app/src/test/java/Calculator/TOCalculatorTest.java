package Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TOCalculatorTest {

    // ===========================
    // Black Box Testing
    // ===========================


    // ===========================
    // ECP
    // ===========================
    @Test
    void testInputsValid() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(5000, 500, 500);

        assertEquals(5000000, wtw);
    }

    @Test
    void testNegativeInputs() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(-100, 500, 500);

        assertEquals(-1, wtw);
    }

    @Test
    void testZeroInputs() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(0, 0, 0);

        assertEquals(0, wtw);
    }

    // ===========================
    // BVA
    // ===========================

    @Test
    void testAllMinValues() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(0, 0, 0);

        assertEquals(0, wtw);
    }

    @Test
    void testAllMaxValues() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(10000, 1000, 1000);

        assertEquals(20000000, wtw);
    }



    // ===========================
    // BVA: ConsumoEi
    // ===========================

    @Test
    void testMinConsumo() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(0, 300, 300);

        assertEquals(0, wtw);
    }

    @Test
    void testMinConsumoMinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(-1, 300, 300);

        assertEquals(-1, wtw);
    }

    @Test
    void testMinConsumoPlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1, 300, 300);

        assertEquals(600, wtw);
    }

    @Test
    void testMaxConsumo() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(10000, 300, 300);

        assertEquals(6000000, wtw);
    }

    @Test
    void testMaxConsumoMinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(9999, 300, 300);

        assertEquals(5999400, wtw);
    }

    @Test
    void testMaxConsumoPlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(10001, 300, 300);

        assertEquals(-1, wtw);
    }

    // ===========================
    // BVA: efTTW
    // ===========================

    @Test
    void testMin_EFTTW() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 0, 300);

        assertEquals(300000, wtw);
    }

    @Test
    void testMin_EFTTW_MinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, -1, 300);

        assertEquals(-1, wtw);
    }

    @Test
    void testMin_EFTTW_PlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 1, 300);

        assertEquals(301000, wtw);
    }

    @Test
    void testMax_EFTTW() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 1000, 300);

        assertEquals(1300000, wtw);
    }

    @Test
    void testMax_EFTTW_MinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 999, 300);

        assertEquals(1299000, wtw);
    }

    @Test
    void testMax_EFTTW_PlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 1001, 300);

        assertEquals(-1, wtw);
    }

    // ===========================
    // BVA: efWTT
    // ===========================

    @Test
    void testMin_EFWTT() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, 0);

        assertEquals(300000, wtw);
    }

    @Test
    void testMin_EFWTT_MinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, -1);

        assertEquals(-1, wtw);
    }

    @Test
    void testMin_EFWTT_PlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, 1);

        assertEquals(301000, wtw);
    }

    @Test
    void testMax_EFWTT() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, 1000);

        assertEquals(1300000, wtw);
    }

    @Test
    void testMax_EFWTT_MinusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, 999);

        assertEquals(1299000, wtw);
    }

    @Test
    void testMax_EFWTT_PlusOne() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(1000, 300, 1001);

        assertEquals(-1, wtw);
    }

    // ===========================
    // White Box Testing
    // ===========================

    // ------------------------------
    // 1. Testes do primeiro if (consumoEi)
    // ------------------------------

    @Test
    void testPrimeiroIfPrimeiraCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(-1, 100, 100);

        assertEquals(-1, wtw);
    }

    @Test
    void testPrimeiroIfSegundaCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(20000, 100, 100);

        assertEquals(-1, wtw);
    }

    // ------------------------------
    // 2. Testes do primeiro if (efTTW)
    // ------------------------------

    @Test
    void testSegundoIfPrimeiraCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(100, -1, 100);

        assertEquals(-1, wtw);
    }

    @Test
    void testSegundaIfSegundaCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(100, 1500, 100);

        assertEquals(-1, wtw);
    }

    // ------------------------------
    // 3. Testes do primeiro if (efWTT)
    // ------------------------------

    @Test
    void testTerceiroIfPrimeiraCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(100, 100, -1);

        assertEquals(-1, wtw);
    }

    @Test
    void testTerceiroIfSegundaCondicao() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(100, 100, 1500);

        assertEquals(-1, wtw);
    }

    // ------------------------------
    // 4. Caminho normal (tudo válido)
    // ------------------------------

    @Test
    void TestCaminhoNormal() {
        TOCalculator calc = new TOCalculator();

        double wtw = calc.calcularWtw(100, 100, 100);

        assertEquals(20000, wtw);
    }
}
