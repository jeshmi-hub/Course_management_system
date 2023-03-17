package GUIApplication;

import GUIApplication.Helpers.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollRegistration {
    private Connection con;
    public  EnrollRegistration(){
        try{
            con = DBUtils.getDBConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    ResultSet get(){
        try{
            String select = "SELECT id,first_name, last_name, course from users where role='Student'";
            PreparedStatement statement = con.prepareStatement(select);
            return  statement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  null;
    }


    ResultSet get1(){
        try {
            String select = "SELECT level, semester, module1, module2, module3 ,module4 from modules";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    void insert(int id, String levelNo, String semNo, String module1, String module2, String module3, String module4, String optional1, String optional2){
        try {
            String insert = "INSERT INTO enroll(id,levelNo,semNo,module1,module2,module3,module4,optional1,optional2)"+"VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1,id);
            statement.setString(2,levelNo);
            statement.setString(3,semNo);
            statement.setString(4,module1);
            statement.setString(5,module2);
            statement.setString(6,module3);
            statement.setString(7,module4);
            statement.setString(8,optional1);
            statement.setString(9,optional2);

            statement.executeUpdate();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    ResultSet get2(){
        try {
            String select = "SELECT users.id, users.first_name, users.last_name, users.course, enroll.levelNo, enroll.semNo, enroll.module1, enroll.module2, enroll.module3, enroll.module4, enroll.optional1, enroll.optional2 FROM users, enroll WHERE users.id = enroll.id";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }



}

