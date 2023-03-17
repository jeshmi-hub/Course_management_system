package GUIApplication;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;

public class ModulePanel extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel;
    JPanel modulePanel;
    JPanel secondPanel, thirdPanel;
    // Reference variable for different fields, buttons, table and table model
    DefaultTableModel model, model1;
    JTable table, table1;
    JTextField courseName, module1, module2, module3, module4;
    JComboBox<String> semester, level, course;
    JButton btnAddCourse, btnUpdateCourse, btnDeleteCourse, btnCancelCourse, btnAddModule, btnUpdateModule, btnDeleteModule,btnClearModule;
    JLabel moduleName3, moduleName4;
    private int userId;

    /**
     * Constructor of the class
     */
    public ModulePanel(int id) {
        this.userId = id;
        setTitle("Module and course");
        setVisible(true);
//        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(3, 1));
        JPanel subPanel = new JPanel(new GridLayout(1, 2));
        subPanel.add(dataUI());
        subPanel.add(buttonUI());
        add(subPanel);
        add(secondUI());
        add(thirdUI());
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is used for creating menus in the GUI.
     * You must call this method in constructor inside setJMenuBar() to display in menu in the frame.
     *
     * @return list of menus
     */
    private JMenuBar getMenu() {
        menuBar = new JMenuBar();

        JMenu course  = new JMenu("Course");
        menuBar.add(course);

        JMenuItem addCourse = new JMenuItem("Add Course");
        course.add(addCourse);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JMenu module = new JMenu("Module");
        menuBar.add(module);


        JMenuItem addModule = new JMenuItem("Add Module");
        module.add(addModule);
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JMenu details = new JMenu("Instructor");
        menuBar.add(details);

        JMenuItem addDetails = new JMenuItem("Add Instructor");
        details.add(addDetails);
        addDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddInstructorPanel();
            }
        });

        JMenuItem viewDetails = new JMenuItem("View Instructor");
        details.add(viewDetails);
        viewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructorPanel();
            }
        });

        JMenu student = new JMenu("Student");
        menuBar.add(student);

        JMenuItem addStudent = new JMenuItem("Enter Remarks");
        student.add(addStudent);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remarks();
            }
        });


        JMenuItem viewMarks = new JMenuItem("View Marks");
        student.add(viewMarks);
        viewMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewRemarks();
            }
        });


        return menuBar;
    }

    /**
     * This method defines interface for data entry section
     *
     * @return panel for data entry section
     */
    private JPanel dataUI() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Course"));
        btnAddCourse = new JButton("Add Course");
        btnUpdateCourse = new JButton("Update Course");
        btnDeleteCourse = new JButton("Delete Course");
        btnCancelCourse = new JButton("Clear Course");

        CourseRegistration courseRegistration = new CourseRegistration();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add(new JLabel("Course Name"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;

        courseName = new JTextField(20);
        dataPanel.add(courseName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add(btnAddCourse, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        dataPanel.add(btnDeleteCourse, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        dataPanel.add(btnUpdateCourse, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        dataPanel.add(btnCancelCourse, gbc);


        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = courseName.getText().trim();

                if (course.isEmpty()) {
                    JOptionPane.showMessageDialog(dataPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    courseRegistration.insert(course,userId);
                    refreshTable();
                    JOptionPane.showMessageDialog(dataPanel, "New record is added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    btnCancelCourse.doClick();
                }
            }
        });

        btnDeleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String row = JOptionPane.showInputDialog(dataPanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                        courseRegistration.delete(rowDelete);
                        refreshTable();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        btnUpdateCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(secondPanel, "You must select a row to update a field", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String course = courseName.getText().trim();
                    if (course.isEmpty()) {
                        JOptionPane.showMessageDialog(secondPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                        courseRegistration.update(id, course);
                        refreshTable();
                        JOptionPane.showMessageDialog(secondPanel, "Data of " + course + " is updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        btnCancelCourse.doClick();
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(secondPanel, "You must enter age in numerical format", "Error", JOptionPane.ERROR_MESSAGE);

                }


            }
        });

        btnCancelCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseName.setText("");
                table.clearSelection();
            }
        });


        return dataPanel;
    }

    /**
     * This method defines interface for button functionality section
     *
     * @return panel for button functionality section
     */
    private JPanel buttonUI() {
        modulePanel = new JPanel();
        modulePanel.setLayout(new GridBagLayout());
        modulePanel.setBorder(BorderFactory.createTitledBorder("Module"));
        btnAddModule = new JButton("Add Module");
        btnUpdateModule = new JButton("Update Module");
        btnDeleteModule = new JButton("Delete Module");
        btnClearModule = new JButton("Clear Module");
        module1 = new JTextField(20);
        module2 = new JTextField(20);
        module3 = new JTextField(20);
        module4 = new JTextField(20);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        semester = new JComboBox<>(new String[]{"semester 1", "semester 2"});
        level = new JComboBox<>(new String[]{"level 4", "level 5", "level 6"});
        ArrayList<String> ar = new ArrayList<String>();
        try {
            String url = "jdbc:mysql://localhost:3306/course";
            String username = "root";
            String password = "";
            Connection con = DriverManager.getConnection(url,username,password);
            Statement st;
            st = con.createStatement();
            String query = "SELECT courseName FROM course";
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()) {
                ar.add(rs.getString("courseName"));
            }
        } catch(Exception throwables) {
            throwables.printStackTrace();
        }
        course = new JComboBox(ar.toArray());
        moduleName3 = new JLabel("Module 3");
        moduleName4 = new JLabel("Module 4");

        ModuleRegistration moduleRegistration = new ModuleRegistration();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        modulePanel.add(new JLabel("Course"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        modulePanel.add(course, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        modulePanel.add(new JLabel("Semester"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        modulePanel.add(semester, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        modulePanel.add(new JLabel("Level"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        modulePanel.add(level, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        modulePanel.add(new JLabel("Module 1"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        modulePanel.add(module1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        modulePanel.add(new JLabel("Module 2"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        modulePanel.add(module2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        modulePanel.add(moduleName3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        modulePanel.add(module3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        modulePanel.add(moduleName4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        modulePanel.add(module4, gbc);



        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        modulePanel.add(btnAddModule, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        modulePanel.add(btnDeleteModule, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        modulePanel.add(btnUpdateModule, gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        modulePanel.add(btnClearModule,gbc);

        level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (level.getSelectedItem() == "level 6") {
                    module3.setVisible(false);
                    module4.setVisible(false);
                    moduleName3.setVisible(false);
                    moduleName4.setVisible(false);

                } else {
                    moduleName3.setVisible(true);
                    moduleName4.setVisible(true);
                    module3.setVisible(true);
                    module4.setVisible(true);
                    JOptionPane.showMessageDialog(modulePanel, "All the modules must be filled other levels", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        btnAddModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String courseName = course.getSelectedItem().toString();
                    String semesterNo = semester.getSelectedItem().toString();
                    String levelNo = level.getSelectedItem().toString();
                    String module1Name = module1.getText().trim();
                    String module2Name = module2.getText().trim();
                    String module3Name = module3.getText().trim();
                    String module4Name = module4.getText().trim();

                    if (levelNo.equals("level 6")) {
                        if (module1Name.isEmpty() || module2Name.isEmpty()) {
                            JOptionPane.showMessageDialog(modulePanel, "Two mandatory modules must be entered", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            moduleRegistration.insert(courseName, semesterNo, levelNo, module1Name, module2Name, module3Name, module4Name);
                            refreshTableModule();
                            JOptionPane.showMessageDialog(modulePanel, "Modules has been added for level 6", "Success", JOptionPane.INFORMATION_MESSAGE);
                            btnClearModule.doClick();
                        }

                    } else {
                        moduleRegistration.insert(courseName, semesterNo, levelNo, module1Name, module2Name, module3Name, module4Name);
                        refreshTableModule();
                        JOptionPane.showMessageDialog(modulePanel, "Modules has been added for level4 and level 5", "Success", JOptionPane.INFORMATION_MESSAGE);
                        btnClearModule.doClick();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(modulePanel, "You must enter age in numerical format", "Error", JOptionPane.ERROR_MESSAGE);


                }
            }
        });



        btnDeleteModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String row = JOptionPane.showInputDialog(modulePanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(modulePanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                        moduleRegistration.delete(rowDelete);
                        refreshTableModule();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(modulePanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(modulePanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        btnUpdateModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table1.getSelectedRow();
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(modulePanel, "You must select a row to update a field","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                        String courseName = course.getSelectedItem().toString();
                        String semesterNo = semester.getSelectedItem().toString();
                        String levelNo = level.getSelectedItem().toString();
                        String module1Name = module1.getText().trim();
                        String module2Name = module2.getText().trim();
                        String module3Name = module3.getText().trim();
                        String module4Name = module4.getText().trim();
                        if (module1Name.isEmpty()||module2Name.isEmpty()) {
                            JOptionPane.showMessageDialog(secondPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int id = Integer.parseInt(model1.getValueAt(selectedRow, 0).toString());
                            moduleRegistration.update(id, courseName,semesterNo,levelNo,module1Name,module2Name,module3Name,module4Name);
                            refreshTableModule();
                            JOptionPane.showMessageDialog(secondPanel, "Data of " + courseName + " is updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            btnClearModule.doClick();
                        }

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(secondPanel, "You must enter age in numerical format", "Error", JOptionPane.ERROR_MESSAGE);

                    }

            }
        });

        btnClearModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                module1.setText("");
                module2.setText("");
                module3.setText("");
                module4.setText("");
                table1.clearSelection();
            }
        });


        return modulePanel;
    }

    /**
     * This method defines interface for list of data section
     * and event listener for row selection
     *
     * @return panel for list of data
     */
    private JPanel secondUI() {
        secondPanel = new JPanel(new BorderLayout());
        secondPanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO", "Course"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        secondPanel.add(scrollPane);
        CourseRegistration courseRegistration = new CourseRegistration();
        model.setRowCount(0);
        try {
            ResultSet resultSet = courseRegistration.get();
            while ((resultSet.next())) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("courseName"),
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // added event listener for row selection inside JTable
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                System.out.println(selectedRow);
                courseName.setText(model.getValueAt(selectedRow, 1).toString());
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

        return secondPanel;
    }

    private JPanel thirdUI() {
        thirdPanel = new JPanel(new BorderLayout());
        thirdPanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(new String[]{"S.NO", "Course", "Semester", "Level", "Module 1", "Module 2", "Module 3", "Module 4"});
        table1 = new JTable(model1);
        JScrollPane scrollPane = new JScrollPane(table1);
        thirdPanel.add(scrollPane);
        ModuleRegistration moduleRegistration = new ModuleRegistration();
        model1.setRowCount(0);
        try {
            ResultSet resultSet = moduleRegistration.get();
            while ((resultSet.next())) {
                model1.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("course"),
                        resultSet.getString("semester"),
                        resultSet.getString("level"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4"),
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();
                course.setSelectedItem(model1.getValueAt(selectedRow,1).toString());
                semester.setSelectedItem(model1.getValueAt(selectedRow,2).toString());
                level.setSelectedItem(model1.getValueAt(selectedRow,3).toString());
                module1.setText(model1.getValueAt(selectedRow,4).toString());
                module2.setText(model1.getValueAt(selectedRow,5).toString());
                module3.setText(model1.getValueAt(selectedRow,6).toString());
                module4.setText(model1.getValueAt(selectedRow,7).toString());

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


        // added event listener for row selection inside JTable

        return thirdPanel;
    }

    private void refreshTable() {
        CourseRegistration courseRegistration = new CourseRegistration();
        model.setRowCount(0);
        try {
            ResultSet resultSet = courseRegistration.get();
            while ((resultSet.next())) {
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("courseName"),
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void refreshTableModule() {
        ModuleRegistration moduleRegistration = new ModuleRegistration();
        model1.setRowCount(0);
        try {
            ResultSet resultSet = moduleRegistration.get();
            while ((resultSet.next())) {
                model1.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("course"),
                        resultSet.getString("semester"),
                        resultSet.getString("level"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4"),
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




        /**
         * Entry point of the application
         *
         * @param args
         */

    }

