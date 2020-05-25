package db;

import java.util.List;

import model.DBNode;
import model.data.Row;

public interface Database {
	
	DBNode loadResourse();
	
	List<Row> readDataFromTable(String tableName);
}
