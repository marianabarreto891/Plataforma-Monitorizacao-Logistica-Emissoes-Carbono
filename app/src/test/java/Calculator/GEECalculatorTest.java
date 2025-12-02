package Calculator;

import DataIngest.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import TransportChain.*;
import DataIngest.Mode;

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
    void TC_GEE_Calculator_01_EmptyChain() {
        double totalGEE = calculator.calculateGEE(chain);
        assertEquals(0.0, totalGEE, "Total GEE for empty chain should be 0");
    }

    @Test
    void TC_GEE_Calculator_02_MinimalTO() {
        TransportOperation op = new TransportOperation(
                "TO001",
                "CHAIN001",
                "PT",
                2025,
                Mode.ROAD,               // example mode
                "LightTruck",            // vehicle category
                100.0,                   // distanceKm
                10.0,                    // totalLoadCapacity
                0.8,                     // loadFactor
                0.1,                     // emptyReturn
                5.0,                     // averageEnergyConsumption
                0.1,                     // emissionFactorTTW
                0.1,                     // emissionFactorWTT
                0.1                      // emissionsPerTKm
        );

        chain.addElement(op);
        double totalGEE = calculator.calculateGEE(chain);
        assertTrue(totalGEE > 0, "Emission for minimal TO should be > 0");
    }

    @Test
    void TC_GEE_Calculator_03_MediumTO() {
        TransportOperation op = new TransportOperation(
                "TO002",
                "CHAIN002",
                "PT",
                2025,
                Mode.ROAD,
                "Truck",
                50.0,
                50.0,
                0.5,
                0.5,
                5.0,
                10.0,
                10.0,
                0.5
        );

        chain.addElement(op);
        double totalGEE = calculator.calculateGEE(chain);
        assertTrue(totalGEE > 0, "Emission for medium TO should be > 0");
    }

    @Test
    void TC_GEE_Calculator_04_InvalidTO() {
        TransportOperation invalidTO = new TransportOperation(
                "TO003",
                "CHAIN003",
                "PT",
                2025,
                Mode.ROAD,
                "Truck",
                0.0,
                10.0,
                -1.0,
                0.5,
                5.0,
                10.0,
                10.0,
                0.0
        );

        chain.addElement(invalidTO);
        double totalGEE = calculator.calculateGEE(chain);
        assertEquals(0.0, totalGEE, "Emission for invalid TO should be 0");
    }

    @Test
    void TC_GEE_Calculator_05_MinimalHO() {
        HubOperation minHO = new HubOperation(
                "HO001",
                "CHAIN005",
                "PT",
                2025,
                0.0,
                0.0,
                Mode.ROAD,
                "truck",
                FuelType.DIESEL
        );

        chain.addElement(minHO);
        double totalGEE = calculator.calculateGEE(chain);
        assertTrue(totalGEE >= 0, "Emission for minimal HO should be >= 0");
    }

    @Test
    void TC_GEE_Calculator_06_MaximalHO() {
        HubOperation maxHO = new HubOperation(
                "HO002",
                "CHAIN006",
                "PT",
                2025,
                999.0,
                999.0,
                Mode.ROAD,
                "truck",
                FuelType.DIESEL
        );

        chain.addElement(maxHO);
        double totalGEE = calculator.calculateGEE(chain);
        assertTrue(totalGEE > 0, "Emission for maximal HO should be > 0");
    }

    @Test
    void TC_GEE_Calculator_07_InvalidHO() {
        HubOperation invalidHO = new HubOperation(
                "HO003",
                "CHAIN007",
                "PT",
                2025,
                -1.0,
                -1.0,
                Mode.ROAD,
                "truck",
                FuelType.DIESEL
        );

        chain.addElement(invalidHO);
        double totalGEE = calculator.calculateGEE(chain);
        assertEquals(0.0, totalGEE, "Emission for invalid HO should be 0");
    }

    @Test
    void TC_GEE_Calculator_08_MixedChain() {
        TransportOperation mediumTO = new TransportOperation(
                "TO010",
                "CHAIN010",
                "PT",
                2025,
                Mode.ROAD,
                "Truck",
                50.0,
                50.0,
                0.5,
                0.5,
                500.0,
                50.0,
                50.0,
                5.0
        );

        HubOperation mediumHO = new HubOperation(
                "HO010",
                "CHAIN010",
                "PT",
                2025,
                50.0,
                5.0,
                Mode.ROAD,
                "truck",
                FuelType.DIESEL
        );

        chain.addElement(mediumTO);
        chain.addElement(mediumHO);

        double totalGEE = calculator.calculateGEE(chain);
        double expectedTotal = mediumTO.getEmission() + mediumHO.getEmission();

        assertEquals(expectedTotal, totalGEE, 0.0001, "Total GEE should be sum of TO + HO emissions");
    }

    @Test
    void TC_GEE_Calculator_09_ZeroInputs() {
        TransportOperation zeroTO = new TransportOperation(
                "TO000",
                "CHAIN009",
                "PT",
                2025,
                Mode.ROAD,
                "Truck",
                0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0
        );

        HubOperation zeroHO = new HubOperation(
                "HO000",
                "CHAIN009",
                "PT",
                2025,
                0.0,
                0.0,
                Mode.ROAD,
                "truck",
                FuelType.DIESEL
        );

        chain.addElement(zeroTO);
        chain.addElement(zeroHO);

        double totalGEE = calculator.calculateGEE(chain);
        assertEquals(0.0, totalGEE, "Total GEE for TO and HO with zero attributes should be 0");
    }
}