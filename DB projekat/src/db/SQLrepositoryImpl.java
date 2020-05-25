package db;

import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.settings.Settings;
import model.DBNode;
import model.DBNodeComposite;
import model.categories.Attribute;
import model.categories.AttributeConstraints;
import model.categories.Resourse;
import model.categories.Table;
import model.data.Row;
import model.enums.AttributeType;
import model.enums.ConstraintType;

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
				
				ResultSet PK = metaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
				ResultSet FK = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
				
				while(columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");
					int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
					Attribute newAttribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
					newTable.addChild(newAttribute);
					
					
					String isNullable = columns.getString("IS_NULLABLE");
					if(isNullable.equals("NO")) {
						AttributeConstraints notnull = new AttributeConstraints("NOT NULL", newAttribute, ConstraintType.NOT_NULL);
						newAttribute.addChild(notnull);
					}
					
					while(PK.next()) {
						if(PK.getString("COLUMN_NAME").equals(columnName)) {
							AttributeConstraints primaryKey = new AttributeConstraints(PK.getString("PK_NAME"), newAttribute, ConstraintType.PRIMARY_KEY);
							newAttribute.addChild(primaryKey);
						}
					}
					
				}
				
				while(FK.next()) {
					for(DBNode t: resourse.getChildren()) {
						Table trenutnaTabela = (Table) t;
							for(DBNode a: trenutnaTabela.getChildren()) {
								Attribute trenutniAtribut = (Attribute) a;
								if(FK.getString("PKCOLUMN_NAME").equals(trenutniAtribut.getName())) {
									AttributeConstraints foreignKey = new AttributeConstraints(FK.getString("FK_NAME"), trenutniAtribut, ConstraintType.FOREIGN_KEY);
									trenutniAtribut.addChild(foreignKey);
								}
							}
					}
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
	public List<Row> get(String from) {
		// TODO Auto-generated method stub
		List<Row> rows = new ArrayList<>();
		
		try {
			this.connect();
			
			String query = "SELECT * FROM " + from;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Row row = new Row();
				row.setName(from);
				
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=1; i<=rsmd.getColumnCount(); i++) {
					row.addField(rsmd.getColumnName(i), rs.getString(i));
				}
				rows.add(row);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		
		return rows;
	}

}
