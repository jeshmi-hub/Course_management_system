package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemarksRegistration {
    private Connection con;

    public RemarksRegistration(){
        try{
            con = DBUtils.getDBConnection();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    ResultSet get(){
        try {
            String select = "SELECT * FROM remarks";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();

        }
        return null;
    }

    void insert(int idNo,String firstName, String lastName, String courseName, String levelNo, String semesterNo,  String moduleName1,int moduleMarks1, String moduleName2, int moduleMarks2, String moduleName3, int moduleMarks3, String moduleName4,int moduleMarks4, String optionalName1, int optionalMarks1, String optionalName2, int optionalMarks2, int totalMarks, int average){
        try{
            String insert = "INSERT INTO remarks(id,first_name,last_name,course,level,semester,moduleName1,module1Marks,moduleName2,module2Marks,moduleName3,module3Marks,moduleName4,module4Marks,optionalName1,optional1Marks,optionalName2,optional2Marks,total,average)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1,idNo);
            statement.setString(2,firstName);
            statement.setString(3,lastName);
            statement.setString(4,courseName);
            statement.setString(5,levelNo);
            statement.setString(6,semesterNo);
            statement.setString(7,moduleName1);
            statement.setInt(8,moduleMarks1);
            statement.setString(9, moduleName2);
            statement.setInt(10,moduleMarks2);
            statement.setString(11,moduleName3);
            statement.setInt(12,moduleMarks3);
            statement.setString(13,moduleName4);
            statement.setInt(14,moduleMarks4);
            statement.setString(15,optionalName1);
            statement.setInt(16,optionalMarks1);
            statement.setString(17,optionalName2);
            statement.setInt(18,optionalMarks2);
            statement.setInt(19, totalMarks);
            statement.setInt(20,average);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}
