/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ehaya
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import javax.swing.JFrame;
public class JDBC 
           {
        public static boolean check_login(String Email,String password)
        {
        try{
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");
           
            String query="SELECT * FROM `user` WHERE `Email` = ? AND `password` = ? "; 
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, password);
            ResultSet resultset = statement.executeQuery();
            while(resultset.next())
            {
                String email = resultset.getString("Email");
                String pass = resultset.getString("password");
                if(email.equals(Email)&&pass.equals(password))
                {
                    String role = resultset.getString("role");
                    if (role.equals("User")){
                   
                     UserDashboard userdashboard = new UserDashboard();
                     userdashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
                userdashboard.setVisible(true);
                    return true;
                    }
                    else if(role.equals("Admin"))
                    {
                      AdminDashboard admindashboard = new AdminDashboard();
                      admindashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
                      admindashboard.setVisible(true);
                    return true;  
                    }
                
                }
            }
            return false;
        }   
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
      public static boolean register_user(String ID,String firstname,String lastname,String email,String phone_no,String password,String role)
      {
           try{
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");
           
            String query= "insert into `user` values(?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, ID);
            statement.setString(2, firstname);
            statement.setString(3, lastname);
            statement.setString(4, email);
            statement.setString(5, phone_no);
            statement.setString(6, password);
            statement.setString(7, role);
            statement.executeUpdate();
          return true;
      }catch(Exception ex)
        {
            ex.printStackTrace();
        }
           return false;
      }
      public boolean check_reserve(String userid,String flightno){
        try{
            Connection con=java.sql.DriverManager.getConnection( "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");
          String request="Select * from `reservation`";
           PreparedStatement statementcheck = con.prepareStatement(request);
            ResultSet resultset = statementcheck.executeQuery();
            while(resultset.next())
            {
                  
                String UID = resultset.getString("PassengerID"); 
                String FID = resultset.getString("Flightno");
  
                
  
            if(UID.equals(userid)&&FID.equals(flightno))
                return true;
            } 
              return false;   
            }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;    
    }
          public void update_seat(String userid,String flightno)
    {
      
        try{
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
         String request1 = "Select*from `reservation` where PassengerID=? AND Flightno=?";
      PreparedStatement statement1 = con.prepareStatement(request1);
      statement1.setString(1, userid);
      statement1.setString(2, flightno);
      ResultSet result=statement1.executeQuery();
      while(result.next())
      {
         String seatno=result.getString("SeatNO");
         change_value(seatno);
    
      }
                    }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
      
             public boolean delete_reservetuple(String userid,String flightno)
    {
     try{
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
          //String request="Delete from reservation where ReservationID='reserveid'";
          String request = "Delete from reservation where PassengerID=? AND Flightno=?";
      PreparedStatement statement = con.prepareStatement(request);
statement.setString(1, userid);
statement.setString(2, flightno);
statement.executeUpdate();
       
            return true;
            }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;    
    }
   public boolean change_value(String seatno){
        try{
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
       String request="Update `seat` SET SeatAvailability='Available' where SeatID=?";
             PreparedStatement statement = con.prepareStatement(request);
            statement.setString(1, seatno);
                     statement.executeUpdate();
                     return true;
      
                    }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;    
    }
   public boolean check_reservation(String reserveid,String flightno){
       try{
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
          String request="Select * from `reservation`";
           PreparedStatement statementcheck = con.prepareStatement(request);
            ResultSet resultset = statementcheck.executeQuery();
            while(resultset.next())
            {
                  
                String RID = resultset.getString("ReservationID"); 
                String FID = resultset.getString("Flightno");
  
                
  
            if(RID.equals(reserveid)&&FID.equals(flightno))
                return true;
            } 
                return false;    
            }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;    
   }
   public void admin_update_seat(String reserveid,String flightno){
       try{
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
         String request1 = "Select*from `reservation` where ReservationID=? AND Flightno=?";
      PreparedStatement statement1 = con.prepareStatement(request1);
      statement1.setString(1, reserveid);
      statement1.setString(2, flightno);
      ResultSet result=statement1.executeQuery();
      while(result.next())
      {
         String seatno=result.getString("SeatNO");
       change_value(seatno);
      
      }
                    }catch(Exception ex)
        {
            ex.printStackTrace();
        }
     
    
   }

public boolean admin_delete_reservetuple(String reserveid,String flightno){
     try{
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
          
          String request = "Delete from `reservation` where ReservationID=? AND Flightno=?";
      PreparedStatement statement = con.prepareStatement(request);
statement.setString(1, reserveid);
statement.setString(2, flightno);
statement.executeUpdate();
       
            return true;
            }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
}
public static boolean Add_reservation(String rid,String userid,String Seatno,String flightno){   
    try{   
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
    String query= "insert into `reservation` values(?,?,?,?)";   
    PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, rid);            
            statement.setString(2, userid);
            statement.setString(3, Seatno);            
            statement.setString(4, flightno);
            statement.executeUpdate();
           return true;
}catch(Exception ex)        {
            ex.printStackTrace();        }
        return false;                }
    public static boolean Flight_add(String FID,String FROM,String TO,String AID,String DEPATURE,String ARRIVAL)
      {
           try{
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");
           
            String query= "insert into `flight` values(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, FID);
            statement.setString(2, FROM);
            statement.setString(3, TO);
            statement.setString(4, AID);
            statement.setString(5, DEPATURE);
            statement.setString(6, ARRIVAL);
            statement.executeUpdate();
          return true;
      }catch(Exception ex)
        {
            ex.printStackTrace();
        }
           return false;
      }
    public static boolean checkuser(String userid)
    {
        try{
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");

    String query = "select * from `user` where UserID=?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setString(1,userid );
    ResultSet resultset = statement.executeQuery();

    while (resultset.next()) {
        String uid = resultset.getString("UserID");
       // String classtype = resultset.getString("SeatClass");

        if (uid.equals(userid)) {
            String seat = resultset.getString("SeatID");
        }
    }
    
} catch (Exception ex) {
    ex.printStackTrace();
}
   
      return false;  
    }
    
/*public static String checkflight(String flightID)
{
    try{
    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/airlines", "root", "H0797Y");
    String seat ;
    String query = "select * from `seat` where SeatAvailability='Available' and FlightID=?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setString(1,flightID );
    ResultSet resultset = statement.executeQuery();

    while (resultset.next()) {
        //String fid = resultset.getString("FlightID");
       // String classtype = resultset.getString("SeatClass");

        //if (fid.equals(flightID)) {
           String  seat = resultset.getString("SeatID");
         
        }
    }
    
} catch (Exception ex) {
    ex.printStackTrace();
}
      return seat;
    }  
*/
       public static boolean changeseat(String seatno){
        try{
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/airlines","root","H0797Y");
       String request="Update `seat` SET SeatAvailability='Booked' where SeatID=?";
             PreparedStatement statement = con.prepareStatement(request);
            statement.setString(1, seatno);
                     statement.executeUpdate();
                     return true;
      
                    }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;    
    }
    
    public static void main(String[] args){
        JDBC C=new JDBC();
    }

}

