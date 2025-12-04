
package DataIngest;

import java.util.ArrayList;
import java.util.List;

import TransportChain.HubOperation;
import TransportChain.TransportChain;
import TransportChain.TransportChainElement;
import TransportChain.TransportOperation;

public class TransportDataParser implements ICsvParser {

    /**
     * Converts a list of raw CSV data rows into a structured TransportChain object.
     * @param data The list of string arrays representing the CSV rows.
     * @return A TransportChain object containing all successfully parsed elements.
     */
    @Override
    public TransportChain parseToChain(List<String[]> data) {

        TransportChain chain = new TransportChain(new ArrayList<>());

        for (String[] row : data) {
            TransportChainElement element = parseRow(row);

            if (element != null) {
                chain.addElement(element);
            }
        }

        return chain;
    }

    /**
     * Parses a single row of data into a concrete TransportChainElement based on distance logic.
     * @param row The array of strings representing a single CSV row.
     * @return A TransportChainElement (either TransportOperation or HubOperation).
     */
    private TransportChainElement parseRow(String[] row) {
        String chainId = extractString(row, Header.CHAIN_ID);
        String elementId = extractString(row, Header.ELEMENT_ID);
        String vehicleCategory = extractString(row, Header.VEHICLE_CATEGORY);
        String geography = extractString(row, Header.GEOGRAPHY);

        Mode mode = null;
        try {
            String modeStr = extractString(row, Header.MODE);
            if (!modeStr.isEmpty()) {
                mode = Mode.fromString(modeStr);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Warning: Invalid Mode ignored: " + e.getMessage());
        } catch (Exception e) {
            
        }

        FuelType fuelType = null;
        try {
            String fuelStr = extractString(row, Header.FUEL_TYPE);
            if (!fuelStr.isEmpty()) {
                fuelType = FuelType.fromString(fuelStr);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
        
        } catch (Exception e) {
            
        }

        double totalLoadCapacity = extractDouble(row, Header.MAXIMUM_CAPACITY);
        double loadFactor = extractDouble(row, Header.PAYLOAD_T);
        double distanceKm = extractDouble(row, Header.TOTAL_DISTANCE);
        double emptyReturn = extractDouble(row, Header.EMPTY_KM_PCT);
        double efTTW = extractDouble(row, Header.TTW_EMISSIONS_KG_PER_TKM);
        double efWTT = extractDouble(row, Header.WTT_EMISSIONS_KG_PER_TKM);
        double emissionFactor = extractDouble(row, Header.EMISSION_FACTOR);
        double emissionsPerTKm = extractDouble(row, Header.TOTAL_EMISSIONS_KG_PER_TKM);
        double energyConsumption = extractDouble(row, Header.ENERGY_CONSUMPTION);
        double averageEnergyConsumption = extractDouble(row, Header.AVERAGE_CONSUMPTION);
        int year = extractInt(row, Header.YEAR);



        if (distanceKm > 0) {
            return createTransportOperation(
                    elementId,
                    chainId,
                    geography,
                    year,
                    mode,
                    vehicleCategory,
                    distanceKm,
                    fuelType,
                    totalLoadCapacity,
                    loadFactor,
                    emptyReturn,
                    averageEnergyConsumption,
                    efTTW,
                    efWTT,
                    emissionsPerTKm);
        } else {
            return createHubOperation(
                    elementId,
                    chainId,
                    geography,
                    year,
                    emissionFactor,
                    energyConsumption,
                    mode,            
                    vehicleCategory, 
                    fuelType         
            );
        }
    }

    /**
     * Creates a new TransportOperation instance with all required attributes.
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
     * @param emissionsPerTKm The total emissions per tonne-kilometer.
     * @return A fully initialized TransportOperation object.
     */
    private TransportOperation createTransportOperation(
            String elementId,
            String chainId,
            String geography,
            int year,
            Mode mode,
            String vehicleCategory,
            double distanceKm,
            FuelType fuelType, 
            double totalLoadCapacity,
            double loadFactor,
            double emptyReturn,
            double averageEnergyConsumption,
            double efTTW,
            double efWTT,
            double emissionsPerTKm) {

        return new TransportOperation(
                elementId,
                chainId,
                geography,
                year,
                mode,
                vehicleCategory,
                distanceKm,
                fuelType, 
                totalLoadCapacity,
                loadFactor,
                emptyReturn,
                averageEnergyConsumption,
                efTTW,
                efWTT,
                emissionsPerTKm);
    }

    /**
     * Creates a new HubOperation instance with all required attributes.
     * @param elementId The unique identifier of the element.
     * @param chainId The identifier of the transport chain.
     * @param geography The geographical location.
     * @param year The year of the operation.
     * @param emissionFactor The static emission factor for the hub.
     * @param energyConsumption The total energy consumption of the hub.
     * @param mode The associated transport mode (can be null if not applicable).
     * @param vehicleCategory The associated vehicle category (can be empty).
     * @param fuelType The associated fuel type.
     * @return A fully initialized HubOperation object.
     */
    private HubOperation createHubOperation(
            String elementId, 
            String chainId, 
            String geography, 
            int year,
            double emissionFactor, 
            double energyConsumption,
            Mode mode,              
            String vehicleCategory,  
            FuelType fuelType      
            ) {
        
        return new HubOperation(
                elementId, 
                chainId, 
                geography, 
                year, 
                emissionFactor, 
                energyConsumption,
                mode,            
                vehicleCategory, 
                fuelType         
        );
    }

    /**
     * Extracts and parses a double value from the specified column in the row.
     * @param row The array of strings representing the data row.
     * @param column The specific header enum indicating the column index.
     * @return The parsed double value, or 0.0 if extraction fails.
     */
    private double extractDouble(String[] row, Header column) {

        int index = column.ordinal();

        try {
            if (index >= 0 && index < row.length) {
                String value = row[index];
                if (value != null && !value.trim().isEmpty()) {
                    return Double.parseDouble(value.replace(",", "."));
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter para Double na coluna " + column + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro de extração na coluna " + column + ": " + e.getMessage());
        }

        return 0.0;
    }

    
    /**
     * Extracts a trimmed string value from the specified column in the row.
     * @param row The array of strings representing the data row.
     * @param column The specific header enum indicating the column index.
     * @return The extracted string, or an empty string if extraction fails.
     */
    private String extractString(String[] row, Header column) {
        int index = column.ordinal();

        try {
            if (index >= 0 && index < row.length) {
                String value = row[index];
                return (value != null) ? value.trim() : "";
            }
        } catch (Exception e) {
            System.err.println("Erro de extração de String na coluna " + column + ": " + e.getMessage());
        }

        return "";
    }


    /**
     * Extracts and parses an integer value from the specified column in the row.
     * @param row The array of strings representing the data row.
     * @param column The specific header enum indicating the column index.
     * @return The parsed integer value, or 0 if extraction fails.
     */
    private int extractInt(String[] row, Header column) {

        int index = column.ordinal();

        try {
            if (index >= 0 && index < row.length) {

                String value = row[index];

                if (value != null && !value.trim().isEmpty()) {
                    value = value.trim();
                    return Integer.parseInt(value);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter para int na coluna " + column + ": " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado na coluna " + column + ": " + e.getMessage());
        }

        return 0;
    }

}
