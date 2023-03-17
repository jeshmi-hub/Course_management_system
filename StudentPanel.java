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

public class StudentPanel extends JFrame {
    private int id;
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel, modulePanel;
    DefaultTableModel model, model1;
    JTable table, table1;
    JButton btnViewInstructor, btnViewResult, btnEnroll;
    JComboBox<String> level, semester, choose1, choose2;
    JLabel module1,module2,module13,module14;
    JTextField getModule1,getModule2,getModule3,getModule4;
    JLabel choose, module3, module4;


    StudentPanel(int id){
        this.id = id;
        setTitle("Student Panel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(3, 1));
        JPanel subPanel = new JPanel(new GridLayout(1, 2));
        subPanel.add(dataUI());

        add(subPanel);
        add(secondUI());
        add(thirdUI());
        pack();
        setLocationRelativeTo(null);
    }

    private JMenuBar getMenu(){
        menuBar = new JMenuBar();
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
        return  menuBar;
    }



    private JPanel dataUI(){
        dataPanel = new JPanel();
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Enroll"));
        level = new JComboBox<>(new String[]{"level 4", "level 5", "level 6"});
        semester = new JComboBox<>(new String[]{"semester 1", "semester 2"});
        choose1 = new JComboBox<>(new String[]{" ","Android Development", "Web Development", "Animation", "Videography"});
        choose2 = new JComboBox<>(new String[]{" ","Data Science", "Data Analytics", "Networking", "Cyber Security"});
        EnrollRegistration enrollRegistration = new EnrollRegistration();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Level"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(level,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Semester"),gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(semester,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        module1 = new JLabel("Module1:");
        dataPanel.add(module1,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        getModule1 = new JTextField(20);
        dataPanel.add(getModule1,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        module2 = new JLabel("Module2:");
        dataPanel.add(module2,gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        getModule2 = new JTextField(20);
        dataPanel.add(getModule2,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        module13 = new JLabel("Module3:");
        dataPanel.add(module13,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        getModule3 = new JTextField(20);
        dataPanel.add(getModule3,gbc);


        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        module14 = new JLabel("Module4:");
        dataPanel.add(module14,gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        getModule4 = new JTextField(20);
        dataPanel.add(getModule4,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        choose = new JLabel("Choose two modules:");
        dataPanel.add(choose,gbc);
        choose.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        module3 = new JLabel("Module 3:");
        dataPanel.add(module3,gbc);
        module3.setVisible(false);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        dataPanel.add(choose1,gbc);
        choose1.setVisible(false);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        module4 = new JLabel("Module 4:");
        dataPanel.add(module4,gbc);
        module4.setVisible(false);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        dataPanel.add(choose2,gbc);
        choose2.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        btnEnroll = new JButton("Enroll");
        dataPanel.add(btnEnroll,gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        btnViewInstructor = new JButton("View Instructor");
        dataPanel.add(btnViewInstructor,gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        btnViewResult = new JButton("View Result");
        dataPanel.add(btnViewResult, gbc);




       level.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(level.getSelectedItem()=="level 6"){
                   choose.setVisible(true);
                   choose1.setVisible(true);
                   choose2.setVisible(true);
                   module4.setVisible(true);
                   module3.setVisible(true);
                   getModule3.setVisible(false);
                   getModule4.setVisible(false);
               }else{
                   choose.setVisible(false);
                   choose1.setVisible(false);
                   choose2.setVisible(false);
                   module3.setVisible(false);
                   module4.setVisible(false);
                   getModule3.setVisible(true);
                   getModule4.setVisible(true);

               }
           }
       });

       btnEnroll.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String levelNo = level.getSelectedItem().toString();
               String semNo = semester.getSelectedItem().toString();
               String module1 = getModule1.getText().trim();
               String module2 = getModule2.getText().trim();
               String module3 =  getModule3.getText().trim();
               String module4 = getModule4.getText().trim();
               String optional1 = choose1.getSelectedItem().toString();
               String optional2 = choose2.getSelectedItem().toString();

               enrollRegistration.insert(id,levelNo,semNo,module1,module2,module3,module4,optional1,optional2);
               refreshTableModule();
               JOptionPane.showMessageDialog(dataPanel,"You are enrolled","Information", JOptionPane.INFORMATION_MESSAGE);
           }
       });




        return dataPanel;
    }

    private JPanel secondUI(){
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO", "FirstName", "LastName", "Course","Level","Semester","Module1", "Module2","Module3","Module4","Optional1","Optional2"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        EnrollRegistration enrollRegistration = new EnrollRegistration();
        model.setRowCount(0);
        try{
            ResultSet  resultSet = enrollRegistration.get2();
            while ((resultSet.next())){
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
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return tablePanel;
    }

    private JPanel thirdUI(){
        modulePanel = new JPanel(new BorderLayout());
        modulePanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(new String[]{"Level", "Semester", "Module 1", "Module 2", "Module 3", "Module 4"});
        table1 = new JTable(model1);
        JScrollPane scrollPane = new JScrollPane(table1);
        modulePanel.add(scrollPane);
        EnrollRegistration enrollRegistration = new EnrollRegistration();
        model1.setRowCount(0);
        try{
            ResultSet resultSet = enrollRegistration.get1();
            while ((resultSet.next())){
                model1.addRow(new Object[]{
                        resultSet.getString("level"),
                        resultSet.getString("semester"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4")
                });
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();
                level.setSelectedItem(model1.getValueAt(selectedRow,0).toString());
                semester.setSelectedItem(model1.getValueAt(selectedRow,1).toString());
                getModule1.setText(model1.getValueAt(selectedRow,2).toString());
                getModule2.setText(model1.getValueAt(selectedRow,3).toString());
                getModule3.setText(model1.getValueAt(selectedRow,4).toString());
                getModule4.setText(model1.getValueAt(selectedRow,5).toString());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        return modulePanel;

    }

    private void refreshTableModule(){
        EnrollRegistration enrollRegistration = new EnrollRegistration();
        model.setRowCount(0);
        try{
            ResultSet resultSet = enrollRegistration.get2();
            while ((resultSet.next())){
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

        }catch (SQLException e){

        }
    }






}
