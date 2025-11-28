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

    public double calcularGEE(TransportChain chain) {
        double total = 0.0;

        for (TransportChainElement tce : chain.getElements()) {

            if (tce instanceof TransportOperation to) {
                double activityData = activityCalculator.calculateActivityData(
                        to.getFatorCarga(),
                        to.getEmptyReturn(),
                        to.getDistanciaKm(),
                        to.getCargaTotalCapacidade()
                );

                if (activityData <= 0) {
                    System.err.println("Erro no cálculo de activityData para TO " + to.getElementId());
                    to.setEmissao(0);
                    continue;
                }

                double consumoEi;
                try {
                    consumoEi = consumptionEstimator.calculateEi(activityData, to.getConsumoMedioE());
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro no cálculo de consumoEi para TO " + to.getElementId() + ": " + e.getMessage());
                    to.setEmissao(0);
                    continue;
                }

                double emissao = toCalculator.calcularWtw(consumoEi, to.getFatorEmissaoTTW(), to.getFatorEmissaoWTT());
                if (emissao == -1) {
                    System.err.println("Erro no cálculo WTW para TO " + to.getElementId());
                    emissao = 0;
                }

                to.setEmissao(emissao);
                total += to.getEmissao();

            } else if (tce instanceof HubOperation hub) {

                double emissao = hoCalculator.calculateEmissonsHO(hub.getFatorEmissao(), hub.getConsumoEnergia());
                if (emissao == -1) {
                    System.err.println("Erro no cálculo HO para Hub " + hub.getElementId());
                    emissao = 0;
                }

                hub.setEmissao(emissao);
                total += hub.getEmissao();
            }
        }
        return total;
    }
}