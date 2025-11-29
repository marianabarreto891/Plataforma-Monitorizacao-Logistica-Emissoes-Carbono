package TransportChain;

// import Calculator.GEECalculator;

import java.util.List;

public class TransportChain {

    private List<TransportChainElement> operations;

    public TransportChain(List<TransportChainElement> elements) {
        this.operations = elements;
    }

    public List<TransportChainElement> getElements() {
        return operations;
    }

    public void addElement(TransportChainElement element) {
        if (element != null) {
            operations.add(element);
        }
    }

    public boolean removeElement(TransportChainElement element) {
        return operations.remove(element);
    }

    public boolean editarTransportOperation(String elementId,
                                            double distanciaKm,
                                            double cargaTotalCapacidade,
                                            double fatorCarga,
                                            double emptyReturn,
                                            double consumoMedioE,
                                            double fatorEmissaoTTW,
                                            double fatorEmissaoWTT) {

        if (distanciaKm < 0 || cargaTotalCapacidade < 0 || fatorCarga < 0 || emptyReturn < 0
                || consumoMedioE < 0 || fatorEmissaoTTW < 0 || fatorEmissaoWTT < 0) {
            return false;
        }

        for (TransportChainElement tc : operations) {
            if (tc instanceof TransportOperation to && to.getElementId().equals(elementId)) {
                to.setDistanciaKm(distanciaKm);
                to.setCargaTotalCapacidade(cargaTotalCapacidade);
                to.setFatorCarga(fatorCarga);
                to.setEmptyReturn(emptyReturn);
                to.setConsumoMedioE(consumoMedioE);
                to.setFatorEmissaoTTW(fatorEmissaoTTW);
                to.setFatorEmissaoWTT(fatorEmissaoWTT);
                return true;
            }
        }
        return false;
    }


    public boolean editarHubOperation(String elementId, double novoFatorEmissao, double novoConsumoEnergia) {
        for (TransportChainElement tc : operations) {
            if (tc instanceof HubOperation ho && ho.getElementId().equals(elementId)) {
                ho.setConsumoEnergia(novoConsumoEnergia);
                ho.setFatorEmissao(novoFatorEmissao);
                return true;
            }
        }
        return false;
    }

    /*
    public double calcularGEE_Total() {
        double total = 0.0;

        if (operations == null || operations.isEmpty()) {
            System.err.println("A cadeia de transporte está vazia.");
            return 0.0;
        }

        GEECalculator calculator = new GEECalculator();

        try {
            total = calculator.calcularGEE(this);
        } catch (Exception e) {
            System.err.println("Erro ao calcular GEE total: " + e.getMessage());
            total = 0.0;
        }

        return total;
    }
    */
}
