package DataIngest;

import java.util.List;

public interface ICsvLoader {

    public List<String[]> load(String path);
}
