package DataIngest;
import java.util.List;

import TransportChain.TransportChain;

public interface ICsvParser{
    /**
     * Parses a list of CSV data represented as arrays of strings.
     *
     * @param data the list of string arrays representing CSV rows.
     * @return a list of parsed objects.
     */
    TransportChain parseToChain(List<String[]> data);
} 
    