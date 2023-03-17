package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class RegisterPanel extends JPanel implements AppLayout{
    private GridBagConstraints gbc;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField username;
    private JPasswordField password;
    private JTextField age;
    private JTextField date;
    private JComboBox<String> gender;



    private JComboBox<String> course;
    private JComboBox<String> role;
    private JButton btnClear, btnRegister, btnUpdate, btnDelete;




    public RegisterPanel(){
        setBorder(BorderFactory.createTitledBorder("Registration"));
        setLayout(new GridBagLayout());
        setBackground(new Color(230,230,250));
        gbc = new GridBagConstraints();
        firstName = new JTextField(20);
        firstName.setToolTipText("Please enter your first name");
        lastName = new JTextField(20);
        username = new JTextField(20);
        password = new JPasswordField(20);
        age = new JTextField(20);
        gender = new JComboBox<>(new String[] {"Male","Female","Other"});
        //for getting the courses name from databases

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
        role = new JComboBox<>(new String[] {"Admin", "Instructor", "Student"});
        date = new JTextField(20);
        btnClear = new JButton("Clear All");
        btnClear.setBackground(Color.BLACK);
        btnClear.setForeground(Color.WHITE);
        btnRegister = new JButton("Register");
        btnRegister.setBackground(Color.BLACK);
        btnRegister.setForeground(Color.WHITE);
        btnUpdate = new JButton("Update");
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnUpdate.setVisible(false);


    }



    public JPanel panelUI(){

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(new JLabel("First Name"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(firstName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        add(new JLabel("Last Name"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth=3;
        add(lastName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Username"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(username,gbc);

        gbc.gridx =  2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Password"),gbc);
        gbc.gridx = 3;
        gbc.gridy =2;
        gbc.gridwidth =1;
        add(password,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel("Age"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth= 1;
        add(age,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth =1;
        add(new JLabel("Date of birth"),gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth =1;
        add(date,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(new JLabel("Gender"),gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(gender,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(new JLabel("Course"),gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(course,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(btnClear,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(btnDelete, gbc);


        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(new JLabel("Choose role"),gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(role, gbc);


        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth =1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnRegister,gbc);

        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnUpdate,gbc);
        return this;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JTextField getAge() {
        return age;
    }

    public JComboBox getGender() {
        return gender;
    }

    public  JComboBox getRole(){return  role;}

    public JTextField getDate(){
        return date;
    }

    public JComboBox getCourse(){
        return  course;
    }

    public JButton getBtnClear() {
        return btnClear;
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public  JButton getBtnDelete(){
        return btnDelete;
    }
}
