package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ViewInstructor extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel;
    JPanel modulePanel;
    JPanel secondPanel, thirdPanel;
    // Reference variable for different fields, buttons, table and table model
    DefaultTableModel model;
    JTable table;

    public ViewInstructor(){
        setTitle("Instructors");
        setVisible(true);
//        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(1, 1));
        add(thirdUI());
        pack();
        setLocationRelativeTo(null);
    }

    private  JMenuBar getMenu(){
        menuBar = new JMenuBar();

        // define top level menu
        JMenu fileMenu = new JMenu("Student");
        JMenu editMenu = new JMenu("Teacher");
        JMenu viewMenu = new JMenu("View");

        // creating sub menu
        JMenuItem newItem = new JMenuItem("View Instructor");
        JMenuItem exitItem = new JMenuItem("View Result");
        JMenuItem enroll = new JMenuItem("Home");

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructor();
            }
        });

        enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentHome();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewRemarks();
            }
        });
        // sub menu added inside file menu
        editMenu.add(newItem);
        viewMenu.add(exitItem);
        fileMenu.add(enroll);


        // top level menu added inside menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    private JPanel thirdUI() {
        thirdPanel = new JPanel(new BorderLayout());
        thirdPanel.setBorder(BorderFactory.createTitledBorder("List of Teachers"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO", "Name of Instructor",  "Module 1", "Module 2", "Module 3", "Module 4"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        thirdPanel.add(scrollPane);
        ModuleAssignedRegistration moduleAssignedRegistration = new ModuleAssignedRegistration();
        model.setRowCount(0);
        try {
            ResultSet resultSet = moduleAssignedRegistration.get();
            while ((resultSet.next())) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("course"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4"),
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

     return  thirdPanel;
    }


}
