package DataIngest;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvLoaderTest {

    CsvLoader loader = new CsvLoader();

    @Test
    void testLoadCsv() {
        var url = getClass().getClassLoader().getResource("test.csv");
        assertNotNull(url, "O ficheiro test.csv não foi encontrado!");

        List<String[]> data;
        try {
            data = loader.load(url.getPath());
        } catch (Exception e) {
            fail("Erro ao carregar o CSV: " + e.getMessage());
            return;
        }

        assertEquals(2, data.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, data.get(0));
        assertArrayEquals(new String[]{"1", "2", "3"}, data.get(1));
    }

    @Test
    void testEmptyCsv() throws IOException {
        File csv = createTempCsv("");

        List<String[]> data = loader.load(csv.getAbsolutePath());

        assertEquals(0, data.size());
    }

    @Test
    void testNonExistentFile() {
        List<String[]> data = loader.load("nonexistent.csv");
        assertEquals(0, data.size());
    }

    private File createTempCsv(String content) throws IOException {
        File temp = File.createTempFile("test", ".csv");
        temp.deleteOnExit();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            bw.write(content);
        }
        return temp;
    }
}
