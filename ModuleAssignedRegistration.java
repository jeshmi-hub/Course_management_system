package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.*;

public class ModuleAssignedRegistration {
    private Connection con;

    public ModuleAssignedRegistration(){
        try {
            con = DBUtils.getDBConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    ResultSet get(){
        try {
            String select = "SELECT * from moduleAssigned";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();

        }
        return  null;
    }

    void insert(String instructor, String courseName, String module1Name, String module2Name, String module3Name, String module4Name){
        try{
            String insert = "INSERT INTO moduleAssigned(name,course,module1,module2,module3,module4)" + "VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,instructor);
            statement.setString(2,courseName);
            statement.setString(3,module1Name);
            statement.setString(4,module2Name);
            statement.setString(5,module3Name);
            statement.setString(6,module4Name);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String delete ="DELETE from moduleAssigned WHERE id=?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1,id);
            statement.execute();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    void update(int id ,String instructor, String courseName, String module1Name, String module2Name, String module3Name, String module4Name){
        try{
            String insert = "UPDATE moduleAssigned SET name = ?, course = ?, module1 = ?, module2 = ?, module3 = ?, module4 = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1,instructor);
            statement.setString(2,courseName);
            statement.setString(3,module1Name);
            statement.setString(4,module2Name);
            statement.setString(5,module3Name);
            statement.setString(6,module4Name);
            statement.setInt(7,id);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
