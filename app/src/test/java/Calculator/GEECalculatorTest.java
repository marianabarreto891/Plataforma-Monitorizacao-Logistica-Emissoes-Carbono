package Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TransportChain.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GEECalculatorTest {

    private TransportChain chain;
    private GEECalculator calculator;

    @BeforeEach
    void setUp() {
        chain = new TransportChain(new ArrayList<>());
        calculator = new GEECalculator();
    }

    @Test
    void TC_GEE_Calculator_01_CadeiaVazia() {
        GEECalculator calculator = new GEECalculator();

        double totalGEE = calculator.calcularGEE(chain);

        // Verifica resultado esperado
        assertEquals(0.0, totalGEE, "Total GEE para cadeia vazia deve ser 0");
    }

    @Test
    void TC_GEE_Calculator_02_UmTOValoresMinimos() {
        TransportOperation op = new TransportOperation(
                "TO001",
                "CHAIN001",
                "PT",
                100.0,
                0.8,
                0.9,
                0.1,
                5.0,
                0.1,
                0.1
        );

        chain.addElement(op);

        double totalGEE = calculator.calcularGEE(chain);

        assertTrue(totalGEE > 0, "Emissão para TO mínimo deve ser maior que 0");
    }

    @Test
    void TC_GEE_Calculator_03_UmTOValidoMaximos() {
        TransportOperation op = new TransportOperation(
                "TO002",
                "CHAIN002",
                "PT",
                50.0,
                50.0,
                0.5,
                0.5,
                5.0,
                10.0,
                10.0
        );

        chain.addElement(op);

        double totalGEE = calculator.calcularGEE(chain);

        assertTrue(totalGEE > 0, "Emissão para TO médio deve ser maior que 0");
    }

    @Test
    void TC_GEE_Calculator_04_TOInvalido() {
        TransportOperation invalidTO = new TransportOperation(
                "TO003",
                "CHAIN003",
                "PT",
                0.0,
                10.0,
                -1,
                0.5,
                5.0,
                10.0,
                10.0
        );

        chain.addElement(invalidTO);

        double totalGEE = calculator.calcularGEE(chain);

        assertEquals(0.0, totalGEE, "Emissão para TO inválido deve ser 0");
    }

    @Test
    void TC_GEE_Calculator_05_HOValidoMinimo() {
        HubOperation minHO = new HubOperation(
                "HO001",
                "CHAIN005",
                "PT",
                0.0,
                0.0
        );

        chain.addElement(minHO);

        double totalGEE = calculator.calcularGEE(chain);

        assertTrue(totalGEE >= 0, "Emissão para HO mínimo deve ser >= 0");
    }

    @Test
    void TC_GEE_Calculator_06_HOValidoMaximo() {
        HubOperation maxHO = new HubOperation(
                "HO002",
                "CHAIN006",
                "PT",
                999,
                999
        );

        chain.addElement(maxHO);

        double totalGEE = calculator.calcularGEE(chain);

        assertTrue(totalGEE > 0, "Emissão para HO máximo deve ser maior que 0");
    }

    @Test
    void TC_GEE_Calculator_07_HOInvalido() {
        HubOperation invalidHO = new HubOperation(
                "HO003",
                "CHAIN007",
                "PT",
                -1.0,
                -1.0
        );

        chain.addElement(invalidHO);

        double totalGEE = calculator.calcularGEE(chain);

        assertEquals(0.0, totalGEE, "Emissão para HO inválido deve ser 0");
    }

    @Test
    void TC_GEE_Calculator_08_CadeiaMistaValida() {
        TransportOperation mediumTO = new TransportOperation(
                "TO010",
                "CHAIN010",
                "PT",
                50.0,
                50.0,
                0.5,
                0.5,
                500.0,
                50.0,
                50.0
        );

        HubOperation mediumHO = new HubOperation(
                "HO010",
                "CHAIN010",
                "PT",
                50.0,
                5.0
        );

        chain.addElement(mediumTO);
        chain.addElement(mediumHO);

        double totalGEE = calculator.calcularGEE(chain);

        double expectedTotal = mediumTO.getEmissao() + mediumHO.getEmissao();

        assertEquals(expectedTotal, totalGEE, 0.0001, "Total GEE deve ser soma das emissões de TO + HO");
    }

    @Test
    void TC_GEE_Calculator_09_InputsTriviais() {
        TransportOperation zeroTO = new TransportOperation(
                "TO000",
                "CHAIN009",
                "PT",
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
        );

        HubOperation zeroHO = new HubOperation(
                "HO000",
                "CHAIN009",
                "PT",
                0.0, 0.0
        );

        chain.addElement(zeroTO);
        chain.addElement(zeroHO);

        double totalGEE = calculator.calcularGEE(chain);

        assertEquals(0.0, totalGEE, "Total GEE para TO e HO com atributos zero deve ser 0");
    }
}