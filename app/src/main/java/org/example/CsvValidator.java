package org.example;

public class CsvValidator implements ICsvValidator {
    private static final String DELIMITER = ";";
    private static final int MINIMUM_NUMBER_ROWS = 2;
    private static final int MODE_POSITION = 0;
    private static final int FUEL_TYPE_POSITION = 2;
    private static final int GEOGRAPHY_POSITION = 3;



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

            if (!validateRow(row)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateRow (String row) {
        String[] columns = row.split(DELIMITER, -1);

        if (columns.length != Header.values().length) {
            return false;
        }

        if (!validateMode(columns[MODE_POSITION])) {
            return false;
        }

        if (!validateFuelType(columns[FUEL_TYPE_POSITION])) {
            return false;
        }

        if (!validateGeography(columns[GEOGRAPHY_POSITION])) {
            return false;
        }
        return true;
    }

    private  boolean validateHeader(String header) {
        String[] columns = header.split(DELIMITER);
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

    private boolean validateMode(String mode) {
        Mode[] modes = Mode.values();

        for (Mode mode_verify : modes) {
            if (mode_verify.matches(mode)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateFuelType(String fuelType) {
        FuelType[] fuelTypes = FuelType.values();

        for (FuelType  fuelType_verify : fuelTypes) {
            if (fuelType_verify.matches(fuelType)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateGeography(String geography) {
        Geography[] geographies = Geography.values();

        for (Geography  geography_verify : geographies) {
            if (geography_verify.matches(geography)) {
                return true;
            }
        }
        return false;
    }
}
