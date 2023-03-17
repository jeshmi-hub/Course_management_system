package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStudent extends JFrame {
    JMenuBar menuBar;
    JPanel studentPanel;
    // Reference variable for different fields, buttons, table and table model
    DefaultTableModel model;
    JTable table;
    ViewStudent(){
        setTitle("Students");
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

    private JMenuBar getMenu(){
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Student");
        JMenu editMenu = new JMenu("Teacher");
        JMenu viewMenu = new JMenu("Marks");

        // creating sub menu
        JMenuItem student = new JMenuItem("View Students");
        fileMenu.add(student);

        JMenuItem teacher = new JMenuItem("Assigned Teachers");
        editMenu.add(teacher);

        JMenuItem marks = new JMenuItem("Add Marks");
        viewMenu.add(marks);

        student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudent();
            }
        });

        teacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructorPanel();
            }
        });

        marks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructorPanel();
            }
        });


        // top level menu added inside menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        return  menuBar;
    }

    private JPanel thirdUI() {
        studentPanel = new JPanel(new BorderLayout());
        studentPanel.setBorder(BorderFactory.createTitledBorder("List of Students"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO", "FirstName", "LastName", "Course","Level","Semester","Module1", "Module2","Module3","Module4","Optional1","Optional2"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        studentPanel.add(scrollPane);
        EnrollRegistration enrollRegistration = new EnrollRegistration();
        model.setRowCount(0);
        try {
            ResultSet resultSet = enrollRegistration.get2();
            while ((resultSet.next())) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("course"),
                        resultSet.getString("levelNo"),
                        resultSet.getString("semNo"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4"),
                        resultSet.getString("optional1"),
                        resultSet.getString("optional2")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return  studentPanel;
    }


}
