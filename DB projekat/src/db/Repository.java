package db;

import java.util.List;

import model.DBNode;
import model.data.Row;

public interface Repository {
	
	DBNode getSchema();
	
	List<Row> get(String from);
}
