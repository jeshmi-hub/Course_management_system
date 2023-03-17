package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksRegistration {
    private Connection con;

    public MarksRegistration(){
        try{
            con = DBUtils.getDBConnection();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    ResultSet get(){try{
        String select = "SELECT * FROM marks";
        PreparedStatement statement = con.prepareStatement(select);
        return  statement.executeQuery();
    }catch (SQLException e){
        e.printStackTrace();
    }
        return  null;
    }

    void insert(int idNo, String moduleName1,int moduleMarks1, String moduleName2, int moduleMarks2, String moduleName3, int moduleMarks3, String moduleName4,int moduleMarks4, String optionalName1, int optionalMarks1, String optionalName2, int optionalMarks2, String levelNo, String semesterNo, String firstName, String lastName, String courseName){
        try{
            String insert = "INSERT INTO marks(id,moduleName1,module1Marks,moduleName2,module2Marks,moduleName3,module3Marks,moduleName4,module4Marks,optionalName1,optional1Marks,optionalName2,optional2Marks,level,semester,first_name,last_name,course)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1,idNo);
            statement.setString(2,moduleName1);
            statement.setInt(3,moduleMarks1);
            statement.setString(4, moduleName2);
            statement.setInt(5,moduleMarks2);
            statement.setString(6,moduleName3);
            statement.setInt(7,moduleMarks3);
            statement.setString(8,moduleName4);
            statement.setInt(9,moduleMarks4);
            statement.setString(10,optionalName1);
            statement.setInt(11,optionalMarks1);
            statement.setString(12,optionalName2);
            statement.setInt(13,optionalMarks2);
            statement.setString(14,levelNo);
            statement.setString(15,semesterNo);
            statement.setString(16,firstName);
            statement.setString(17,lastName);
            statement.setString(18,courseName);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    void update(int idNo, int moduleMarks1, int moduleMarks2, int moduleMarks3, int moduleMarks4,  int optionalMarks1, int optionalMarks2){
        try{
            String insert = "UPDATE marks SET module1Marks=?,module2Marks=?,module3Marks=?,module4Marks=?,optional1Marks=?,optional2Marks=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1,moduleMarks1);
            statement.setInt(2,moduleMarks2);
            statement.setInt(3,moduleMarks3);
            statement.setInt(4,moduleMarks4);
            statement.setInt(5,optionalMarks1);
            statement.setInt(6,optionalMarks2);
            statement.setInt(7,idNo);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void delete(int id){
        String delete = "DELETE FROM marks WHERE id=?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


}
