package DataIngest.SecondaryDataSet;

import java.util.List;

/**
 * Validates the hubs CSV.
 *
 * Expected format:
 * hub_type;energy_id;unit;consumption_per_ton
 *
 * Rules:
 * - Must contain header + at least one data row
 * - Header must match expected columns
 * - Each row must have exactly 4 fields
 * - consumption_per_ton must be numeric
 */
public class HubsCsvValidator implements ISecondaryCsvValidator {

    // hub_type; energy_id; unit; consumption_per_ton

    @Override
    public boolean validate(List<String[]> rows) {
        if (rows == null || rows.size() < 2) return false;

        String[] header = rows.get(0);

        if (header.length != 4) return false;

        if (!header[0].equalsIgnoreCase("hub_type")) return false;
        if (!header[1].equalsIgnoreCase("energy_id")) return false;
        if (!header[2].equalsIgnoreCase("unit")) return false;
        if (!header[3].equalsIgnoreCase("consumption_per_ton")) return false;

        for (int i = 1; i < rows.size(); i++) {
            try {
                Double.parseDouble(rows.get(i)[3]);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
