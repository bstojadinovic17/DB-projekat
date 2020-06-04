package gui;

import db.SQLrepositoryImpl;
import gui.table.TableModel;
import model.categories.Table;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TabDole extends JTabbedPane implements Observer {

    private static TabDole instance = null;
    private JTabbedPane tabbedPane;
    private static ArrayList<Table> tabele;
    private JButton closeButton;

    private TabDole() {
        // TODO Auto-generated constructor stub

        tabele = new ArrayList<>();
        tabbedPane = new JTabbedPane();
        tabbedPane.setSize(new Dimension(700, 350));
        tabbedPane.setVisible(true);
    }



    public static TabDole getInstance() {
        if (instance == null) instance = new TabDole();
        return instance;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static ArrayList<Table> getTabele() {
        return tabele;
    }

    @Override
    public void update(Object o, List<String> columnNames, List<String> values, int where) {
        TabDole.getInstance().getTabbedPane().removeAll();
        if(where == 1){
            System.out.println(o.toString() + " " + where);
            for (int i = 0; i<columnNames.size(); i++){
                System.out.println(columnNames.get(i) + " " + values.get(i));

            }
        }else{
            System.out.println(MainView.getinstance().getAppCore().getDatabase().getTableModelFromRow(o.toString()));
            for(String s : MainView.getinstance().getAppCore().getDatabase().getTableModelFromRow(o.toString())){
                JTable tabela = new JTable();
                TableModel model = new TableModel();
                model.setRows(MainView.getinstance().getAppCore().getDatabase().readDataFromTable(s));
                tabela.setModel(model);
                tabela.setRowSelectionAllowed(true);
                InTabPanel panel = new InTabPanel(tabela);
                tabbedPane.addTab(s, panel);
                tabbedPane.setFocusable(true);
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
            }

           // System.out.println(o.toString());
           // System.out.println(where);
        }
        //System.out.println("----------------------------------------");

    }

}