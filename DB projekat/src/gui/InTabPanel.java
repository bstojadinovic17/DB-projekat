package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class InTabPanel extends JPanel{
	
	private JTable tabela;
	private JToolBar toolbar;
	
	public InTabPanel(JTable table) {
		// TODO Auto-generated constructor stub
		this.tabela = table;
		toolbar = new JToolBar();
		toolbar.setSize(700,80);
		setSize(new Dimension(700, 350));
		setLayout(new BorderLayout());
		JScrollPane scrollTable = new JScrollPane(tabela);
		scrollTable.setMinimumSize(new Dimension(700, 270));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setSize(new Dimension(40,25));
		btnAdd.addActionListener(MainView.getinstance().getActionManager().getAddAction());
		toolbar.add(btnAdd);
		toolbar.addSeparator();
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setSize(new Dimension(40,25));
		btnDelete.addActionListener(MainView.getinstance().getActionManager().getDeleteAction());
		toolbar.add(btnDelete);
		toolbar.addSeparator();
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setSize(new Dimension(40,25));
		btnUpdate.addActionListener(MainView.getinstance().getActionManager().getUpdateAction());
		toolbar.add(btnUpdate);
		toolbar.addSeparator();
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.setSize(new Dimension(50,25));
		btnFilter.addActionListener(MainView.getinstance().getActionManager().getFilterAction());
		toolbar.add(btnFilter);
		toolbar.addSeparator();
		
		JButton btnSort = new JButton("Sort");
		btnSort.setSize(new Dimension(50,25));
		btnSort.addActionListener(MainView.getinstance().getActionManager().getSortAction());
		toolbar.add(btnSort);
		toolbar.addSeparator();
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setSize(new Dimension(40,25));
		toolbar.add(btnSearch);
		toolbar.addSeparator();
		
		JButton btnCount = new JButton("Count");
		btnCount.setSize(new Dimension(40,25));
		toolbar.add(btnCount);
		toolbar.addSeparator();
		
		JButton btnAverage = new JButton("Averaage");
		btnAverage.setSize(new Dimension(40,25));
		toolbar.add(btnAverage);
		toolbar.addSeparator();
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setSize(new Dimension(40,25));
		btnRefresh.addActionListener(MainView.getinstance().getActionManager().getRefreshAction());
		toolbar.add(btnRefresh);
		toolbar.addSeparator();
		
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTable, toolbar);
		add(split, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public JTable getTabela() {
		return tabela;
	}
}
