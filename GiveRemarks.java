package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class GiveRemarks extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel;
    DefaultTableModel model;
    JTable table;
    JTextField idNo;
    JButton btnView;
    String firstName, lastName;
    int totalMarks, averageMarks;
    int id;
    JLabel grade, first_name, last_name, total_marks, average_marks, student_id;

    GiveRemarks(){
        setTitle("View Marks");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(1, 2));
        JPanel subPanel = new JPanel(new GridLayout(1, 2));
        add(dataUI());
        add(Table());
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
        dataPanel.setBorder(BorderFactory.createTitledBorder("Data Entry"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        ModuleAssignedRegistration moduleAssignedRegistration=new ModuleAssignedRegistration();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Student Id:"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        student_id = new JLabel("");
        dataPanel.add(student_id,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Name: "),gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        first_name = new JLabel("");
        dataPanel.add(first_name,gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        last_name = new JLabel("");
        dataPanel.add(last_name,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth= 1;
        dataPanel.add(new JLabel("Total Marks: "),gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        total_marks = new JLabel("");
        dataPanel.add(total_marks,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Average Marks: "),gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        average_marks = new JLabel("");
        dataPanel.add(average_marks,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth =1;
        dataPanel.add(new JLabel("Enter Id"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        idNo = new JTextField(20);
        dataPanel.add(idNo,gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        btnView = new JButton("Give Remarks");
        dataPanel.add(btnView,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        dataPanel.add(new JLabel("Received Grade:"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        grade = new JLabel("");
        dataPanel.add(grade,gbc);

        GradeRegistration gradeRegistration = new GradeRegistration();
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Integer.parseInt(idNo.getText().trim());
                view(id);
                try{
                    int studentId = Integer.parseInt(student_id.getText());
                    String studentFirstName = first_name.getText();
                    String studentLastName = last_name.getText();
                    int studentTotalMarks = Integer.parseInt(total_marks.getText());
                    int studentAverageMarks = Integer.parseInt(average_marks.getText());
                    if(averageMarks>=70){
                        grade.setText("A");
                    }else if(averageMarks> 59 && averageMarks<=69){
                        grade.setText("B");
                    }else if(averageMarks>40 && averageMarks<=59){
                        grade.setText("C");
                    }else{
                        grade.setText("D");
                    }
                    String gradePoint = grade.getText();
                    System.out.println(gradePoint + studentId + studentFirstName + studentLastName + studentTotalMarks + studentAverageMarks);
                    gradeRegistration.insert(studentId,studentFirstName,studentLastName,studentTotalMarks,studentAverageMarks,gradePoint);
                    JOptionPane.showMessageDialog(dataPanel, "Grades are added","Information",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex){
                    System.out.println("Message" + ex);
                }

            }
        });

        return  dataPanel;
    }


    private JPanel Table(){
        tablePanel =  new JPanel();
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Give Remarks"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO","First Name", "Last Name","Total Marks", "Average Marks"});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane);
        RemarksRegistration remarksRegistration = new RemarksRegistration();
        model.setRowCount(0);
        try{
            ResultSet resultSet = remarksRegistration.get();
            while ((resultSet.next())){
                model.addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("total"),
                        resultSet.getString("average")
                });
            }


        }catch(Exception e){
            JOptionPane.showMessageDialog(tablePanel,"Error","Error",JOptionPane.ERROR_MESSAGE);
        }

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                student_id.setText(model.getValueAt(selectedRow,0).toString());
                first_name.setText(model.getValueAt(selectedRow,1).toString());
                last_name.setText(model.getValueAt(selectedRow,2).toString());
                total_marks.setText(model.getValueAt(selectedRow,3). toString());
                average_marks.setText(model.getValueAt(selectedRow,4).toString());
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

    ResultSet view(int id){
        try{
            String url = "jdbc:mysql://localhost:3306/course";
            String uname="root";
            String pass = "";

            Connection con = DriverManager.getConnection(url,uname,pass);
            Statement st ;
            st = con.createStatement();
            String viewRemarks = "SELECT first_name, last_name,total,average from remarks where id="+id+"";
            ResultSet result = st.executeQuery(viewRemarks);


            while (result.next()){
                firstName= result.getString("first_name");
                lastName= result.getString("last_name");
                totalMarks = result.getInt("total");
                averageMarks = result.getInt("average");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        new GiveRemarks();
    }

}



