package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsumptionEstimatorTest {

    // AC2 + ECP (valid class) + normal valid scenario
    @Test
    public void testValidEi() {
        ConsumptionEstimator ce = new ConsumptionEstimator();
        assertEquals(350.0, ce.calculateEi(100, 3.5), 0.01);
    }

    // AC1 + ECP (invalid class) - values below boundary for doubles
    @Test
    public void testInvalidInputs_BelowBoundary() {
        ConsumptionEstimator ce = new ConsumptionEstimator();

        // Below lower boundary for t.km
        assertThrows(IllegalArgumentException.class,
                () -> ce.calculateEi(-0.1, 3.5));

        // Below lower boundary for EC
        assertThrows(IllegalArgumentException.class,
                () -> ce.calculateEi(10, -0.1));
    }

    // AC1 + AC2 + BVA (boundary values = 0)
    @Test
    public void testBoundaryValues_Zero() {
        ConsumptionEstimator ce = new ConsumptionEstimator();

        assertEquals(0, ce.calculateEi(0, 3.5), 0.01);
        assertEquals(0, ce.calculateEi(10, 0), 0.01);
    }

    // BVA - Just above boundary (0.1)
    @Test
    public void testJustAboveBoundary() {
        ConsumptionEstimator ce = new ConsumptionEstimator();

        assertEquals(0.35, ce.calculateEi(0.1, 3.5), 0.01);
        assertEquals(1.0, ce.calculateEi(10, 0.1), 0.01);
    }
}


