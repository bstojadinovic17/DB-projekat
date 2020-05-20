package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.settings.Settings;
import model.DBNode;
import model.DBNodeComposite;
import model.categories.Attribute;
import model.categories.Resourse;
import model.categories.Table;
import model.enums.AttributeType;

public class SQLrepositoryImpl implements Repository{
	
	private Settings settings;
	private Connection connection;
	
	public SQLrepositoryImpl(Settings settings) {
		// TODO Auto-generated constructor stub
		this.settings = settings;
	}
	
	private void connect() throws ClassNotFoundException, SQLException {
		 	Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        String ip = (String) settings.getParameter("mssql_ip");
	        String database = (String) settings.getParameter("mssql_database");
	        String username = (String) settings.getParameter("mssql_username");
	        String password = (String) settings.getParameter("mssql_password");
	        Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
	       
        }
	
	
	private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }
	
	
	@Override
	public DBNode getSchema() {
		// TODO Auto-generated method stub
		
		try {
			this.connect();
			DatabaseMetaData metaData = connection.getMetaData();
			Resourse resourse = new Resourse("tim_27_bp2020");
			
			String tableType[] = {"TABLE"};
			ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
			
			while(tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				Table newTable = new Table(tableName, resourse);
				resourse.addChild(newTable);
				
				ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);
				
				while(columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");
					int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
					
					Attribute newAttribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
					newTable.addChild(newAttribute);
							
				}
			}
			
			
			for (DBNode t: resourse.getChildren()) {
				System.out.println("Ime tabele: " + t);
				System.out.println("----------------------------");
				for(DBNode a: ((DBNodeComposite) t).getChildren()) {
					System.out.println("	Atribut: "+ a);
				}
			}
			return resourse;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//System.out.println("greska 1");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("greska2");
			e.printStackTrace();
		}
		finally {
			this.closeConnection();
		}
		return null;
	}

	@Override
	public List<String> get(String from) {
		// TODO Auto-generated method stub
		return null;
	}

}
