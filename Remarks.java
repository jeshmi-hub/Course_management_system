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

public class Remarks extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel, buttonPanel, marksPanel, remarksPanel;
    DefaultTableModel model, model1;
    JTable table, table1;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    JLabel module1;
    JLabel module2;
    JLabel module3;
    JLabel module4;
    JLabel optional;
    JLabel optional2;
    JLabel level;
    JLabel semester;
    JLabel course;
    JLabel first_name;
    JLabel last_name;
    JLabel total,avg;

    JTextField getModule1,getModule2,getModule3,getModule4, getOptional1, getOptional2, id;
    MarksRegistration marksRegistration = new MarksRegistration();
    Remarks(){
        setTitle("View Marks");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(3, 2));
        JPanel subPanel = new JPanel(new GridLayout(1, 2));
        subPanel.add(dataUI());
        subPanel.add(buttonUI());
        add(subPanel);
        add(marksUI());
        add(remarksUI());
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

    private JPanel dataUI(){
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Mark Students"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("id:"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        id = new JTextField(20);
        dataPanel.add(id,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        first_name = new JLabel("");
        dataPanel.add(first_name, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        last_name = new JLabel("");
        dataPanel.add(last_name, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        dataPanel.add(new JLabel("Course:"),gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        course = new JLabel("");
        dataPanel.add(course,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        dataPanel.add(new JLabel("Semester:"),gbc);
        gbc.gridx =1;
        gbc.gridy = 1;
        semester = new JLabel("");
        dataPanel.add(semester,gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("level:"),gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        level = new JLabel("");
        dataPanel.add(level,gbc);


        gbc.gridx = 0;
        gbc.gridy=2;
        gbc.gridwidth =1;
        module1 = new JLabel("");
        dataPanel.add(module1,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth =3;
        getModule1 = new JTextField(20);
        dataPanel.add(getModule1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        module2 = new JLabel("");
        dataPanel.add(module2,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        getModule2 = new JTextField(20);
        dataPanel.add(getModule2,gbc);

        gbc.gridx= 0;
        gbc.gridy = 4;
        gbc.gridwidth =1 ;
        module3 = new JLabel("");
        dataPanel.add(module3,gbc);
        gbc.gridx =1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        getModule3 = new JTextField(20);
        dataPanel.add(getModule3,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        module4 = new JLabel("");
        dataPanel.add(module4,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        getModule4 = new JTextField(20);
        dataPanel.add(getModule4,gbc);

        gbc.gridx =0;
        gbc.gridy=6;
        gbc.gridwidth =1;
        optional = new JLabel("");
        dataPanel.add(optional,gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth=1;
        getOptional1 = new JTextField(20);
        dataPanel.add(getOptional1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth=1;
        optional2 = new JLabel("");
        dataPanel.add(optional2,gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth =1;
        getOptional2 = new JTextField(20);
        dataPanel.add(getOptional2, gbc);

        gbc.gridx = 5;
        gbc.gridy = 6;
        dataPanel.add(new JLabel("Total:"),gbc);
        gbc.gridx=6;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        total = new JLabel("");
        dataPanel.add(total,gbc);

        gbc.gridx = 5;
        gbc.gridy = 7;
        dataPanel.add(new JLabel("Average:"),gbc);
        gbc.gridx= 6;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        avg = new JLabel("");
        dataPanel.add(avg,gbc);



        return dataPanel;
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

        table1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();
                module1.setText(model1.getValueAt(selectedRow,6).toString());
                getModule1.setText(model1.getValueAt(selectedRow,7).toString());
                module2.setText(model1.getValueAt(selectedRow,8).toString());
                getModule2.setText(model1.getValueAt(selectedRow,9).toString());
                module3.setText(model1.getValueAt(selectedRow,10).toString());
                getModule3.setText(model1.getValueAt(selectedRow,11).toString());
                module4.setText(model1.getValueAt(selectedRow,12).toString());
                getModule4.setText(model1.getValueAt(selectedRow, 13).toString());
                optional.setText(model1.getValueAt(selectedRow,14).toString());
                getOptional1.setText(model1.getValueAt(selectedRow,15).toString());
                optional2.setText(model1.getValueAt(selectedRow,16).toString());
                getOptional2.setText(model1.getValueAt(selectedRow,17).toString());
                level.setText(model1.getValueAt(selectedRow,4).toString());
                semester.setText(model1.getValueAt(selectedRow,5).toString());
                id.setText(model1.getValueAt(selectedRow,0).toString());
                first_name.setText(model1.getValueAt(selectedRow,1).toString());
                last_name.setText(model1.getValueAt(selectedRow,2).toString());
                course.setText(model1.getValueAt(selectedRow,3).toString());
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


        return  marksPanel;
    }


    private JPanel buttonUI(){
        buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Buttons Functionality"));
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Give Remarks");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        RemarksRegistration remarksRegistration = new RemarksRegistration();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idNo = Integer.parseInt(id.getText().trim());
                String moduleName1 = module1.getText();
                int moduleMarks1 = Integer.parseInt(getModule1.getText().trim());
                String moduleName2 = module2.getText();
                int moduleMarks2 = Integer.parseInt(getModule2.getText().trim());
                String moduleName3 = module3.getText();
                int moduleMarks3 = Integer.parseInt(getModule3.getText().trim());
                String moduleName4 = module4.getText();
                int moduleMarks4 = Integer.parseInt(getModule4.getText().trim());
                String optionalName1 = optional.getText();
                int optionalMarks1 = Integer.parseInt(getOptional1.getText().trim());
                String optionalName2 = optional2.getText();
                int optionalMarks2 = Integer.parseInt(getOptional2.getText().trim());
                String levelNo = level.getText();
                String semesterNo = semester.getText();
                String firstName = first_name.getText();
                String lastName = last_name.getText();
                String courseName = course.getText();
                int totalMarks = moduleMarks1+moduleMarks2+moduleMarks3+moduleMarks4+optionalMarks1+optionalMarks2;
                int average = totalMarks/4;
                total.setText(String.valueOf(moduleMarks1+moduleMarks2+moduleMarks3+moduleMarks4+optionalMarks1+optionalMarks2));
                avg.setText(String.valueOf((moduleMarks1+moduleMarks2+moduleMarks3+moduleMarks4+optionalMarks1+optionalMarks2)/4));
                remarksRegistration.insert(idNo,firstName,lastName,courseName,levelNo,semesterNo,moduleName1,moduleMarks1,moduleName2,moduleMarks2,moduleName3,moduleMarks3,moduleName4,moduleMarks4,optionalName1,optionalMarks1,optionalName2,optionalMarks2,totalMarks,average);
                refreshTable();
                btnClear.doClick();
                JOptionPane.showMessageDialog(dataPanel,firstName+" 's marks is added","Information", JOptionPane.INFORMATION_MESSAGE);
            }


        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(marksPanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(marksPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirm == JOptionPane.YES_NO_OPTION){
                    try{
                        int rowDelete = Integer.parseInt(row);
                        marksRegistration.delete(rowDelete);

                        btnClear.doClick();
                    }catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(marksPanel, "You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }catch (ArrayIndexOutOfBoundsException exception){
                        JOptionPane.showMessageDialog(marksPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GiveRemarks();
                refreshRemarksTable();
            }
        });



        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                module1.setText("");
                getModule1.setText("");
                module2.setText("");
                getModule2.setText("");
                module3.setText("");
                getModule3.setText("");
                module4.setText("");
                getModule4.setText("");
                level.setText("");
                id.setText("");
                course.setText("");
                semester.setText("");
                first_name.setText("");
                last_name.setText("");
                optional.setText("");
                getOptional1.setText("");
                optional2.setText("");
                getOptional2.setText("");
                table1.clearSelection();

            }
        });
        return buttonPanel;

    }

    private JPanel remarksUI(){
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Remarks Entry"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Student Id", "FirstName", "LastName", "TotalMarks", "Average", "Grade"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(scrollPane);
        GradeRegistration  gradeRegistration = new GradeRegistration();
        model.setRowCount(0);
        try{
            ResultSet resultSet = gradeRegistration.get();
            while ((resultSet.next())){
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("total"),
                        resultSet.getString("average"),
                        resultSet.getString("grade")
                });
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                total.setText(model.getValueAt(selectedRow,18).toString());
                avg.setText(model.getValueAt(selectedRow,19).toString());
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

        return tablePanel;
    }

    private  void refreshTable(){
        RemarksRegistration remarksRegistration = new RemarksRegistration();
        model1.setRowCount(0);
        try{
            ResultSet resultSet = remarksRegistration.get();
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
                        resultSet.getInt("optional2Marks"),
                        resultSet.getInt("total"),
                        resultSet.getInt("average")
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private  void refreshRemarksTable(){
        GradeRegistration gradeRegistration = new GradeRegistration();
        model.setRowCount(0);
        try{
            ResultSet resultSet = gradeRegistration.get();
            while ((resultSet.next())){
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("total"),
                        resultSet.getInt("average"),
                        resultSet.getString("grade")
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new Remarks();
    }


}
