package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JPanel implements AppLayout{
    private DefaultTableModel model;
    private JTable table;

    public DataPanel(){
        model = new DefaultTableModel();
        String[] headers = {"S.No.","First Name", "Last Name", "Username", "Password", "Age","Date of Birth", "Gender", "Course", "Role"};
        model.setColumnIdentifiers(headers);
        table = new JTable(model);
        table.setSelectionBackground(Color.GRAY);
    }
   @Override
    public JPanel panelUI(){
        setBorder(BorderFactory.createTitledBorder("Registration Data"));
        setBackground(new Color(0xFFE2C9C9, true));
        table.setShowGrid(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        return this;
    }

    public DefaultTableModel getModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JTable getTable() {
        return table;
    }

}
