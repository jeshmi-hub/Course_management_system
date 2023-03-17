package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRemarks extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel, buttonPanel, marksPanel, remarksPanel;
    DefaultTableModel model, model1;
    JTable table, table1;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    JLabel total,avg;
    JTextField totalMarks, avgMarks;
    ViewRemarks(){
        setTitle("Remarks Panel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(1, 1));
        add(marksUI());
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

    private JPanel marksUI(){
        marksPanel = new JPanel(new BorderLayout());
        marksPanel.setBorder(BorderFactory.createTitledBorder("Marks Entry"));
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(new String[]{"Student Id", "FirstName", "LastName", "Course","Level","Semester","Module1Name","Module1Marks" ,"Module2Name","Module2Marks","Module3Name","Module3Marks","Module4Name", "Module4Marks","Optional1Name","Optional1Marks","Optional2Name","Optional2Marks"});
        table1 = new JTable(model1);
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        marksPanel.add(scrollPane);
        MarksRegistration marksRegistration = new MarksRegistration();
        model1.setRowCount(0);
        try{
            ResultSet resultSet = marksRegistration.get();
            while ((resultSet.next())){
                model1.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("course"),
                        resultSet.getString("level"),
                        resultSet.getString("semester"),
                        resultSet.getString("moduleName1"),
                        resultSet.getInt("module1Marks"),
                        resultSet.getString("moduleName2"),
                        resultSet.getInt("module2Marks"),
                        resultSet.getString("moduleName3"),
                        resultSet.getInt("module3Marks"),
                        resultSet.getString("moduleName4"),
                        resultSet.getInt("module4Marks"),
                        resultSet.getString("optionalName1"),
                        resultSet.getInt("optional1Marks"),
                        resultSet.getString("optionalName2"),
                        resultSet.getInt("optional2Marks")
                });
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }



        return  marksPanel;
    }




}
