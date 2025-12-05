package DataIngest.SecondaryDataSet;

import java.util.List;

/**
 * Validates the operational factors CSV.
 *
 * Expected format:
 * mode;vehicle_category;default_load_factor;default_empty_return
 *
 * Rules:
 * - Must contain header + at least one data row
 * - Header must match expected columns
 * - Each row must have exactly 4 fields
 * - default_load_factor and default_empty_return must be numeric
 */
public class OperationalFactorsCsvValidator implements ISecondaryCsvValidator {

    // mode;vehicle_category;default_load_factor;default_empty_return

    @Override
    public boolean validate(List<String[]> rows) {
        if (rows == null || rows.size() < 2) return false;

        String[] header = rows.get(0);

        if (header.length != 4) return false;

        if (!header[0].equalsIgnoreCase("mode")) return false;
        if (!header[1].equalsIgnoreCase("vehicle_category")) return false;
        if (!header[2].equalsIgnoreCase("default_load_factor")) return false;
        if (!header[3].equalsIgnoreCase("default_empty_return")) return false;

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            if (row.length != 4) return false;

            try {
                Double.parseDouble(row[2].trim().replace(",", "."));
                Double.parseDouble(row[3].trim().replace(",", "."));
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }
}
