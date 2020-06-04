package observer;

import java.util.List;

public interface Observer {

    public void update(Object o, List<String> columnNames, List<String> values, int where);

}
