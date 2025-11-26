package DataIngest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CsvValidatorTest {
    private final String HEADER = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;Data_Source;Year;Notes";
    private static final String VALID_UNIT = "kgCO2e/t¬∑km";
    private final String VALID_ROW = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
    private CsvValidator validator;


    @BeforeEach
    void setup() {
        validator = new CsvValidator();
    }


    @Test
    public void testValidRow() {
        String csv = HEADER + "\n" + VALID_ROW;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidRow() {
        String invalidRow = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testValidNumberOfColumns() {
        String csv = HEADER + "\n" + VALID_ROW;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidNumberOfColumns() {
        String csv = "Mode;Vehicle_Category;Fuel_Type;Geography;Payload_t;Empty_km_pct;Activity_Unit;\n" + " ";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidDelimiter() {
        String invalidRow = "road,Heavy Duty Truck,diesel,Global,8,0%," + VALID_UNIT + ",0,0,0.136,GLEC v3 Annex 1,2025,Ok";
        String csv = HEADER.replace(";", ",") + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidYearFormat() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2ZX5;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidMode() {
        String invalidRow = ";Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testEmptyVehicleCategory() {
        String invalidRow = "road;;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidTotalEmissions() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;-0.55;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidEmptyKmPctFormat() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;172%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidPayload() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;-5;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidActivityUnit() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;kg;0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    //BVA

    @Test
    public void testTotalEmissionsZero() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.0;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionLowerLimitInvalid() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;-0.0001;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionsUpperLimitValid() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.0001;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZero() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctHundred() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;8;100%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZLowerLimitInvalid() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;-1%;" + VALID_UNIT + ";0;0;0.0;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZUpperLimitInvalid() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;101%;" + VALID_UNIT + ";0;0;0.0;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctLoweLimitValid() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;8;1%;" + VALID_UNIT + ";0;0;0.0;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctUpperLimitValid() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;8;99%;" + VALID_UNIT + ";0;0;0.0;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testPayloadZero() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;0;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadLowerLimitInvalid() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;-0.0001;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadUpperLimitValid() {
        String validRow = "road;Heavy Duty Truck;diesel;Global;0.0001;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;2025;Ok";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidYear() {
        String invalidRow = "road;Heavy Duty Truck;diesel;Global;8;0%;" + VALID_UNIT + ";0;0;0.136;GLEC v3 Annex 1;3000;Ok";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }
}
