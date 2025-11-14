package org.example;

public enum Header {

    MODE("Mode"),
    VEHICLE_CATEGORY("Vehicle_Category"),
    FUEL_TYPE("Fuel_Type"),
    GEOGRAPHY("Geography"),
    PAYLOAD_T("Payload_t"),
    EMPTY_KM_PCT("Empty_km_pct"),
    ACTIVITY_UNIT("Activity_Unit"),
    WTT_EMISSIONS_KG_PER_TKM("WTT_Emissions_kg_per_tkm"),
    TTW_EMISSIONS_KG_PER_TKM("TTW_Emissions_kg_per_tkm"),
    TOTAL_EMISSIONS_KG_PER_TKM("Total_Emissions_kg_per_tkm"),
    DATA_SOURCE("Data_Source"),
    YEAR("Year"),
    NOTES("Notes");


    private final String name;

    Header(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches (String header) {
        return name.equals(header);
    }
}
