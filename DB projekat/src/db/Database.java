package db;

import java.util.List;
import model.DBNode;
import model.categories.Table;
import model.data.Row;

public interface Database {
	
	DBNode loadResourse();
	
	List<Row> readDataFromTable(String tableName);
	
	void deleteData(String from, String column, String value);
	
	void addData(Table tabela);
	
	void updateData(Table tabela, String pk ,List<String> rowData);
	
	List<Row> filterData(String from, List<String> data);
	
	List<Row> sortData(String from, String column, String order);
	
	List<Row> countData(String from, String countColumn, List<String> data);
	
	List<Row> averageData(String from, String averageColumn, List<String> data);
}
