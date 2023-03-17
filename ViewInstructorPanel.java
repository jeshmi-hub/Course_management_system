package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewInstructorPanel extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel, tablePanel;
    DefaultTableModel model;
    JTable table;
    JTextField name;
    JButton btnView;
    String module1,module2,module3,module4;
    String instructor;

    ViewInstructorPanel(){
        setTitle("View Instructor");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setLayout(new GridLayout(1,1));
        setSize(400,400);
        JPanel subPanel = new JPanel(new GridLayout(1,1));
        subPanel.add(dataUI());
        subPanel.add(Table());
        add(subPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private  JMenuBar getMenu(){
        menuBar = new JMenuBar();
        JMenu course  = new JMenu("Student");
        menuBar.add(course);

        JMenuItem addCourse = new JMenuItem("View Student");
        course.add(addCourse);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudent();
            }
        });

        JMenu module = new JMenu("Teacher");
        menuBar.add(module);


        JMenuItem addModule = new JMenuItem("Assigned Module");
        module.add(addModule);
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructorPanel();
            }
        });
        JMenu details = new JMenu("Marks");
        menuBar.add(details);

        JMenuItem addDetails = new JMenuItem("Add Marks");
        details.add(addDetails);
        addDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructorPanel();
            }
        });




        return menuBar;
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
        gbc.gridwidth =1;
        dataPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        name = new JTextField(20);
        dataPanel.add(name,gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        btnView = new JButton("View Instructor");
        dataPanel.add(btnView,gbc);


        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructor = name.getText().trim();
                view(instructor);
                JOptionPane.showMessageDialog(dataPanel, instructor+ " "+ "teaches " + module1 + ", "+ module2 + ", "+ module3 + ", " + module4, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return  dataPanel;
    }

    private JPanel Table(){
        tablePanel =  new JPanel();
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Modules Assigned to Instructor"));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"S.NO","Name", "Course","Module1", "Module2","Module3", "Module4"});
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


        }catch(Exception e){
            JOptionPane.showMessageDialog(tablePanel,"Error","Error",JOptionPane.ERROR_MESSAGE);
        }
        return tablePanel;
    }

    ResultSet view(String instructor){
        try{
            String url = "jdbc:mysql://localhost:3306/course";
            String uname="root";
            String pass = "";

            Connection con = DriverManager.getConnection(url,uname,pass);
            Statement st ;
            st = con.createStatement();
            String viewInstructor = "SELECT module1, module2,module3,module4 from moduleAssigned where name='"+instructor+"'";
            ResultSet result = st.executeQuery(viewInstructor);


            while (result.next()){
               module1= result.getString("module1");
               module2= result.getString("module2");
               module3 = result.getString("module3");
               module4 = result.getString("module4");


            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
