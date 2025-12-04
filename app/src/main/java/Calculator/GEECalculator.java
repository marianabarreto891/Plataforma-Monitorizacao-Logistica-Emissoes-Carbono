package Calculator;

import TransportChain.HubOperation;
import TransportChain.TransportChain;
import TransportChain.TransportChainElement;
import TransportChain.TransportOperation;

public class GEECalculator {
    private TOCalculator toCalculator;
    private HOCalculator hoCalculator;
    private CalculateActivityData activityCalculator;
    private ConsumptionEstimator consumptionEstimator;

    
    /**
     * Initializes the GEE calculator and instantiates all necessary sub-calculators.
     */
    public GEECalculator() {
        this.toCalculator = new TOCalculator();
        this.hoCalculator = new HOCalculator();
        this.activityCalculator = new CalculateActivityData();
        this.consumptionEstimator = new ConsumptionEstimator();
    }

    /**
     * Calculates the total emissions for a specific transport chain by processing each element.
     * @param chain The transport chain containing the list of operations to process.
     * @return The total sum of emissions calculated for the entire chain.
     */
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