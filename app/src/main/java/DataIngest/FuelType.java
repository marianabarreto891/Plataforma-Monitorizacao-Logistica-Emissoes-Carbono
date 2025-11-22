package org.example;

/**
 * Represents the supported fuel types used for validation.
 */
public enum FuelType {
    DIESEL("diesel"),
    GASOLINE("gasoline");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }

    /**
     * Retrieves the string identifier for the fuel type.
     *
     * @return the name of the fuel type.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the provided string matches this fuel type's name.
     *
     * @param fuelType the string to compare.
     * @return true if the provided string matches the fuel type name.
     */
    public boolean matches(String fuelType) {
        return name.equalsIgnoreCase(fuelType);
    }
}
