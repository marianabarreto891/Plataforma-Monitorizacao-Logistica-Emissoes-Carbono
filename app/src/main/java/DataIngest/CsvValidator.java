package DataIngest;

/**
 * Validates CSV content against specific structural and data integrity requirements.
 */
public class CsvValidator implements ICsvValidator {
    private static final String DELIMITER = ";";
    private static final int MINIMUM_NUMBER_ROWS = 2;
    private static final int MODE_POSITION = 0;
    private static  final int VEHICLE_CATEGORY_POSITION = 1;
    private static final int FUEL_TYPE_POSITION = 2;
    private static final int GEOGRAPHY_POSITION = 3;
    private static final int PAYLOAD_POSITION = 4;
    private static final int EMPTY_KM_POSITION = 5;
    private static final int ACTIVITY_UNIT_POSITION = 6;
    private static final int TOTAL_EMISSIONS_POSITION = 9;
    private static final int YEAR_POSITION = 11;
    private static final String EXPECTED_ACTIVITY_UNIT = "kgCO2e/t¬∑km";


    /**
     * Validates the entire CSV content to ensure it meets formatting and data constraints.
     *
     * @param content The raw CSV string to be validated.
     * @return true if the content is valid, false otherwise.
     */
    @Override
    public boolean validate(String content) {
        if (content == null || content.isEmpty()) {
            return false;
        }

        String[] lines = content.split("\n");

        if (lines.length < MINIMUM_NUMBER_ROWS) {
            return false;
        }

        if (!validateHeader(lines[0])){
            return false;
        }

        for (int i = 1; i < lines.length; i++) {
            String row = lines[i].trim();

            if (row.isEmpty()) {
                continue;
            }

            if (!validateRow(row)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates a single row of data against column count and specific field rules.
     *
     * @param row The single line string representing a CSV row.
     * @return true if the row structure and all its fields are valid.
     */
    private boolean validateRow (String row) {
        String[] columns = row.split(DELIMITER, -1);

        if (columns.length != Header.values().length) {
            return false;
        }

        if (!validateMode(columns[MODE_POSITION])) {
            return false;
        }

        if (!validateVehicleCategory(columns[VEHICLE_CATEGORY_POSITION])) {
            return false;
        }

        if (!validateFuelType(columns[FUEL_TYPE_POSITION])) {
            return false;
        }

        if (!validateGeography(columns[GEOGRAPHY_POSITION])) {
            return false;
        }

        if (!validatePayload(columns[PAYLOAD_POSITION])) {
            return false;
        }

        if (!validateEmptyKmPresent(columns[EMPTY_KM_POSITION])) {
            return false;
        }

        if (!validateActivityUnit(columns[ACTIVITY_UNIT_POSITION])) {
            return false;
        }

        if (!validateTotalEmissions(columns[TOTAL_EMISSIONS_POSITION])) {
            return false;
        }

        if (!validateYear(columns[YEAR_POSITION])) {
            return false;
        }

        return true;
    }

    /**
     * Verifies that the CSV header matches the expected column sequence.
     *
     * @param header The first line of the CSV content.
     * @return true if the header columns match the expected Header enum values.
     */
    private  boolean validateHeader(String header) {
        String[] columns = header.split(DELIMITER, -1);
        Header[] expectedHeaders = Header.values();

        if (columns.length != expectedHeaders.length) {
            return false;
        }

        for (int i = 0; i < expectedHeaders.length; i++) {
            if (!expectedHeaders[i].matches(columns[i].trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the provided mode string matches a valid Mode enum value.
     *
     * @param mode The transport mode string to verify.
     * @return true if the mode is valid.
     */
    private boolean validateMode(String mode) {
        Mode[] modes = Mode.values();

        for (Mode mode_verify : modes) {
            if (mode_verify.matches(mode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the provided fuel type string matches a valid FuelType enum value.
     *
     * @param fuelType The fuel type string to verify.
     * @return true if the fuel type is valid.
     */
    private boolean validateFuelType(String fuelType) {
        FuelType[] fuelTypes = FuelType.values();

        for (FuelType  fuelType_verify : fuelTypes) {
            if (fuelType_verify.matches(fuelType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the provided geography string matches a valid Geography enum value.
     *
     * @param geography The geography string to verify.
     * @return true if the geography is valid.
     */
    private boolean validateGeography(String geography) {
        Geography[] geographies = Geography.values();

        for (Geography  geography_verify : geographies) {
            if (geography_verify.matches(geography)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates that the payload is a positive numeric value.
     *
     * @param payload The payload string to parse.
     * @return true if the payload is a valid positive number.
     */
    private boolean validatePayload(String payload) {
        try {
            double payloadDouble = Double.parseDouble(payload.replace(",", ".").trim());
            return payloadDouble > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that the empty kilometer value is a properly formatted percentage between 0 and 100.
     *
     * @param emptyKm The percentage string (must end with "%").
     * @return true if the format is correct and the value is within range.
     */
    private boolean validateEmptyKmPresent(String emptyKm) {
        if (emptyKm == null || !emptyKm.endsWith("%")) {
            return false;
        }

        try {
            String numberKms = emptyKm.replace("%", "").replace(",", ".").trim();
            double emptyKmDouble = Double.parseDouble(numberKms);
            return emptyKmDouble >= 0 && emptyKmDouble <= 100;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that the total emissions value is a non-negative number.
     *
     * @param totalEmissions The emissions string to parse.
     * @return true if the emissions value is a valid non-negative number.
     */
    private boolean validateTotalEmissions(String totalEmissions) {
        try {
            double emissionsDouble = Double.parseDouble(totalEmissions.replace(",", ".").trim());
            return emissionsDouble >= 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that the year is a numeric integer within the allowed range (2000-2025).
     *
     * @param year The year string to parse.
     * @return true if the year is parsed successfully and is within the valid range.
     */
    private boolean validateYear(String year) {
        try {
           int yearInt = Integer.parseInt(year);
           return yearInt >= 2000 && yearInt <= 2025;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that the vehicle category is provided and is not empty.
     *
     * @param vehicleCategory The vehicle category string to validate.
     * @return true if the category is not null and contains non-whitespace characters.
     */
    private boolean validateVehicleCategory(String vehicleCategory) {
        return vehicleCategory != null && !vehicleCategory.trim().isEmpty();
    }

    /**
     * Validates that the activity unit matches the required specific format exactly.
     *
     * @param activityUnit The activity unit string to verify.
     * @return true if the unit is not null and matches the expected standard unit.
     */
    private boolean validateActivityUnit(String activityUnit) {
        if (activityUnit == null) {
            return false;
        }
        return activityUnit.trim().equals(EXPECTED_ACTIVITY_UNIT);
    }
}
