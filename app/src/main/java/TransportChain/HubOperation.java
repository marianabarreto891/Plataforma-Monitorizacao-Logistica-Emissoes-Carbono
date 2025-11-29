package TransportChain;

public class HubOperation extends TransportChainElement {
    private double energyConsumption;
    private double emissionFactor;

    public HubOperation(String elementId, String chainId, String geography, int year, double emissionFactor, double energyConsumption) {
        super(elementId, chainId, geography, year);
        this.energyConsumption = energyConsumption;
        this.emissionFactor = emissionFactor;
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