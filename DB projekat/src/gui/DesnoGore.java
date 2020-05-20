package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class DesnoGore extends JPanel{
	
	private JFrame frame = MainView.getinstance();
	
	public DesnoGore() {
		// TODO Auto-generated constructor stub
		setSize(new Dimension(700, 350));
		setVisible(true);
		
		JTabbedPane pane=new JTabbedPane();
		pane.add("Tab 1", makePanel("This is tab 1"));
		pane.addTab("Tab 2", makePanel("This is tab 2"));
		pane.add("Tab 3", makePanel("This is tab 3"));
		add(pane);
		}
	
	
		private static JPanel makePanel(String text) {
		JPanel p=new JPanel();
		p.add(new JLabel(text));
		p.setLayout(new GridLayout(1, 1));
		
		return p;
	}
	}

