package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.*;

public class GradeRegistration {
    private Connection con;
    public GradeRegistration(){
        try{
            con = DBUtils.getDBConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    ResultSet get(){
        try{
            String select = "SELECT * from grade";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return  null;
    }

    void insert(int studentId, String studentFirstName, String studentLastName, int studentTotalMarks , int studentAverageMarks, String gradePoint ){
        try{
            System.out.println(gradePoint + studentId + studentFirstName + studentLastName + studentTotalMarks + studentAverageMarks);
            Statement st = con.createStatement();
            String query = "INSERT INTO grade(id,first_name,last_name,total,average,grade) VALUES ("+studentId+",'"+studentFirstName+"','"+studentLastName+"','"+studentTotalMarks+"','"+studentAverageMarks+"','"+gradePoint+"')";
            st.executeUpdate(query);
//
//            String insert = "INSERT INTO grade(id,first_name,last_name,total,average,grade)" + "VALUES (?,?,?,?,?,?)";
//            PreparedStatement statement = con.prepareStatement(insert);
//            statement.setInt(1,studentId);
//            statement.setString(2,studentFirstName);
//            statement.setString(3,studentLastName);
//            statement.setInt(4,studentTotalMarks);
//            statement.setInt(5, studentAverageMarks);
//            statement.setString(6,gradePoint);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
