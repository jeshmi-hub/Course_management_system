package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRegistration {
    private Connection con;

    public CourseRegistration(){
        try{
            con = DBUtils.getDBConnection();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    ResultSet get() {
        try {
            String select = "SELECT * FROM course";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insert(String course, int userId){
        try{
            String insert = "INSERT INTO course (courseName,  user_id)" +
                    "VALUES (?,?)";
            System.out.println(course);
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,course);
            statement.setInt(2,userId);

            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void delete(int id){
        String delete = "DELETE from course WHERE id=?";
        try{
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    void update(int id, String course){
        try{
            String update = "UPDATE course SET courseName = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(update);

            statement.setString(1,course);
            statement.setInt(2,id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
}


