package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuleRegistration {
    private Connection con;
    public ModuleRegistration(){
        try{
            con = DBUtils.getDBConnection();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    ResultSet get(){
        try{
            String select = "SELECT * FROM modules";
            PreparedStatement statement = con.prepareStatement(select);
            return  statement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    void insert(String courseName, String semesterNo, String levelNo, String module1Name, String module2Name, String module3Name, String module4Name){
        try{
            String insert = "INSERT INTO modules(course,semester,level,module1,module2,module3,module4)" + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,courseName);
            statement.setString(2,semesterNo);
            statement.setString(3,levelNo);
            statement.setString(4,module1Name);
            statement.setString(5,module2Name);
            statement.setString(6,module3Name);
            statement.setString(7,module4Name);

            statement.executeUpdate();
            statement.close();

        }catch(SQLException e){
         e.printStackTrace();
        }
    }

    void update(int id,String courseName, String semesterNo, String levelNo, String module1Name, String module2Name, String module3Name, String module4Name){
        try{
            String update = "UPDATE modules SET course=?,semester=?,level=?,module1=?,module2=?,module3=?,module4=? WHERE id=?" ;
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1,courseName);
            statement.setString(2,semesterNo);
            statement.setString(3,levelNo);
            statement.setString(4,module1Name);
            statement.setString(5,module2Name);
            statement.setString(6, module3Name);
            statement.setString(7,module4Name);
            statement.setInt(8,id);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    public void delete(int id){
        String delete = "DELETE FROM modules WHERE id=?";
        try{
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1,id);
            statement.execute();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }





}
