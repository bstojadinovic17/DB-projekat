package db;

import java.util.List;

import model.DBNode;

public interface Repository {
	
	DBNode getSchema();
	
	List<String> get(String from);
}
