package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import main.AppCore;


public class MainView extends JFrame{
	
	private static MainView instance=null;
	
	private AppCore appCore;
	private ToolBar toolbar;
	private DesnoGore desnoGore;
	private DesnoDole desnoDole;
	
	
	private MainView() {
		
	}
	
	
	private void initialize() {
		initializeGUI();
	}


	private void initializeGUI() {
		setTitle("DB projekat");
		setSize(1000, 800);
		setLocationRelativeTo(null);
        setLayout(new BorderLayout());
		setVisible(true);
		setResizable(false);
		toolbar = new ToolBar();
		add(toolbar, BorderLayout.NORTH);
		
		JScrollPane scrollTree = new JScrollPane();
		scrollTree.setMinimumSize(new Dimension(250,750));
		JPanel desno = new JPanel(new BorderLayout());
		JScrollPane scrollDesno = new JScrollPane();
		scrollDesno.setPreferredSize(new Dimension(700, 350));
		desnoDole = DesnoDole.getInstance();
		desnoDole.setPreferredSize(new Dimension(700, 350));
		JSplitPane splitVer=new JSplitPane(JSplitPane.VERTICAL_SPLIT,scrollDesno,desnoDole);
		desno.add(splitVer,BorderLayout.SOUTH);
		JSplitPane splitHor=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollTree,desno);
		add(splitHor,BorderLayout.CENTER);
		
	}
	
	public static MainView getinstance() {
		if(instance==null) {
			instance=new MainView();
		    instance.initialize();
		}
		return instance;
	}
	
	public AppCore getAppCore() {
		return appCore;
	}
	public void setAppCore(AppCore appCore) {
		this.appCore = appCore;
	}
}
