package DataIngest.SecondaryDataSet;

import DataIngest.SecondaryDataSet.SecondaryLoader.*;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SecondaryDataSet
 *
 * This class loads and stores all secondary dataset information used by the system.
 * It automatically loads four CSV files from the classpath:
 *
 *  - fuels.csv
 *  - transport_consumptions.csv
 *  - operational_factors.csv
 *  - hubs.csv
 *
 * Each CSV file is validated before loading. If a CSV is missing or invalid,
 * an exception is thrown.
 *
 * The class provides access to:
 *  - Fuel emission factors (WTT and TTW)
 *  - Fuel units
 *  - Average transport consumption per transport mode and vehicle category
 *  - Vehicle capacity in tons
 *  - Operational factors (load factor and empty return percentage)
 *  - Hub energy consumption per ton
 *
 * All lookups are case-insensitive.
 */
public class SecondaryDataSet implements ISecondaryDataSet {

    // Fuels
    private Map<String, Double> efWtt = new HashMap<>();
    private Map<String, Double> efTtw = new HashMap<>();
    private Map<String, String> fuelUnits = new HashMap<>();

    // Transport Consumptions
    private Map<String, Double> mediumConsumption = new HashMap<>();
    private Map<String, Double> capacityTon = new HashMap<>();

    // Operational Factors
    private Map<String, Double> loadFactor = new HashMap<>();
    private Map<String, Double> emptyReturnPct = new HashMap<>();

    // Hubs
    private Map<String, Double> hubConsumption = new HashMap<>();

    /**
     * Constructor automatically loads all CSV files and validates their content.
     */
    public SecondaryDataSet() {
        loadFuels(loadCsv("fuels.csv"));
        loadConsumptions(loadCsv("transport_consumptions.csv"));
        loadOperationalFactors(loadCsv("operational_factors.csv"));
        loadHubs(loadCsv("hubs.csv"));
    }

    /**
     * Loads a CSV file from the classpath.
     * @param fileName CSV file name
     * @return List of rows (each row as a String[])
     * @throws IllegalArgumentException if the file is not found
     */
    private List<String[]> loadCsv(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

        if (is == null) {
            throw new IllegalArgumentException("CSV resource not found in classpath: " + fileName);
        }

        CsvLoader loader = new CsvLoader();

        try {
            return loader.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + fileName, e);
        }
    }

    /**
     * Loads and stores all fuel-related data after validating the CSV.
     * Expected columns:
     *   fuel_id; fuel_name; unit; ef_ttw; ef_wtt
     */
    private void loadFuels(List<String[]> rows) {

        FuelCsvValidator validator = new FuelCsvValidator();

        if (!validator.validate(rows)) {
            throw new IllegalArgumentException("Invalid fuel CSV dataset.");
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            String id = row[0].trim().toLowerCase();
            String unit = row[2].trim();

            double ttw = Double.parseDouble(row[3]);
            double wtt = Double.parseDouble(row[4]);

            fuelUnits.put(id, unit);
            efTtw.put(id, ttw);
            efWtt.put(id, wtt);
        }
    }

    /**
     * Loads transport consumption data.
     * Expected columns:
     *   mode; vehicle_category; fuel_id; consumption_unit; average_consumption; capacity_tons
     *
     * Uses key format: mode_vehicleCategory (all lowercase)
     */
    private void loadConsumptions(List<String[]> rows) {

        ConsumptionsCsvValidator validator = new ConsumptionsCsvValidator();
        if (!validator.validate(rows)) {
            throw new IllegalArgumentException("Invalid transport consumption CSV dataset.");
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            String mode = row[0].trim().toLowerCase();
            String vehicle = row[1].trim().toLowerCase();

            String key = mode + "_" + vehicle;

            double averageConsumption = Double.parseDouble(row[4]);
            double capacity = Double.parseDouble(row[5]);

            mediumConsumption.put(key, averageConsumption);
            capacityTon.put(key, capacity);
        }
    }

    /**
     * Loads operational factors such as load factor and empty return percentage.
     * Expected columns:
     *   mode; vehicle_category; load_factor; empty_return_pct
     *
     * Uses key format: mode_vehicleCategory
     */
    private void loadOperationalFactors(List<String[]> rows) {

        OperationalFactorsCsvValidator validator = new OperationalFactorsCsvValidator();
        if (!validator.validate(rows)) {
            throw new IllegalArgumentException("Invalid operational factors CSV dataset.");
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            String mode = row[0].trim().toLowerCase();
            String vehicle = row[1].trim().toLowerCase();

            String key = mode + "_" + vehicle;

            double load = Double.parseDouble(row[2]);
            double empty = Double.parseDouble(row[3]);

            loadFactor.put(key, load);
            emptyReturnPct.put(key, empty);
        }
    }

    /**
     * Loads hub energy consumption per ton.
     * Expected columns:
     *   hub_type; ... ; ... ; consumption
     */
    private void loadHubs(List<String[]> rows) {

        HubsCsvValidator validator = new HubsCsvValidator();
        if (!validator.validate(rows)) {
            throw new IllegalArgumentException("Invalid hubs CSV dataset.");
        }

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            String hubType = row[0].trim().toLowerCase();
            double consumption = Double.parseDouble(row[3]);

            hubConsumption.put(hubType, consumption);
        }
    }

    /** Returns the WTT emission factor for a given fuel type (case-insensitive). */
    @Override
    public Double getEfWtt(String fuelType) {
        return efWtt.get(fuelType.toLowerCase());
    }

    /** Returns the TTW emission factor for a given fuel type (case-insensitive). */
    @Override
    public Double getEfTtw(String fuelType) {
        return efTtw.get(fuelType.toLowerCase());
    }

    /** Returns the unit associated with the fuel (e.g., L, kg). */
    @Override
    public String getFuelUnit(String fuelType) {
        return fuelUnits.get(fuelType.toLowerCase());
    }

    /** Returns average fuel consumption for the given mode and vehicle category. */
    @Override
    public Double getAverageConsumption(String mode, String vehicleCategory) {
        return mediumConsumption.get((mode + "_" + vehicleCategory).toLowerCase());
    }

    /** Returns capacity in tons for the specified mode and vehicle category. */
    @Override
    public Double getCapacityTon(String mode, String vehicleCategory) {
        return capacityTon.get((mode + "_" + vehicleCategory).toLowerCase());
    }

    /** Returns load factor for the given mode + vehicle combination. */
    @Override
    public Double getLoadFactor(String mode, String vehicleCategory) {
        return loadFactor.get((mode + "_" + vehicleCategory).toLowerCase());
    }

    /** Returns empty return percentage for the given mode + vehicle combination. */
    @Override
    public Double getEmptyReturnPct(String mode, String vehicleCategory) {
        return emptyReturnPct.get((mode + "_" + vehicleCategory).toLowerCase());
    }

    /** Returns hub energy consumption per ton for the given hub type. */
    @Override
    public Double getHubConsumptionPerTon(String hubType) {
        return hubConsumption.get(hubType.toLowerCase());
    }
}