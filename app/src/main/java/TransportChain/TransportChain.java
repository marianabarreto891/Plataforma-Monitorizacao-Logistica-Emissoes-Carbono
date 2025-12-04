package TransportChain;

import java.util.ArrayList;
import java.util.List;

import Calculator.GEECalculator;
import DataIngest.Mode;

public class TransportChain {

    private List<TransportChainElement> operations;

    /**
     * Initializes a new TransportChain with a given list of operations.
     * @param elements The initial list of transport chain elements (creates an empty list if null).
     */
    public TransportChain(List<TransportChainElement> elements) {
        this.operations = elements != null ? elements : new ArrayList<>();
    }

    /**
     * Retrieves the list of all elements currently in the transport chain.
     * @return The list of TransportChainElement objects.
     */
    public List<TransportChainElement> getElements() {
        return operations;
    }

    /**
     * Adds a new element (TransportOperation or HubOperation) to the chain.
     * @param element The element object to be added.
     */
    public void addElement(TransportChainElement element) {
        if (element != null) {
            operations.add(element);
        }
    }

    /**
     * Removes a specific element from the transport chain.
     * @param element The element object to be removed.
     * @return true if the element was found and removed, false otherwise.
     */
    public boolean removeElement(TransportChainElement element) {
        return operations.remove(element);
    }


    /**
     * Updates the attributes of an existing TransportOperation identified by its ID.
     * @param elementId The unique identifier of the element to edit.
     * @param mode The new transport mode.
     * @param vehicleCategory The new vehicle category.
     * @param distanceKm The new distance in kilometers.
     * @param totalLoadCapacity The new total load capacity.
     * @param loadFactor The new load factor.
     * @param emptyReturn The new empty return percentage.
     * @param averageEnergyConsumption The new average energy consumption.
     * @param emissionFactorTTW The new Tank-to-Wheel emission factor.
     * @param emissionFactorWTT The new Well-to-Tank emission factor.
     * @param emissionsPerTKm The new emissions per tonne-kilometer.
     * @return true if the operation was successfully updated, false if validation failed or ID was not found.
     */
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

    /**
     * Updates the attributes of an existing HubOperation identified by its ID.
     * @param elementId The unique identifier of the hub element to edit.
     * @param newEmissionFactor The new emission factor.
     * @param newEnergyConsumption The new energy consumption value.
     * @return true if the hub operation was found and updated, false otherwise.
     */
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

    /**
     * Calculates the total Greenhouse Gas (GEE) emissions for the entire chain.
     * @return The sum of emissions for all operations in the chain, or 0.0 if an error occurs.
     */
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
