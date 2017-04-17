package com.brokenarrow.jersey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DBConnection {
    // Method to create DB Connection
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception 
    {
        Connection con = null;
        try 
        {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } 
        catch (Exception e) 
        {
            throw e;
        } 
        finally 
        {
            return con;
        }
    }
    // Method to check whether uname and pwd combination are correct

    public static boolean checkLogin(String uname, String pwd) throws Exception 
    {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try 
            {
                dbConn = DBConnection.createConnection();
            } 
            catch (Exception e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM main_login WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } 
        catch (SQLException sqle) 
        {
            throw sqle;
        } 
        catch (Exception e) 
        {
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } 
        finally 
        {
            if (dbConn != null) 
            {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
}
