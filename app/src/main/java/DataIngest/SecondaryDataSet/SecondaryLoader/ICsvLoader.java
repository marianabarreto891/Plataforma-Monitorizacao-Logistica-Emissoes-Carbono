package DataIngest.SecondaryDataSet.SecondaryLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ICsvLoader {

    List<String[]> load(InputStream is) throws IOException;

}
