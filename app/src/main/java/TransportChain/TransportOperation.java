package TransportChain;

import DataIngest.FuelType;
import DataIngest.Mode;

public class TransportOperation extends TransportChainElement {
    private Mode mode;
    private String vehicleCategory;
    private double distanceKm;
    private FuelType fuelType;
    private double totalLoadCapacity;
    private double loadFactor;
    private double emptyReturn;
    private double averageEnergyConsumption;
    private double emissionFactorTTW;
    private double emissionFactorWTT;
    private double emissionsPerTKm;

    /**
     * Initializes a new TransportOperation with specific vehicle, fuel, and operational data.
     * @param elementId The unique identifier of the element.
     * @param chainId The identifier of the transport chain.
     * @param geography The geographical location or scope.
     * @param year The year of the operation.
     * @param mode The transport mode (e.g., Road, Rail).
     * @param vehicleCategory The category of the vehicle used.
     * @param distanceKm The total distance traveled in kilometers.
     * @param fuelType The type of fuel used.
     * @param totalLoadCapacity The maximum load capacity of the vehicle.
     * @param loadFactor The actual payload transported.
     * @param emptyReturn The percentage of empty return distance.
     * @param averageEnergyConsumption The average energy consumption per unit.
     * @param efTTW The Tank-to-Wheel emission factor.
     * @param efWTT The Well-to-Tank emission factor.
     * @param emissionsPerTKm The total emissions per tonne-kilometer (pre-calculated or reference).
     */
    public TransportOperation(String elementId, String chainId, String geography, int year,
                              Mode mode, String vehicleCategory,
                              double distanceKm, FuelType fuelType, double totalLoadCapacity, double loadFactor,
                              double emptyReturn, double averageEnergyConsumption,
                              double efTTW, double efWTT, double emissionsPerTKm) {

        super(elementId, chainId, geography, year);

        this.mode = mode;
        this.vehicleCategory = vehicleCategory;
        this.distanceKm = distanceKm;
        this.fuelType = fuelType;
        this.totalLoadCapacity = totalLoadCapacity;
        this.loadFactor = loadFactor;
        this.emptyReturn = emptyReturn;
        this.averageEnergyConsumption = averageEnergyConsumption;
        this.emissionFactorTTW = efTTW;
        this.emissionFactorWTT = efWTT;
        this.emissionsPerTKm = emissionsPerTKm;
    }

    /**
     * Updates the transport mode for this operation.
     * @param mode The new transport mode to set.
     */
    public void setMode(Mode mode) { this.mode = mode; }

    /**
     * Updates the vehicle category string.
     * @param vehicleCategory The new vehicle category to set.
     */
    public void setVehicleCategory(String vehicleCategory) { this.vehicleCategory = vehicleCategory; }

    /**
     * Retrieves the total distance traveled.
     * @return The distance in kilometers.
     */
    public double getDistanceKm() { return distanceKm; }

    /**
     * Updates the travel distance in kilometers.
     * @param distanceKm The new distance value to set.
     */
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    /**
     * Retrieves the maximum load capacity of the vehicle.
     * @return The total load capacity value.
     */
    public double getTotalLoadCapacity() { return totalLoadCapacity; }

    /**
     * Updates the maximum load capacity of the vehicle.
     * @param totalLoadCapacity The new capacity value to set.
     */
    public void setTotalLoadCapacity(double totalLoadCapacity) { this.totalLoadCapacity = totalLoadCapacity; }

    /**
     * Retrieves the actual payload transported (load factor).
     * @return The load factor value.
     */
    public double getLoadFactor() { return loadFactor; }

    /**
     * Updates the actual payload transported.
     * @param loadFactor The new load factor value to set.
     */
    public void setLoadFactor(double loadFactor) { this.loadFactor = loadFactor; }

    /**
     * Retrieves the percentage of empty return distance.
     * @return The empty return percentage.
     */
    public double getEmptyReturn() { return emptyReturn; }

    /**
     * Updates the percentage of empty return distance.
     * @param emptyReturn The new empty return percentage to set.
     */
    public void setEmptyReturn(double emptyReturn) { this.emptyReturn = emptyReturn; }

    /**
     * Retrieves the average energy consumption for the vehicle.
     * @return The average energy consumption value.
     */
    public double getAverageEnergyConsumption() { return averageEnergyConsumption; }

    /**
     * Updates the average energy consumption.
     * @param averageEnergyConsumption The new energy consumption value to set.
     */
    public void setAverageEnergyConsumption(double averageEnergyConsumption) { this.averageEnergyConsumption = averageEnergyConsumption; }

    /**
     * Retrieves the Tank-to-Wheel emission factor.
     * @return The TTW emission factor.
     */
    public double getEmissionFactorTTW() { return emissionFactorTTW; }

    /**
     * Updates the Tank-to-Wheel emission factor.
     * @param emissionFactorTTW The new TTW factor to set.
     */
    public void setEmissionFactorTTW(double emissionFactorTTW) { this.emissionFactorTTW = emissionFactorTTW; }

    /**
     * Retrieves the Well-to-Tank emission factor.
     * @return The WTT emission factor.
     */
    public double getEmissionFactorWTT() { return emissionFactorWTT; }

    /**
     * Updates the Well-to-Tank emission factor.
     * @param emissionFactorWTT The new WTT factor to set.
     */
    public void setEmissionFactorWTT(double emissionFactorWTT) { this.emissionFactorWTT = emissionFactorWTT; }

    /**
     * Retrieves the total emissions per tonne-kilometer.
     * @return The emissions per tkm value.
     */
    public double getEmissionsPerTKm() { return emissionsPerTKm; }

    /**
     * Updates the total emissions per tonne-kilometer.
     * @param emissionsPerTKm The new emissions per tkm value to set.
     */
    public void setEmissionsPerTKm(double emissionsPerTKm) { this.emissionsPerTKm = emissionsPerTKm; }
}