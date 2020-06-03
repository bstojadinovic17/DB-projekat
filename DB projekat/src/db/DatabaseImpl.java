package db;

import java.util.List;
import java.util.Vector;

import model.DBNode;
import model.categories.Attribute;
import model.categories.Table;
import model.data.Row;

public class DatabaseImpl implements Database{
	
	private Repository repository;
	
	public DatabaseImpl(SQLrepositoryImpl sqLrepositoryImpl) {
		// TODO Auto-generated constructor stub
		this.repository = sqLrepositoryImpl;
	}

	@Override
	public DBNode loadResourse() {
		return repository.getSchema();
	}

	@Override
	public List<Row> readDataFromTable(String from) {
		return repository.get(from);
	}

	
	public Repository getRepository() {
		return repository;
	}

	@Override
	public void deleteData(String from, String columnName, String value) {
		// TODO Auto-generated method stub
		repository.delete(from, columnName, value);
	}

	@Override
	public void addData(Table toTableName) {
		// TODO Auto-generated method stub
		repository.add(toTableName);
	}
	
	
	@Override
	public List<Row> filterData(String from, List<String> data) {
		// TODO Auto-generated method stub
		return repository.filter(from, data);
	}

	@Override
	public List<Row> sortData(String from, String column, String order) {
		// TODO Auto-generated method stub
		return repository.sort(from,column,order);
	}

	@Override
	public List<Row> countData(String from, String countColumn, List<String> data) {
		// TODO Auto-generated method stub
		return repository.count(from,countColumn, data);
	}

	@Override
	public List<Row> averageData(String from, String averageColumn, List<String> data) {
		// TODO Auto-generated method stub
		return repository.average(from,averageColumn, data);
	}

	@Override
	public void updateData(Table tabela, String pk, List<String> rowData) {
		// TODO Auto-generated method stub
		repository.update(tabela, pk, rowData);
	}

	
}
