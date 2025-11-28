package DataIngest;

/**
 * Represents the supported geographic regions for validation.
 */
public enum Geography {
    GLOBAL("global"),
    EU("eu"),
    USA("usa");


    private final String name;

    Geography(String name) {
        this.name = name;
    }

    /**
     * Retrieves the string identifier for the geography.
     *
     * @return the name of the geographic region.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the provided string matches this geography's name.
     *
     * @param geography the string to compare.
     * @return true if the provided string matches the geography name.
     */
    public boolean matches(String geography) {
        return name.equalsIgnoreCase(geography);
    }

    public static Geography fromString(String geography) {
        for (Geography currentGeography : values()) {
            if (currentGeography.matches(geography)) {
                return currentGeography;
            }
        }
        throw new IllegalArgumentException("Invalid geography: " + geography);
    }
}
