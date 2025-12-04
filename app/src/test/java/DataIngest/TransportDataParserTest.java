package DataIngest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TransportChain.HubOperation;
import TransportChain.TransportChain;
import TransportChain.TransportOperation;

public class TransportDataParserTest {

    private TransportDataParser parser;
    private int headerSize;

    @BeforeEach
    void setup() {
        parser = new TransportDataParser();
        headerSize = Header.values().length;
    }

   
    private String[] createBaseRow() {
        String[] row = new String[headerSize];
      
        for (int i = 0; i < headerSize; i++) {
            row[i] = "0"; 
        }
        
       
        row[Header.CHAIN_ID.ordinal()] = "1";
        row[Header.ELEMENT_ID.ordinal()] = "E1";
        row[Header.MODE.ordinal()] = "Road";     
        row[Header.FUEL_TYPE.ordinal()] = "diesel"; 
        
        
        row[Header.TOTAL_DISTANCE.ordinal()] = "100"; 
        return row;
    }

    @Test
    public void testValidTransportOperation() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = "500.5";

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().get(0) instanceof TransportOperation);
        
        TransportOperation op = (TransportOperation) chain.getElements().get(0);
        assertEquals(500.5, op.getDistanceKm(), 0.001);
    }

    @Test
    public void testValidHubOperationZeroDistance() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = "0";
        row[Header.ENERGY_CONSUMPTION.ordinal()] = "150.5";

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));

        assertEquals(1, chain.getElements().size());
        assertTrue(chain.getElements().get(0) instanceof HubOperation);

        HubOperation hub = (HubOperation) chain.getElements().get(0);
        assertEquals(150.5, hub.getEnergyConsumption(), 0.001);
    }

    @Test
    public void testHubOperationNegativeDistance() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = "-10";
        row[Header.EMISSION_FACTOR.ordinal()] = "0.75";

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));

        assertTrue(chain.getElements().get(0) instanceof HubOperation);
            
        HubOperation hub = (HubOperation) chain.getElements().get(0);
        assertEquals(0.75, hub.getEmissionFactor(), 0.001);
    }

    @Test
    public void testSeparatorReplacement() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = "100,50";

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));
        
        TransportOperation op = (TransportOperation) chain.getElements().get(0);
        assertEquals(100.50, op.getDistanceKm(), 0.001);
    }

    @Test
    public void testInvalidNumberFormat() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = "TextoInvalido"; 

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));

       
        assertTrue(chain.getElements().get(0) instanceof HubOperation);
    }

    @Test
    public void testNullValuesInRow() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = null; 
        row[Header.ENERGY_CONSUMPTION.ordinal()] = null;
        
        TransportChain chain = parser.parseToChain(Collections.singletonList(row));
        
        assertTrue(chain.getElements().get(0) instanceof HubOperation);
        HubOperation hub = (HubOperation) chain.getElements().get(0);
        assertEquals(0.0, hub.getEnergyConsumption(), 0.001);
    }

    @Test
    public void testEmptyStrings() {
        String[] row = createBaseRow();
        row[Header.TOTAL_DISTANCE.ordinal()] = ""; 

        TransportChain chain = parser.parseToChain(Collections.singletonList(row));

        assertTrue(chain.getElements().get(0) instanceof HubOperation);
    }

    @Test
    public void testShortRowArray() {
        String[] shortRow = new String[1]; 

        TransportChain chain = parser.parseToChain(Collections.singletonList(shortRow));

        assertFalse(chain.getElements().isEmpty());
        assertTrue(chain.getElements().get(0) instanceof HubOperation);
    }

    @Test
    public void testEmptyList() {
        TransportChain chain = parser.parseToChain(new ArrayList<>());
        assertNotNull(chain);
        assertTrue(chain.getElements().isEmpty());
    }
}