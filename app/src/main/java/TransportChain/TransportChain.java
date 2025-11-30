package TransportChain;

import Calculator.GEECalculator;
import DataIngest.Mode;

import java.util.ArrayList;
import java.util.List;

public class TransportChain {

    private List<TransportChainElement> operations;

    public TransportChain(List<TransportChainElement> elements) {
        this.operations = elements != null ? elements : new ArrayList<>();
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

    public boolean editTransportOperation(String elementId,
                                          Mode mode,
                                          String vehicleCategory,
                                          double distanceKm,
                                          double totalLoadCapacity,
                                          double loadFactor,
                                          double emptyReturn,
                                          double averageEnergyConsumption,
                                          double emissionFactorTTW,
                                          double emissionFactorWTT,
                                          double emissionsPerTKm) {

        if (distanceKm < 0 || totalLoadCapacity < 0 || loadFactor < 0 || emptyReturn < 0
                || averageEnergyConsumption < 0 || emissionFactorTTW < 0 || emissionFactorWTT < 0 || emissionsPerTKm < 0) {
            return false;
        }

        for (TransportChainElement tc : operations) {
            if (tc instanceof TransportOperation to && to.getElementId().equals(elementId)) {
                to.setMode(mode);
                to.setVehicleCategory(vehicleCategory);
                to.setDistanceKm(distanceKm);
                to.setTotalLoadCapacity(totalLoadCapacity);
                to.setLoadFactor(loadFactor);
                to.setEmptyReturn(emptyReturn);
                to.setAverageEnergyConsumption(averageEnergyConsumption);
                to.setEmissionFactorTTW(emissionFactorTTW);
                to.setEmissionFactorWTT(emissionFactorWTT);
                to.setEmissionsPerTKm(emissionsPerTKm);
                return true;
            }
        }
        return false;
    }

    public boolean editHubOperation(String elementId, double newEmissionFactor, double newEnergyConsumption) {
        for (TransportChainElement tc : operations) {
            if (tc instanceof HubOperation ho && ho.getElementId().equals(elementId)) {
                ho.setEmissionFactor(newEmissionFactor);
                ho.setEnergyConsumption(newEnergyConsumption);
                return true;
            }
        }
        return false;
    }

    public double calculateTotalGEE() {
        double total = 0.0;

        if (operations.isEmpty()) {
            System.err.println("The transport chain is empty.");
            return 0.0;
        }

        GEECalculator calculator = new GEECalculator();

        try {
            total = calculator.calculateGEE(this);
        } catch (Exception e) {
            System.err.println("Error calculating total GEE: " + e.getMessage());
            total = 0.0;
        }

        return total;
    }
}
