/**
 * Estimates energy intensity (Ei) based on ton-kilometer and energy consumption.
 */
package org.example;

public class ConsumptionEstimator {

    /**
     * Calculates energy intensity (Ei).
     * Throws an exception if inputs are negative.
     */
    public double calculateEi(double tonkm, double energyAverage) {
        if (tonkm < 0 || energyAverage < 0) {
            throw new IllegalArgumentException("Values cannot be negative.");
        }
        return tonkm * energyAverage;
    }
}
