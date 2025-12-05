package DataIngest;

import java.util.List;

/**
 * Interface responsible for loading CSV files.
 */
public interface ICsvLoader {

    /**
     * Reads a CSV file from the given path.
     *
     * @param path Path to the CSV file.
     * @return A list of rows, where each row is represented as a String array.
     */
    public List<String[]> load(String path);
}