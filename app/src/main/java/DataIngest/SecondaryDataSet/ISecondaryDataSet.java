package DataIngest.SecondaryDataSet;

/**
 * Provides read-only access to secondary dataset values
 * loaded from CSV files (fuels, consumptions, operational factors, hubs).
 *
 * Methods return parsed numerical values
 */
public interface ISecondaryDataSet {

        // Fuels
        Double getEfWtt(String fuelId);
        Double getEfTtw(String fuelId);
        String getFuelUnit(String fuelId);

        // Vehicle Energy Consumptions
        Double getAverageConsumption(String mode, String vehicleCategory);
        Double getCapacityTon(String mode, String vehicleCategory);

        // Operational Factors
        Double getLoadFactor(String mode, String vehicleCategory);
        Double getEmptyReturnPct(String mode, String vehicleCategory);

        // Hubs
        Double getHubConsumptionPerTon(String hubType);
    }

