
package koneksi;
import java.sql.*;
/**
 *
 * @author LENOVO
 */
public class koneksi {
    private Connection koneksi;
    public Connection connect(){
    try{
        Class.forName ("com.mysql.jdbc.Driver");
        System.out.println("Berhasil Koneksi");
    }catch (ClassNotFoundException ex){
        System.out.println("Gagal Koneksi "+ex);
    }
    String url = "jdbc:mysql://localhost/gaji";
    try{
        koneksi = DriverManager.getConnection(url,"root","");
        System.out.println("Berhasil Koneksi Database");
    }catch (SQLException ex){
        System.out.println("Gagal Koneksi Database "+ex);
    }
    return koneksi;
    
    } 
    
}
