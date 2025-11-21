package org.example;

import interfaces.ICalculateActivityData;

public class CalculateActivityData implements ICalculateActivityData {

    @Override
    public double calculateActivityData(double loadFactor,
                                        double emptyReturn,
                                        double distance,
                                        double load) {

        if (loadFactor <= 0 || loadFactor > 1) return -1;
        if (emptyReturn < 0 || emptyReturn > 1) return -1;
        if (distance <= 0) return -1;
        if (load <= 0) return -1;

        double adjustedDistance = calculateAdjustedDistance(distance, emptyReturn);
        double effectiveLoad = calculateEffectiveLoad(load, loadFactor);

        return adjustedDistance * effectiveLoad;
    }

    private double calculateAdjustedDistance(double distance, double emptyReturn) {
        return distance * (1 + emptyReturn);
    }

    private double calculateEffectiveLoad(double load, double loadFactor) {
        return load * loadFactor;
    }
}
