package db;

import java.util.HashMap;
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
	
	List<Row> filter(String from, List<String> data);

	List<Row> sort(String from, String column, String order);

	List<Row> count(String from, String countColumn, List<String> data);

	List<Row> average(String from, String averageColumn, List<String> data);

	List<String> getTableModelFromRow(String tableName);

	void update(Table tabela, String pk, HashMap<String, String> rowData);
	
}
