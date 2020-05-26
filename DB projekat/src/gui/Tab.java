package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import gui.table.TableModel;
import model.categories.Table;

public class Tab extends JTabbedPane{
	
	private static Tab instance = null;
	private JTabbedPane tabbedPane;
	private static ArrayList<Table> tabele;
	
	private Tab() {
		// TODO Auto-generated constructor stub
		
		tabele = new ArrayList<>();
		tabbedPane = new JTabbedPane();
		tabbedPane.setSize(new Dimension(550,350));
		tabbedPane.setVisible(true);
	}
	
	public void dodajTab(Object arg) {
		if(arg instanceof Table) {
			Table t = (Table) arg;
			
			boolean ima=false;
			for(int i=0;i<tabbedPane.getTabCount();i++) {
				if(tabbedPane.getTitleAt(i).equals(t.getName())) {
					tabbedPane.setSelectedIndex(i);
					ima = true;
					break;
				}
				
			}
			
			if(ima == false) {
				
				JTable tabela = new JTable();
				TableModel model = new TableModel();
				model.setRows(MainView.getinstance().getAppCore().getDatabase().readDataFromTable(t.getName()));
				tabela.setModel(model);
				InTabPanel panel = new InTabPanel(tabela);
				tabbedPane.addTab(t.getName(), panel);
				System.out.println(t.getName() + " dodat");
				tabbedPane.setFocusable(true);
				tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
			}
		}
	}
	
	public static Tab getInstance() {
		if(instance==null) instance=new Tab();
		return instance;
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
