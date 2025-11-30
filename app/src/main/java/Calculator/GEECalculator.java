package Calculator;

import TransportChain.*;

public class GEECalculator {
    private TOCalculator toCalculator;
    private HOCalculator hoCalculator;
    private CalculateActivityData activityCalculator;
    private ConsumptionEstimator consumptionEstimator;

    public GEECalculator() {
        this.toCalculator = new TOCalculator();
        this.hoCalculator = new HOCalculator();
        this.activityCalculator = new CalculateActivityData();
        this.consumptionEstimator = new ConsumptionEstimator();
    }

    public double calculateGEE(TransportChain chain) {
        double total = 0.0;

        for (TransportChainElement tce : chain.getElements()) {

            if (tce instanceof TransportOperation to) {
                double activityData = activityCalculator.calculateActivityData(
                        to.getLoadFactor(),
                        to.getEmptyReturn(),
                        to.getDistanceKm(),
                        to.getTotalLoadCapacity()
                );

                if (activityData <= 0) {
                    System.err.println("Error calculating activityData for TO " + to.getElementId());
                    to.setEmission(0);
                    continue;
                }

                double consumptionEi;
                try {
                    consumptionEi = consumptionEstimator.calculateEi(activityData, to.getAverageEnergyConsumption());
                } catch (IllegalArgumentException e) {
                    System.err.println("Error calculating consumptionEi for TO " + to.getElementId() + ": " + e.getMessage());
                    to.setEmission(0);
                    continue;
                }

                double emission = toCalculator.calculateWTW(consumptionEi, to.getEmissionFactorTTW(), to.getEmissionFactorWTT());
                if (emission == -1) {
                    System.err.println("Error calculating WTW for TO " + to.getElementId());
                    emission = 0;
                }

                to.setEmission(emission);
                total += to.getEmission();

            } else if (tce instanceof HubOperation hub) {

                double emission = hoCalculator.calculateEmissonsHO(hub.getEmissionFactor(), hub.getEnergyConsumption());
                if (emission == -1) {
                    System.err.println("Error calculating HO for Hub " + hub.getElementId());
                    emission = 0;
                }

                hub.setEmission(emission);
                total += hub.getEmission();
            }
        }

        return total;
    }
}