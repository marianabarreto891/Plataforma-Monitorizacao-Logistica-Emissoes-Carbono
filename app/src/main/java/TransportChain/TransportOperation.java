package TransportChain;

import DataIngest.Mode;

public class TransportOperation extends TransportChainElement {
    private Mode mode;
    private String vehicleCategory;
    private double distanceKm;
    private double totalLoadCapacity;
    private double loadFactor;
    private double emptyReturn;
    private double averageEnergyConsumption;
    private double emissionFactorTTW;
    private double emissionFactorWTT;
    private double emissionsPerTKm;

    public TransportOperation(String elementId, String chainId, String geography, int year,
                              Mode mode, String vehicleCategory,
                              double distanceKm, double totalLoadCapacity, double loadFactor,
                              double emptyReturn, double averageEnergyConsumption,
                              double efTTW, double efWTT, double emissionsPerTKm) {

        super(elementId, chainId, geography, year);

        this.mode = mode;
        this.vehicleCategory = vehicleCategory;
        this.distanceKm = distanceKm;
        this.totalLoadCapacity = totalLoadCapacity;
        this.loadFactor = loadFactor;
        this.emptyReturn = emptyReturn;
        this.averageEnergyConsumption = averageEnergyConsumption;
        this.emissionFactorTTW = efTTW;
        this.emissionFactorWTT = efWTT;
        this.emissionsPerTKm = emissionsPerTKm;
    }

    public void setMode(Mode mode) { this.mode = mode; }

    public void setVehicleCategory(String vehicleCategory) { this.vehicleCategory = vehicleCategory; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public double getTotalLoadCapacity() { return totalLoadCapacity; }
    public void setTotalLoadCapacity(double totalLoadCapacity) { this.totalLoadCapacity = totalLoadCapacity; }

    public double getLoadFactor() { return loadFactor; }
    public void setLoadFactor(double loadFactor) { this.loadFactor = loadFactor; }

    public double getEmptyReturn() { return emptyReturn; }
    public void setEmptyReturn(double emptyReturn) { this.emptyReturn = emptyReturn; }

    public double getAverageEnergyConsumption() { return averageEnergyConsumption; }
    public void setAverageEnergyConsumption(double averageEnergyConsumption) { this.averageEnergyConsumption = averageEnergyConsumption; }

    public double getEmissionFactorTTW() { return emissionFactorTTW; }
    public void setEmissionFactorTTW(double emissionFactorTTW) { this.emissionFactorTTW = emissionFactorTTW; }

    public double getEmissionFactorWTT() { return emissionFactorWTT; }
    public void setEmissionFactorWTT(double emissionFactorWTT) { this.emissionFactorWTT = emissionFactorWTT; }

    public double getEmissionsPerTKm() { return emissionsPerTKm; }
    public void setEmissionsPerTKm(double emissionsPerTKm) { this.emissionsPerTKm = emissionsPerTKm; }
}