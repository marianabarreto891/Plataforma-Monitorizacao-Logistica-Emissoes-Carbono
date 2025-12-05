package DataIngest.SecondaryDataSet;

import java.util.List;

/**
 * Interface for validating data loaded from a secondary CSV file.
 */
public interface ISecondaryCsvValidator {

    /**
     * Validates the CSV rows.
     *
     * @param rows list of CSV rows, each already split into columns
     * @return true if the data is valid, false otherwise
     */
    boolean validate(List<String[]> rows);
}
