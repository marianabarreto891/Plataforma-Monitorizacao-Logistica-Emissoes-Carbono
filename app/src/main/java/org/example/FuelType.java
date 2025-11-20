package org.example;

public enum FuelType {

    DIESEL("diesel"),
    GASOLINE("gasoline");

    private final String name;

    FuelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches (String fuelType) {
        return name.equals(fuelType);
    }
}
