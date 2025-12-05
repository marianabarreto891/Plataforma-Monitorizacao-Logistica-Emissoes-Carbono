package DataIngest.SecondaryDataSet;

import java.util.List;

/**
 * Validates the fuels CSV.
 *
 * Expected format:
 * fuel_id;fuel_name;unit;ef_ttw;ef_wtt
 *
 * Rules:
 * - Must contain header + at least one data row
 * - Header must match expected columns
 * - Each row must have exactly 5 fields
 * - ef_ttw and ef_wtt must be numeric
 */

public class FuelCsvValidator implements ISecondaryCsvValidator {

    //   fuel_id; fuel_name; unit;ef_ttw; ef_wtt

    @Override
    public boolean validate(List<String[]> rows) {
        if (rows == null || rows.size() < 2) return false;

        String[] header = rows.get(0);

        if (header.length != 5) return false;

        if (!header[0].equalsIgnoreCase("fuel_id")) return false;
        if (!header[1].equalsIgnoreCase("fuel_name")) return false;
        if (!header[2].equalsIgnoreCase("unit")) return false;
        if (!header[3].equalsIgnoreCase("ef_ttw")) return false;
        if (!header[4].equalsIgnoreCase("ef_wtt")) return false;

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            if (row.length != 5) return false;

            try {
                Double.parseDouble(row[3]);
                Double.parseDouble(row[4]);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}

