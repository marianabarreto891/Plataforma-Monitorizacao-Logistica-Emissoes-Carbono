package DataIngest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICsvLoader that loads CSV files from a given file path.
 */
public class CsvLoader implements ICsvLoader {

    /**
     * Loads a CSV file and returns its contents as a list of String arrays.
     * Each array represents one line of the file, split by the ';' delimiter.
     *
     * @param path The path to the CSV file.
     * @return A list of rows, where each row is represented as a String[].
     */
    @Override
    public List<String[]> load(String path) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return data;
    }
}