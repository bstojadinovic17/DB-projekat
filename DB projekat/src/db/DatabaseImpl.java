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
}
