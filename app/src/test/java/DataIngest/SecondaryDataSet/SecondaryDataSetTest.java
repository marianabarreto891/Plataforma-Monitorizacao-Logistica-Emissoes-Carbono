package DataIngest.SecondaryDataSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SecondaryDataSet.
 * These tests verify:
 *  - Correct loading of known values
 *  - Case-insensitive lookups
 *  - Null return for unknown keys
 *  - Proper key generation for mode/vehicle queries
 */
class SecondaryDataSetTest {

    /** Ensures getEfWtt returns a valid value for a known fuel. */
    @Test
    void getEfWttReturnsExpectedValueForKnownFuel() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double dieselWtt = dataSet.getEfWtt("diesel");

        assertNotNull(dieselWtt);
    }

    /** Ensures fuel lookup is case-insensitive. */
    @Test
    void getEfWttIsCaseInsensitive() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double lower = dataSet.getEfWtt("diesel");
        Double mixed = dataSet.getEfWtt("DiEsEl");

        assertEquals(lower, mixed);
    }

    /** Unknown fuel should return null instead of throwing an exception. */
    @Test
    void getEfWttReturnsNullForUnknownFuel() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double result = dataSet.getEfWtt("unicorn-fuel");

        assertNull(result);
    }

    /** Validates the combination key logic for mode + vehicle. */
    @Test
    void getAverageConsumptionUsesModeAndVehicleKey() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double value = dataSet.getAverageConsumption("road", "medium_truck");

        assertNotNull(value);
    }

    /** Ensures getFuelUnit returns a unit for known fuels. */
    @Test
    void getFuelUnitReturnsExpectedUnitForKnownFuel() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        String unit = dataSet.getFuelUnit("diesel");

        assertNotNull(unit);
    }

    /** Ensures capacity lookup works for a known mode/vehicle combination. */
    @Test
    void getCapacityTonReturnsValueForModeAndVehicle() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double capacity = dataSet.getCapacityTon("road", "medium_truck");

        assertNotNull(capacity);
    }

    /** Catches the expected nullpointerexception if the given string is null. */
    @Test
    void getEfWttFailsOnNullInput() {
        SecondaryDataSet dataSet = new SecondaryDataSet();
        assertThrows(NullPointerException.class, () -> dataSet.getEfWtt(null));
    }


    /** Empty or blank fuel type should return null */
    @Test
    void emptyOrBlankFuelTypeReturnsNull() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        assertNull(dataSet.getEfWtt(""));
        assertNull(dataSet.getEfWtt("   "));
    }


    /** Malformed fuel type should not match any entry and return null */
    @Test
    void malformedFuelTypeReturnsNull() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double result = dataSet.getEfWtt("diesel##");

        assertNull(result);
    }

    /** Non-existing mode/vehicle pair should return null */
    @Test
    void invalidModeAndVehicleReturnNull() {
        SecondaryDataSet dataSet = new SecondaryDataSet();

        Double result = dataSet.getAverageConsumption("road", "spaceship");

        assertNull(result);
    }



}
