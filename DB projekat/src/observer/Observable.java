package observer;

import java.util.List;

public interface Observable {

    public void addObserver(Observer o);

    public void notify(Object o, List<String> columnNames, List<String> values, int where);


}
