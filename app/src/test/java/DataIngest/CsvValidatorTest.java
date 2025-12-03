package DataIngest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CsvValidatorTest {
    private final String HEADER = "ChainId;Elementid;Mode;Vehicle_Category;Fuel_Type;Geography;MaximumCapacity;Payload_t;Total_distance;Empty_km_pct;Activity_Unit;WTT_Emissions_kg_per_tkm;TTW_Emissions_kg_per_tkm;Total_Emissions_kg_per_tkm;EmissionFactor;EnergyConsumption;AverageConsumption;Year";
    private static final String VALID_UNIT = "kgCO2e/t¬∑km";

    private final String VALID_ROW = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
    private CsvValidator validator;


    @BeforeEach
    void setup() {
        validator = new CsvValidator();
    }


    @Test
    public void testNullContent() {
        assertFalse(validator.validate(null));
    }

    @Test
    public void testEmptyContent() {
        assertFalse(validator.validate(""));
    }

    @Test
    public void testHeaderOnly() {
        assertFalse(validator.validate(HEADER));
    }

    @Test
    public void testRowWithEmptyLines() {
        String csv = HEADER + "\n" + VALID_ROW + "\n\n" + VALID_ROW;
        assertTrue(validator.validate(csv));
    }

    @Test
    public void testHeaderWithError() {
        String invalidHeader = HEADER.replace("ChainId", "Chain_ID_Errado");
        String csv = invalidHeader + "\n" + VALID_ROW;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testValidRow() {
        String csv = HEADER + "\n" + VALID_ROW;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidRow() {
        String invalidRow = "1;E1;Mode;Vehicle_Category;Fuel_Type;Geography;MaximumCapacity;Payload_t;Empty_km_pct;Activity_Unit;";
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
        String csv = "ChainId;Elementid;Mode;Vehicle_Category;Fuel_Type;Geography;MaximumCapacity;Payload_t;Total_distance;Empty_km_pct;\n" + " ";

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidDelimiter() {
        String invalidRow = VALID_ROW.replace(";", ",");
        String csv = HEADER.replace(";", ",") + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidChainId() {
        String invalidRow = ";E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidElementId() {
        String invalidRow = "1;;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidMaximumCapacity() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;XX;8;0%;" + VALID_UNIT + ";0;0;0.136;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testZeroMaximumCapacity() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;0;8;0%;" + VALID_UNIT + ";0;0;0.136;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidYearFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2ZX5";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidMode() {
        String invalidRow = "1;E1;;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testEmptyVehicleCategory() {
        String invalidRow = "1;E1;road;;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidTotalEmissions() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;-0.55;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testWrongTotalEmissionsFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;Muitas;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidEmptyKmPctFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;Dez%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testEmptyKmPctMissingPercentSymbol() {
        String invalidRow = VALID_ROW.replace(";0%;", ";0;");
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testEmptyKmPctIsEmpty() {
        String invalidRow = VALID_ROW.replace(";0%;", ";;");
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidPayload() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;-5;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testWrongPayloadFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;Trinta;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidActivityUnit() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;kgErrado;0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testActivityUnitWithSpaces() {
        String unitWithSpaces = "  " + VALID_UNIT + "  ";
        String validRowWithSpaces = VALID_ROW.replace(VALID_UNIT, unitWithSpaces);
        String csv = HEADER + "\n" + validRowWithSpaces;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testEmptyActivityUnit() {
        String invalidRow = VALID_ROW.replace(";" + VALID_UNIT + ";", ";;");
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidFuelType() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;RocketFuel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidGeography() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Mars;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidTotalDistanceFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;CEM;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testNegativeTotalDistance() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;-100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidEmissionFactorFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;XYZ;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testNegativeEmissionFactor() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;-0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidEnergyConsumptionFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;Cinco;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testNegativeEnergyConsumption() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;-50;10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testInvalidAverageConsumptionFormat() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;Dez;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    @Test
    public void testNegativeAverageConsumption() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;-10;2025";
        String csv = HEADER + "\n" + invalidRow;
        assertFalse(validator.validate(csv));
    }

    //BVA

    @Test
    public void testTotalEmissionsZero() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.0;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionLowerLimitInvalid() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;-0.0001;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmissionsUpperLimitValid() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.0001;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZero() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctHundred() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;100%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZLowerLimitInvalid() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;-1%;" + VALID_UNIT + ";0;0;0.0;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctZUpperLimitInvalid() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;101%;" + VALID_UNIT + ";0;0;0.0;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctLoweLimitValid() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;1%;" + VALID_UNIT + ";0;0;0.0;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testTotalEmptyKmPctUpperLimitValid() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;99%;" + VALID_UNIT + ";0;0;0.0;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testPayloadZero() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;0;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadLowerLimitInvalid() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;-0.0001;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testPayloadUpperLimitValid() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;0.0001;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testInvalidYear() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;3000";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testYearAboveLimit() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2026";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testYearBelowLimit() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;24;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;1999";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testMaxCapacityBoundaryZero() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;0;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }

    @Test
    public void testMaxCapacityBoundaryJustAboveZero() {
        String validRow = "1;E1;road;Heavy Duty Truck;diesel;Global;0.001;0.0001;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + validRow;

        assertTrue(validator.validate(csv));
    }

    @Test
    public void testMaxCapacityBoundaryNegative() {
        String invalidRow = "1;E1;road;Heavy Duty Truck;diesel;Global;-0.01;8;100;0%;" + VALID_UNIT + ";0;0;0.136;0.5;50;10;2025";
        String csv = HEADER + "\n" + invalidRow;

        assertFalse(validator.validate(csv));
    }
}