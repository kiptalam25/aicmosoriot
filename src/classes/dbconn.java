/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class dbconn {
    Connection conn = null;
    
    public Connection dbconn(){
        
        try{
     Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql:///mosoriot", "root", "");
    return conn;
}catch(Exception e){
    
    JOptionPane.showMessageDialog(null, e);}
        
        return conn;
    }

}
