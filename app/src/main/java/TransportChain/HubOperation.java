package TransportChain;

import DataIngest.FuelType;
import DataIngest.Mode;

public class HubOperation extends TransportChainElement {
    private double energyConsumption;
    private double emissionFactor;
    private Mode mode;
    private String vehicleCategory;
    private FuelType fuelType;

    public HubOperation(String elementId, String chainId, String geography, int year, double emissionFactor, double energyConsumption, Mode mode, String vehicleCategory, FuelType fuelType) {
        super(elementId, chainId, geography, year);
        this.energyConsumption = energyConsumption;
        this.emissionFactor = emissionFactor;
        this.mode = mode;
        this.vehicleCategory = vehicleCategory;
        this.fuelType = fuelType;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    @Override
    public String getElementId() {
        return super.getElementId();
    }
}