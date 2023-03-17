package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import javax.swing.*;
import java.sql.*;

public class UserRegistration {
    private Connection con;

    public UserRegistration() {
        try {
            con = DBUtils.getDBConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ResultSet get() {
        try {
            String select = "SELECT * from users";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return null;
    }

    void insert(String firstName, String lastName, String username, String password,
                int age, String date, String gender, String course, String role) {
        try {
            String insert = "INSERT INTO users (first_name, last_name, username, password, age, date_of_birth, gender, course, role) " +
                    "VALUES (?,?,?,?,?,?,?,?, ?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setInt(5, age);
            statement.setString(6, date);
            statement.setString(7, gender);
            statement.setString(8, course);
            statement.setString(9, role);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void update(int id,String firstName, String lastName, String username, String password,
                int age, String date, String gender, String course){
        try{
            String update = "UPDATE users SET first_name = ?, last_name=?, username=?, password=?, age=?, date_of_birth=?, gender=?, course=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(update);

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setInt(5, age);
            statement.setString(6, date);
            statement.setString(7, gender);
            statement.setString(8, course);
            statement.setInt(9, id);

            statement.executeUpdate();
            statement.close();//prevents from memory leaking


        }catch (SQLException e){
            e.printStackTrace();
        }




    }

    void delete(int id){
         String delete = "DELETE FROM users WHERE id=?";
         try{
             PreparedStatement statement = con.prepareStatement(delete);
             statement.setInt(1, id);
             statement.execute();
             statement.close();
         }catch (SQLException e){
             e.printStackTrace();

         }


    }

    ResultSet login(String username, String password){
        String login = "SELECT * FROM users where username=? and password=?";
        try{
            PreparedStatement statement = con.prepareStatement(login);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                try {
                    String url = "jdbc:mysql://localhost:3306/course";
                    String uname="root";
                    String pass = "";

                    Connection con =DriverManager.getConnection(url,uname,pass);
                    Statement st ;
                    st = con.createStatement();
                    String query = "SELECT id ,role  FROM users where username = '"+username+"'";
                    ResultSet result = st.executeQuery(query);

                    while(result.next()) {
                        int id = result.getInt("id");

                        String role = rs.getString("role");
                        if(role.equals("Student")){
                            new StudentPanel(id);
                        }else if(role.equals("Instructor")){
                            new TeacherHome();
                        }else {
                            new AdminHome(id);
                        }

                    }
                }
                catch(Exception e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(null,"Logged in successfully");
            }else{
                JOptionPane.showMessageDialog(null,"Invalid username and password");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return null;

    }

}
