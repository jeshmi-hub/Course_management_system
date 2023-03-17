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

public class AddInstructorPanel extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel;
    DefaultTableModel model;
    JTable table;
    JTextField name,module1,module2,module3,module4;
    JComboBox<String> course;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;



    AddInstructorPanel(){
        setTitle("Add Instructor");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(1,1));
        JPanel subPanel = new JPanel(new GridLayout(1,1));
        subPanel.add(dataUI());
        subPanel.add(Table());
        add(subPanel);

        pack();
        setLocationRelativeTo(null);

    }

    private JMenuBar getMenu(){
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        // creating sub menu
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem marksItem = new JMenuItem("View Students Marks");
        JMenuItem exitItem = new JMenuItem("Exit");

        // sub menu added inside file menu
        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // top level menu added inside menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        return menuBar;
    }

    private JPanel dataUI(){
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Data Entry"));
        ModuleAssignedRegistration moduleAssignedRegistration= new ModuleAssignedRegistration();
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        dataPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        name = new JTextField(20);
        dataPanel.add(name,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        dataPanel.add(new JLabel("Course"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        dataPanel.add(course,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Module 1"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        module1 = new JTextField(20);
        dataPanel.add(module1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Module 2"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        module2 = new JTextField(20);
        dataPanel.add(module2,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dataPanel.add(new JLabel("Module 3"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        module3 = new JTextField(20);
        dataPanel.add(module3,gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Module 4"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        module4 = new JTextField(20);
        dataPanel.add(module4,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth=1;
        btnAdd = new JButton("Add Instructor");
        dataPanel.add(btnAdd,gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        btnUpdate =new JButton("Update Instructor");
        dataPanel.add(btnUpdate,gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        btnDelete = new JButton("Delete Instructor");
        dataPanel.add(btnDelete,gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        btnClear = new JButton("Clear Instructor");
        dataPanel.add(btnClear,gbc);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String instructor = name.getText().trim();
                    String courseName = course.getSelectedItem().toString();
                    String module1Name = module1.getText().trim();
                    String module2Name = module2.getText().trim();
                    String module3Name = module3.getText().trim();
                    String module4Name =  module4.getText().trim();
                    if(instructor.isEmpty() || module1Name.isEmpty() || module2Name.isEmpty() || module3Name.isEmpty() || module4Name.isEmpty()){
                        JOptionPane.showMessageDialog(dataPanel, "Two mandatory modules must be entered", "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        moduleAssignedRegistration.insert(instructor,courseName,module1Name,module2Name,module3Name,module4Name);
                        refreshTable();
                        JOptionPane.showMessageDialog(dataPanel, "New record is added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        btnClear.doClick();
                    }

                }catch(Exception ex){

                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(dataPanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                        moduleAssignedRegistration.delete(rowDelete);
                        refreshTable();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(dataPanel, "You must select a row to update a field", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    String instructor = name.getText().trim();
                    String courseName = course.getSelectedItem().toString();
                    String module1Name = module1.getText().trim();
                    String module2Name = module2.getText().trim();
                    String module3Name = module3.getText().trim();
                    String module4Name = module4.getText().trim();
                    if(instructor.isEmpty()|| module1Name.isEmpty()|| module2Name.isEmpty() || module3Name.isEmpty() || module4Name.isEmpty()){
                        JOptionPane.showMessageDialog(dataPanel, "Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        int id = Integer.parseInt(model.getValueAt(selectedRow,0).toString());
                        moduleAssignedRegistration.update(id,instructor,courseName,module1Name,module2Name,module3Name,module4Name);
                        refreshTable();
                        JOptionPane.showMessageDialog(dataPanel, "Data of " + instructor + " is updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        btnClear.doClick();
                    }

                }catch(ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(dataPanel, "Array is out of bound", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                module1.setText("");
                module2.setText("");
                module3.setText("");
                module4.setText("");
                table.clearSelection();
            }
        });



        return  dataPanel;
    }

    private JPanel Table(){
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Modules Assigned to Instructor"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO","Name","Course", "Module1", "Module2","Module3", "Module4"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        ModuleAssignedRegistration moduleAssignedRegistration = new ModuleAssignedRegistration();
        model.setRowCount(0);
        try{
            ResultSet resultSet = moduleAssignedRegistration.get();
            while ((resultSet.next())){
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
        }catch(SQLException e){
            e.printStackTrace();
        }

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                name.setText(model.getValueAt(selectedRow,1).toString());
                course.setSelectedItem(model.getValueAt(selectedRow,2).toString());
                module1.setText(model.getValueAt(selectedRow,3).toString());
                module2.setText(model.getValueAt(selectedRow, 4).toString());
                module3.setText(model.getValueAt(selectedRow, 5).toString());
                module4.setText(model.getValueAt(selectedRow, 6).toString());
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


        return  tablePanel;
    }

    private void refreshTable(){
        ModuleAssignedRegistration moduleAssignedRegistration = new ModuleAssignedRegistration();
        model.setRowCount(0);
        try {
            ResultSet resultSet = moduleAssignedRegistration.get();
            while ((resultSet.next())){
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("course"),
                        resultSet.getString("module1"),
                        resultSet.getString("module2"),
                        resultSet.getString("module3"),
                        resultSet.getString("module4")
                });

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
