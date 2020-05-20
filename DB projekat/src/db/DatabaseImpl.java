package db;

import java.util.List;

import model.DBNode;

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
	public List<String> readDataFromTable(String tableName) {
		return repository.get(tableName);
	}

}
