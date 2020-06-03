package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import gui.InTabPanel;
import gui.MainView;
import gui.Tab;
import gui.table.TableModel;
import model.categories.Table;
import model.data.Row;

public class FilterAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int indexTaba = Tab.getInstance().getTabbedPane().getSelectedIndex();
		InTabPanel panel = (InTabPanel) Tab.getInstance().getTabbedPane().getComponent(indexTaba);
		
		Table trenutnaTabela = Tab.getInstance().getTabele().get(indexTaba);
		TableModel model= (TableModel) panel.getTabela().getModel();
		JTable tabela = panel.getTabela();
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		JPanel mainPanel = new JPanel();
		HashMap<String, JCheckBox> boxovi = new HashMap<>();
		List<String> kolone= new ArrayList<>();
		for(int i=0;i<tabela.getColumnCount();i++) {
			kolone.add(tabela.getColumnName(i));
			boxovi.put(tabela.getColumnName(i), new JCheckBox());
		}
		for(String s: kolone) {
			JLabel labela = new JLabel(s);
			JCheckBox box = boxovi.get(s);
			mainPanel.add(labela);
			mainPanel.add(box);
		}
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setSize(50,25);
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<String> zaFilter = new ArrayList<>();
				for(String s:kolone) {
					JCheckBox trenutniBox = boxovi.get(s);
					if(trenutniBox.isSelected()) {
						zaFilter.add(s);
					}
				}
				frame.setVisible(false);
				model.setRows(MainView.getinstance().getAppCore().getDatabase().filterData(trenutnaTabela.getName(), zaFilter));
			}
		});
		mainPanel.add(btnSubmit);
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	

}
