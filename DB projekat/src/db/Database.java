package db;

import java.util.List;
import java.util.Vector;

import model.DBNode;
import model.categories.Attribute;
import model.categories.Table;
import model.data.Row;

public interface Database {
	
	DBNode loadResourse();
	
	List<Row> readDataFromTable(String tableName);
	
	void deleteData(String from, String column, String value);
	
	void addData(Table tabela);
	
	List<Row> filterData(String from, List<String> data);
	
	List<Row> sortData(String from, String column, String order);
}
