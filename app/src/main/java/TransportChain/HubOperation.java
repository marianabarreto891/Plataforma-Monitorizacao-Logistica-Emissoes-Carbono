package TransportChain;

import DataIngest.FuelType;
import DataIngest.Mode;

public class HubOperation extends TransportChainElement {
    private double energyConsumption;
    private double emissionFactor;
    private Mode mode;
    private String vehicleCategory;
    private FuelType fuelType;


    /**
     * Initializes a new HubOperation with identification, location, and operational data.
     * @param elementId The unique identifier for this hub element.
     * @param chainId The identifier of the transport chain this hub belongs to.
     * @param geography The geographical location of the hub.
     * @param year The year of operation.
     * @param emissionFactor The emission factor associated with the energy source.
     * @param energyConsumption The total energy consumed by the hub.
     * @param mode The transport mode associated with the hub (e.g., specific terminal type).
     * @param vehicleCategory The category of vehicles handled or used at the hub.
     * @param fuelType The type of fuel or energy source used.
     */
    public HubOperation(String elementId, String chainId, String geography, int year, double emissionFactor, double energyConsumption, Mode mode, String vehicleCategory, FuelType fuelType) {
        super(elementId, chainId, geography, year);
        this.energyConsumption = energyConsumption;
        this.emissionFactor = emissionFactor;
        this.mode = mode;
        this.vehicleCategory = vehicleCategory;
        this.fuelType = fuelType;
    }

    /**
     * Retrieves the total energy consumption of the hub operation.
     * @return The energy consumption value.
     */
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * Retrieves the emission factor used for calculation.
     * @return The emission factor value.
     */
    public double getEmissionFactor() {
        return emissionFactor;
    }

    /**
     * Updates the energy consumption value for the hub.
     * @param energyConsumption The new energy consumption value to set.
     */
    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    /**
     * Updates the emission factor for the hub.
     * @param emissionFactor The new emission factor value to set.
     */
    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    /**
     * Retrieves the unique element ID from the superclass.
     * @return The unique identifier string of the element.
     */
    @Override
    public String getElementId() {
        return super.getElementId();
    }
}