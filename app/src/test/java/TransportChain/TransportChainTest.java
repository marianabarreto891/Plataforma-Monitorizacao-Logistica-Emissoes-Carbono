package TransportChain;

import DataIngest.FuelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DataIngest.Mode;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransportChainTest {

    private TransportChain chain;

    @BeforeEach
    void setUp() {
        chain = new TransportChain(new ArrayList<>());
    }

    @Test
    void testAddTransportOperation() {
        TransportOperation op = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );

        chain.addElement(op);

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().contains(op));
    }

    @Test
    void testAddNullElement() {
        int initialSize = chain.getElements().size();
        chain.addElement(null);
        assertEquals(initialSize, chain.getElements().size(), "List should not change when adding null");
        assertTrue(chain.getElements().isEmpty(), "List should remain empty");
    }

    @Test
    void testAddHubOperation() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 100.0, 0.5, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().contains(ho));
    }

    @Test
    void testRemoveTransportOperation() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.GASOLINE, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        boolean removed = chain.removeElement(to);
        assertTrue(removed);
        assertFalse(chain.getElements().contains(to));
        assertEquals(0, chain.getElements().size());
    }

    @Test
    void testRemoveNonExistentTransportOperation() {
        TransportOperation to = new TransportOperation(
                "TO999", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                200.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );

        boolean removed = chain.removeElement(to);
        assertFalse(removed);
        assertTrue(chain.getElements().isEmpty());
    }

    @Test
    void testRemoveExistingHubOperation() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 100.0, 0.5, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean removed = chain.removeElement(ho);
        assertTrue(removed);
        assertFalse(chain.getElements().contains(ho));
        assertEquals(0, chain.getElements().size());
    }

    @Test
    void testRemoveNonExistentHubOperation() {
        HubOperation ho = new HubOperation("HO999", "CHAIN001", "PT", 2025, 200.0, 0.5, Mode.SEA, "Central Operation", FuelType.DIESEL);

        boolean removed = chain.removeElement(ho);
        assertFalse(removed);
        assertTrue(chain.getElements().isEmpty());
    }

    @Test
    void testEditHubOperation_Success() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 0.5, 100.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean result = chain.editHubOperation("HO001", 20.0, 10.0);
        assertTrue(result);
        assertEquals(10.0, ho.getEnergyConsumption(), 0.001);
        assertEquals(20.0, ho.getEmissionFactor(), 0.001);
    }

    @Test
    void testEditHubOperation_Failure_NonexistentID() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 0.5, 100.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean result = chain.editHubOperation("HO999", 10.0, 20.0);
        assertFalse(result);
        assertEquals(100.0, ho.getEnergyConsumption(), 0.001);
        assertEquals(0.5, ho.getEmissionFactor(), 0.001);
    }

    @Test
    void testEditTransportOperation_Failure_NegativeValue() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO001", Mode.ROAD, "Truck", -100.0, 50.0, 0.4, 0.2, 4.5, 0.5, 0.5, 0.0);
        assertFalse(result);
    }

    @Test
    void testEditTransportOperation_Success_PositiveValues() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.DIESEL,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO001", Mode.ROAD, "Truck", 120.0, 40.0, 0.6, 0.3, 8.0, 0.3, 0.1, 0.0);
        assertTrue(result);
    }

    @Test
    void testEditTransportOperation_Failure_NonexistentID() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO999", Mode.ROAD, "Truck", 100.0, 50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0);
        assertFalse(result);
    }

    @Test
    void testEditTransportOperation_ZeroValues() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                100.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO001", Mode.ROAD, "Truck", 0, 0, 0, 0, 0, 0, 0, 0);
        assertTrue(result);
    }

    @Test
    void testEditHubOperation_ZeroValues() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 10.0, 20.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean result = chain.editHubOperation("HO001", 0.0, 0.0);
        assertTrue(result);
        assertEquals(0.0, ho.getEnergyConsumption(), 0.001);
        assertEquals(0.0, ho.getEmissionFactor(), 0.001);
    }

    @Test
    void testEditHubOperation_NormalValues() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 10.0, 20.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean result = chain.editHubOperation("HO001", 0.5, 100.0);
        assertTrue(result);
        assertEquals(100.0, ho.getEnergyConsumption(), 0.001);
        assertEquals(0.5, ho.getEmissionFactor(), 0.001);
    }

    @Test
    void testEditHubOperation_MaxValues() {
        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 10.0, 20.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        boolean result = chain.editHubOperation("HO001", 1000.0, 1_000_000.0);
        assertTrue(result);
        assertEquals(1_000_000.0, ho.getEnergyConsumption(), 0.001);
        assertEquals(1000.0, ho.getEmissionFactor(), 0.001);
    }

    @Test
    void testEditTransportOperation_TypicalValues() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                1000.0, FuelType.DIESEL,500.0, 10.0, 5.0, 50.0, 2.0, 1.0, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO001", Mode.ROAD, "Truck", 400, 10, 0.5, 0.5, 0.5, 1.5, 1.5, 0.5);
        assertTrue(result);
    }

    @Test
    void testEditTransportOperation_UpperLimitValues() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                1000.0, FuelType.DIESEL,500.0, 10.0, 5.0, 50.0, 2.0, 1.0, 0.0
        );
        chain.addElement(to);

        boolean result = chain.editTransportOperation("TO001", Mode.ROAD, "CHAIN001", 1000.0, 80.0, 0.99, 0.99, 1000.0, 1000.0, 1000.0, 1);
        assertTrue(result);
    }

    // White BOX Testing for the method CalculateTotalGEE

    @Test
    void testCalculateTotalGEE_withEmptyOperations() {
        double total = chain.calculateTotalGEE();
        assertEquals(0.0, total);
    }


    @Test
    void testCalculateTotalGEE_withValidOperations() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                1000.0, FuelType.GASOLINE,500.0, 10.0, 5.0, 50.0, 2.0, 1.0, 0.0);
        chain.addElement(to);

        HubOperation ho = new HubOperation("HO001", "CHAIN001", "PT", 2025, 10.0, 20.0, Mode.SEA, "Central Operation", FuelType.DIESEL);
        chain.addElement(ho);

        double total = chain.calculateTotalGEE();

        assertEquals(200.0, total);
    }

    @Test
    void testCalculateTotalGEE_withInvalidOperations() {
        TransportOperation to = new TransportOperation(
                "TO001", "CHAIN001", "PT", 2025, Mode.ROAD, "Truck",
                -100.0, FuelType.GASOLINE,50.0, 0.8, 0.1, 5.0, 0.2, 0.1, 0.0
        );
        chain.addElement(to);

        double total = chain.calculateTotalGEE();
        assertEquals(0.0, total, 0.01);
    }
}