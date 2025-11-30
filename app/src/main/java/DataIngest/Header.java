package DataIngest;

/**
 * Represents the expected CSV header columns for validation.
 */
public enum Header {
    CHAIN_ID("ChainId"),
    ELEMENT_ID("Elementid"),
    MODE("Mode"),
    VEHICLE_CATEGORY("Vehicle_Category"),
    FUEL_TYPE("Fuel_Type"),
    GEOGRAPHY("Geography"),
    MAXIMUM_CAPACITY("MaximumCapacity"),
    PAYLOAD_T("Payload_t"),
    EMPTY_KM_PCT("Empty_km_pct"),
    ACTIVITY_UNIT("Activity_Unit"),
    WTT_EMISSIONS_KG_PER_TKM("WTT_Emissions_kg_per_tkm"),
    TTW_EMISSIONS_KG_PER_TKM("TTW_Emissions_kg_per_tkm"),
    TOTAL_EMISSIONS_KG_PER_TKM("Total_Emissions_kg_per_tkm"),
    YEAR("Year");


    private final String name;

    Header(String name) {
        this.name = name;
    }

    /**
     * Retrieves the string identifier for the header column.
     *
     * @return the name of the header column.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the provided string matches this header's name.
     *
     * @param header the string to compare.
     * @return true if the provided string matches the header name.
     */
    public boolean matches(String header) {
        return name.equalsIgnoreCase(header);
    }
}
