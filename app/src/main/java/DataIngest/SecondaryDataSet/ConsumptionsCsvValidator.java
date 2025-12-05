package DataIngest.SecondaryDataSet;

import java.util.List;

/**
 * Validates the transport consumptions CSV.
 *
 * Expected format (6 columns):
 * mode;vehicle_category;fuel_id;consumption_unit;average_consumption;capacity_tons
 *
 * Rules:
 * - Must contain at least header + one data row
 * - Header must match the expected column names
 * - Each row must have exactly 6 fields
 * - Columns 5 and 6 must be numeric
 */

public class  ConsumptionsCsvValidator implements ISecondaryCsvValidator {

    // mode;vehicle_category;fuel_id;consumption_unit;average_consumption;capacity_tons

    @Override
    public boolean validate(List<String[]> rows) {
        if (rows == null || rows.size() < 2) return false;

        String[] header = rows.get(0);

        if (header.length != 6) return false;

        if (!header[0].equalsIgnoreCase("mode")) return false;
        if (!header[1].equalsIgnoreCase("vehicle_category")) return false;
        if (!header[2].equalsIgnoreCase("fuel_id")) return false;
        if (!header[3].equalsIgnoreCase("consumption_unit")) return false;
        if (!header[4].equalsIgnoreCase("average_consumption")) return false;
        if (!header[5].equalsIgnoreCase("capacity_tons")) return false;

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            if (row.length != 6) return false;

            try {
                Double.parseDouble(row[4].trim().replace(",", "."));
                Double.parseDouble(row[5].trim().replace(",", "."));
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }
}
