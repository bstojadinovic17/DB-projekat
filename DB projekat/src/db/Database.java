package db;

import java.util.List;

import model.DBNode;

public interface Database {
	
	DBNode loadResourse();
	
	List<String> readDataFromTable(String tableName);
}
