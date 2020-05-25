package db;

import java.util.List;

import model.DBNode;
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
}
