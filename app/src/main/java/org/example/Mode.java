package org.example;

/**
 * Represents the supported transport modes for validation.
 */
public enum Mode {
    ROAD("road"),
    AIR("air"),
    SEE("see");

    private final String name;

    Mode(String name) {
        this.name = name;
    }

    /**
     * Retrieves the string identifier for the transport mode.
     *
     * @return the name of the mode.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the provided string matches this mode's name.
     *
     * @param mode the string to compare.
     * @return true if the provided string matches the mode name.
     */
    public boolean matches(String mode) {
        return name.equals(mode);
    }
}
