package db;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import db.settings.Settings;
import gui.Tab;

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
					//System.out.println(FK.getString("FKCOLUMN_NAME"));
					for(DBNode a: newTable.getChildren()) {
						Attribute attribute = (Attribute) a;
							if(attribute.getName().equals(FK.getString("PKCOLUMN_NAME")) && !(FK.getString("PKTABLE_NAME").equals(newTable))) {
								AttributeConstraints foreignKey = new AttributeConstraints(FK.getString("FK_NAME"), attribute, ConstraintType.FOREIGN_KEY);
								attribute.addChild(foreignKey);
							}
							if(attribute.getName().equals(FK.getString("FKCOLUMN_NAME"))) {
								//System.out.println(FK.getString("PKCOLUMN_NAME"));
								if(!(FK.getString("PKCOLUMN_NAME").equals(FK.getString("FKCOLUMN_NAME")))) {
									AttributeConstraints foreignKey = new AttributeConstraints(FK.getString("FK_NAME"), attribute, ConstraintType.FOREIGN_KEY);
									attribute.addChild(foreignKey);
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
	
	@Override
	public void delete(String from, String columnName, String value) {
		// TODO Auto-generated method stub
		
		
		try {
			this.connect();
			DatabaseMetaData metaData = connection.getMetaData();
			String tableType[] = {"TABLE"};
			ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
			System.out.println(tables);
			/*while(tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				ResultSet FK = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
				while(FK.next()) {
					String pk_name = FK.getString("PKCOLUMN_NAME");
					if(pk_name.contains(columnName)) {
						//System.out.println(pk_name + " = " + columnName);
						String fk_tableName = FK.getString("FKTABLE_NAME");
						String query = "DELETE FROM "+ fk_tableName + " WHERE " + columnName +" = " + "'"+ value +"'";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.executeUpdate();
		
					}
				}
				
			} */
			
			while(tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				ResultSet FK = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
				String kolona = "";
				System.out.println("Za tabelu "+ tableName);
				while(FK.next()) {
					kolona = FK.getString("PKTABLE_NAME");
					System.out.println(kolona);
				}
				System.out.println("---------------");
			}
			/*String query = "DELETE FROM "+ from + " WHERE " + columnName +" = " + "'"+ value +"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			System.out.println("obrisan red");*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		
	}

	@Override
	public void add(Table to) {
		// TODO Auto-generated method stub
		
		try {
			this.connect();
			ArrayList<DBNode> atributi = (ArrayList<DBNode>) to.getChildren();
			int cnt = to.getChildCount();
			ArrayList<Object> values = new ArrayList<>();
			String query = "INSERT INTO "+ to.getName() + " (";
			for(DBNode a: atributi) {
				Attribute atribut = (Attribute) a;
				AttributeType type = atribut.getAttributeType();
				int lenght = atribut.getLenght();
				cnt--;
				if(cnt == 0) {
					query+= atribut.getName()+ ") VALUES (";
				}else {
				query+= atribut.getName()+", ";
				}
				
				String poruka = atribut.getName()+": ";
				Object value = null;
				if(type.toString().contains("CHAR") || type.toString().contains("TEXT")) {
					value = JOptionPane.showInputDialog(poruka);
					if(value == null) {
						return;
					}
					while(value.toString().length() > lenght) {
						JOptionPane.showMessageDialog(null, "Ne moze duze od "+lenght+ " karaktera!");
						value = JOptionPane.showInputDialog(poruka);
						if(value == null) {
							return;
						}
					}
				} else if(type.toString().contains("INT") || type.toString().contains("NUMERIC")) {
					value = Integer.parseInt(JOptionPane.showInputDialog(poruka));	
				} else if(type.toString().contains("FLOAT") || type.toString().contains("DECIMAL")) {
					value = Float.parseFloat(JOptionPane.showInputDialog(poruka));
				} else if(type.toString().contains("DATE") || type.toString().contains("TIME")) {
					value = Date.parse(JOptionPane.showInputDialog(poruka));
				}else {
					value = JOptionPane.showInputDialog(poruka);
				}
				
				while(value.equals("")) {
					if(atribut.getChildByName("NOT NULL") !=null) {
						JOptionPane.showMessageDialog(null, "NE MOZE PRAZNO!");
						value = JOptionPane.showInputDialog(poruka);
					}else{
						value = "";
						break;
					}
				}
				
				values.add(value);
			}
			for(int i=0;i<values.size()-1;i++) {
					query+= "'"+values.get(i)+"' , ";
			}
			query+="'"+values.get(values.size()-1)+"' "+")";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Red uspesno dodat!");
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			if(e.toString().contains("duplicate key")) {
				JOptionPane.showMessageDialog(null, "Primarni kljuc vec postoji u tabeli!");
				return;
			}
			else if(e.toString().contains("conflicted with the FOREIGN KEY")){
				JOptionPane.showMessageDialog(null, "Uneli ste vrednost za foreign key koja ne postoji u toj tabeli!");
				return;
			}
			else {
				e.printStackTrace();
			}
			
		} finally {
			this.closeConnection();
		}
	}
	
	
	
	

	@Override
	public List<Row> count(String from, String countColumn, List<String> data) {
		// TODO Auto-generated method stub
		List<Row> rows = new ArrayList<>();
		try {
			this.connect();
			String query = "SELECT COUNT("+countColumn+"), ";
			for(String s: data) {
				if(data.indexOf(s)== data.size()-1) {
					query+=s;
				}else {
					query+=s+" , ";
				}
			}
			query+= " FROM "+from+" GROUP BY ";
			for(String s: data) {
				if(data.indexOf(s)== data.size()-1) {
					query+=s;
				}else {
					query+=s+" , ";
				}
			}
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Row row = new Row();
				row.setName(from);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					row.addField(rsmd.getColumnName(i), rs.getString(i));
				}
				rows.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return rows;
	}

	@Override
	public List<Row> average(String from, String averageColumn, List<String> data) {
		// TODO Auto-generated method stub
		List<Row> rows = new ArrayList<>();
		System.out.println(data);
		try {
			this.connect();
			String query = " SELECT ";
			for(String s: data) {
				query+=s+" , ";
			}
			query+=" AVG("+averageColumn+") FROM "+from+" GROUP BY ";
			for(String s: data) {
				if(data.indexOf(s)== data.size()-1) {
					query+=s;
				}else {
					query+=s+" , ";
				}
			}
			//System.out.println(query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Row row = new Row();
				row.setName(from);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					row.addField(rsmd.getColumnName(i), rs.getString(i));
				}
				rows.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return rows;
	}

	/*public List<Row> getTableModelFromTableName(String from, String column, String value){

	}*/

	public List<String> getTableModelFromRow(String tableName){
		try {
			this.connect();
			DatabaseMetaData metaData = connection.getMetaData();
			String tableType[] = {"TABLE"};
			ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
			//System.out.println(tables);
			List<String> toReturn = new ArrayList<>();
			while(tables.next()) {
				if(tableName.equals(tables.getString("TABLE_NAME"))){
					ResultSet FK = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
					String kolona = "";
					//System.out.println("Za tabelu "+ tableName);
					while(FK.next()) {
						kolona = FK.getString("PKTABLE_NAME");
						//System.out.println(kolona);
						toReturn.add(kolona);
					}
					//System.out.println("---------------");
				}
				}

				return toReturn;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
			return null;
	}

	

	@Override
	public void update(Table tabela, String pk, HashMap<String, String> rowData) {
		// TODO Auto-generated method stub
		try {
			this.connect();
			ArrayList<DBNode> atributi = (ArrayList<DBNode>) tabela.getChildren();
			ArrayList<Object> values = new ArrayList<>();
			Set<String> headeri = rowData.keySet();
			String primaryKey = null;
			String query = "UPDATE "+tabela.getName()+" SET ";
			for(DBNode a:atributi) {
				Attribute atribut = (Attribute) a;
				String attributeName = atribut.getName();
				AttributeType type = atribut.getAttributeType();
				int lenght = atribut.getLenght();
				
				String poruka = null;
				String currentData = null;
				Object value = null;
				for(int i=0;i<headeri.size();i++) {
					if(headeri.contains(attributeName)) {
						poruka = attributeName+":";
						currentData = rowData.get(attributeName.toString());
					}
				}
				if(type.toString().contains("CHAR") || type.toString().contains("TEXT")) {
					value = JOptionPane.showInputDialog(poruka, currentData);
					if(value == null) {
						return;
					}
					while(value.toString().length() > lenght) {
						JOptionPane.showMessageDialog(null, "Ne moze duze od "+lenght+" karaktera!");
						value = JOptionPane.showInputDialog(poruka);
						if(value == null) {
							return;
						}
					}
				} else if(type.toString().contains("INT") || type.toString().contains("NUMERIC")) {
					value = Integer.parseInt(JOptionPane.showInputDialog(poruka, currentData));
					if(value == null) {
						return;
					}
				} else if(type.toString().contains("FLOAT") || type.toString().contains("DECIMAL")) {
					value = Float.parseFloat(JOptionPane.showInputDialog(poruka, currentData));
					if(value == null) {
						return;
					}
				} else {
					value = JOptionPane.showInputDialog(poruka, currentData);
				}
				
				while(value.equals("")) {
					if(atribut.getChildByName("NOT NULL") !=null) {
						JOptionPane.showMessageDialog(null, "Ne mozete ostaviti prazno!");
						value = JOptionPane.showInputDialog(poruka, currentData);
					}else {
						value="";
						break;
					}
				}
				if(!(value.equals(currentData))) {
					values.add(poruka+value);
				}

			}
			for(int i=0;i<values.size()-1;i++) {
				String[] split = values.get(i).toString().split(":");
				query+= split[0]+" = '"+split[1]+"' , ";
			}
			String split[] = values.get(values.size()-1).toString().split(":");
			String pkSplit[] = pk.split(":");
			String pkColumnName = pkSplit[0];
			String pkValue = pkSplit[1];
			query+=split[0]+" = '"+split[1]+"' WHERE "+pkColumnName+" = '" +pkValue+"'";
			System.out.println(query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			if(e.toString().contains("duplicate key")) {
				JOptionPane.showMessageDialog(null, "Primarni kljuc vec postoji u tabeli!");
				return;
			} else if(e.toString().contains("conflicted with the FOREIGN KEY")) {
				JOptionPane.showMessageDialog(null, "Uneli ste vrednost za foreign key koja ne postoji u toj tabeli!");
				return;
			} else {
				e.printStackTrace();
			}
		} finally {
			this.closeConnection();
		}
		
	}

	@Override
	public List<Row> filterIsort(String from, HashMap<String, String> data) {
		// TODO Auto-generated method stub
		List<Row> rows = new ArrayList<Row>();
		List<String> columns = new ArrayList<String>();
		Set<String> kolone = data.keySet();
		try {
			this.connect();
			String query = "SELECT ";
			int cnt = data.size();
			Iterator<String> it = kolone.iterator();
			while(it.hasNext()) {
				columns.add(it.next());			
			}
			if(columns.size()>1) {
				for(int i=0;i<columns.size()-1;i++) {
					query+=columns.get(i)+" , ";
				}
				query+=columns.get(columns.size()-1);
			}else {
				query+=columns.get(columns.size()-1);
			}
			query+=" FROM "+from+" ORDER BY ";
			for(String s:columns) {
				if(columns.indexOf(s) == columns.size()-1) {
					query+=s+" "+data.get(s).toString();
				}else {
					query+=s+" "+data.get(s).toString()+" , ";
				}
			}
			System.out.println(query);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Row row = new Row();
				row.setName(from);
				ResultSetMetaData rsmd = rs.getMetaData();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					row.addField(rsmd.getColumnName(i), rs.getString(i));
				}
				rows.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return rows;
	}

	

	

	

	

	

}
