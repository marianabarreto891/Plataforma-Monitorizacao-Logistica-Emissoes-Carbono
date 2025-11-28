package DataIngest;
/**
 * Defines the contract for validating CSV data line by line or field by field.
 */
public interface ICsvValidator {

    /**
     * Validates the provided CSV input to ensure it meets specific criteria.
     *
     * @param path The input string representing the CSV content or path to be validated.
     * @return true if the validation is successful, false otherwise.
     */
    boolean validate(String path);
}
