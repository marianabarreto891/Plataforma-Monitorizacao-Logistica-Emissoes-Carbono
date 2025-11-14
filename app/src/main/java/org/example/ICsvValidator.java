package org.example;
/**
 * Defines the contract for validating CSV data line by line or field by field.
 */
public interface ICsvValidator {

    boolean Validate(String path);
}
