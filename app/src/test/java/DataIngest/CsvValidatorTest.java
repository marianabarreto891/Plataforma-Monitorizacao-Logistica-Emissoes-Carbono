package DataIngest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CsvValidatorTest {
    private CsvValidator validator;

    @BeforeEach
    void setup() {
        validator = new CsvValidator();
    }

    @Test
    public void testValidNumberOfColumns() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidNumberOfColumns() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;\n" + " ";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidDelimiter() {
        String csv = "Mode,Vehicle_Category,Fuel_Type,Geography,Payload_t,Empty_km_pct,Activity_Unit,WTT_Emissions_kg_per_tkm,TTW_Emissions_kg_per_tkm,Total_Emissions_kg_per_tkm,Data_Source,Year,Notes\n" + "road,Heavy Duty Truck,diesel,Global,8,0%,kgCO2e/tkm,0,0,0.136,GLEC v3 Annex 1,2025,Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidYearFormat() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2ZX5;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidMode() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + ";Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidTotalEmissions() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;-0.55;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidEmptyKmPctFormat() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;172%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidPayload() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;-5;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    //BVA

    @Test
    public void testTotalEmissionsZero() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.0;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionLowerLimitInvalid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;-0.0001;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionsUpperLimitValid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.0001;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZero() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctHundred() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;100%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZLowerLimitInvalid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;-1%;kgCO2e/tkm;0;0;0.0;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZUpperLimitInvalid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;101%;kgCO2e/tkm;0;0;0.0;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctLoweLimitValid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;1%;kgCO2e/tkm;0;0;0.0;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctUpperLimitValid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;99%;kgCO2e/tkm;0;0;0.0;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testPayloadZero() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;0;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadLowerLimitInvalid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;-0.0001;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadUpperLimitValid() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;0.0001;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;2025;Ok";

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidYear(){
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes\n" + "road;Heavy Duty Truck;diesel;Global;8;0%;kgCO2e/tkm;0;0;0.136;GLEC v3 Annex 1;3000;Ok";

        assertFalse(validator.validate(csv));
    }
}
