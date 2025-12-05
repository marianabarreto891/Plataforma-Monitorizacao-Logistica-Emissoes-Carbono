package DataIngest.SecondaryDataSet.SecondaryLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader implements ICsvLoader {

    @Override
    public List<String[]> load(InputStream is) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                data.add(values);
            }
        }

        return data;
    }
}
