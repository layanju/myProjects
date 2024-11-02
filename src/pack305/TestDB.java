/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack305;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soso_
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GI19
 */

import java.sql.Connection;
import java.sql.DriverManager;


public class TestDB {
  
    Connection connection;
    
    public TestDB()
    {
    boolean b= connect();
 
    System.out.println("Response of DB connection: "+b);

    }
    
      public boolean connect()
{
      try
        {
            // (1) set the path for the database
            String ConnectionURL = "jdbc:mysql://localhost:3306/sys";
            
            // (2) create connection
            connection = DriverManager.getConnection(ConnectionURL,"root","marya") ;
        }
         catch(Exception exception)
        {
            System.out.println("This is DB exception:"+exception);
             return false ;
        }

        if(connection != null)
            return true ;
        else
            return false ;
    }

   public static void main(String args[])
   {
       TestDB db = new TestDB();

   }}
  
    


