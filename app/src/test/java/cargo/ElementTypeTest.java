import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ElementType enum.
 */
class ElementTypeTest {

    /**
     * Test case: Verify that enum values exist
     *
     * Given the need to identify logistics chain elements,
     * When accessing ElementType.HUB and ElementType.TRANSPORT,
     * Then both enum values should exist and not be null.
     *
     */
    @Test
    void testEnumValuesExist() {
        ElementType hub = ElementType.HUB;         
        ElementType transport = ElementType.TRANSPORT; 

        assertNotNull(hub);                        
        assertNotNull(transport);                 
    }

    /**
     * Test case: Verify that getCode() returns correct values
     *
     * Given ElementType enum values,
     * When calling getCode() on HUB and TRANSPORT,
     * Then HUB returns "HO" and TRANSPORT returns "TO".
     *
     */
    @Test
    void testEnumGetCode() {
        assertEquals("HO", ElementType.HUB.getCode());      
        assertEquals("TO", ElementType.TRANSPORT.getCode()); 
    }

    /**
     * Test case: Validate that only valid enum values are accepted
     *
     * Given only HUB and TRANSPORT are valid,
     * When attempting to access an invalid enum value,
     * Then IllegalArgumentException should be thrown.
     */
    @Test
    void testInvalidEnumValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ElementType.valueOf("INVALID");
        });
        assertTrue(exception.getMessage().contains("No enum constant"));
    }
}
