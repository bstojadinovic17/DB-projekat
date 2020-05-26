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
		toolbar.setSize(100,350);
		setSize(new Dimension(550, 350));
		setLayout(new BorderLayout());
		JScrollPane scrollTable = new JScrollPane(tabela);
		scrollTable.setMinimumSize(new Dimension(450, 350));
		
		//JButton btnAdd = new JButton("Add");
		//btnAdd.setSize(new Dimension(50,25));
		//toolbar.add(btnAdd);
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTable, toolbar);
		add(scrollTable, BorderLayout.NORTH);
		setVisible(true);
	}
	
	
}
