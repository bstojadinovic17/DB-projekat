package db;

import java.util.List;
import java.util.Vector;

import model.DBNode;
import model.categories.Attribute;
import model.categories.Table;
import model.data.Row;

public interface Repository {
	
	DBNode getSchema();
	
	List<Row> get(String from);

	void delete(String from, String column, String value);
	
	void add(Table toTableName);
}
