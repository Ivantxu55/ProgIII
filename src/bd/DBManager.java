package bd; 
 
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datos.*; 
 
public class DBManager { 
	private static Connection conn; 
	 
	 
	public static boolean abrirConexion() { 
		try { 
			Class.forName("org.sqlite.JDBC"); 
			conn = DriverManager.getConnection("jdbc:sqlite:bd/conciertos.db"); 
			Statement statement = conn.createStatement(); 
			String sent = "CREATE TABLE IF NOT EXISTS Conciertos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, genero TEXT, fecha TEXT, artista TEXT, descripcion TEXT, precio REAL, aforo INTEGER );"; 
			statement.executeUpdate( sent ); 
			sent = "CREATE TABLE IF NOT EXISTS Festivales (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fechaInicio TEXT, fechaFin TEXT, conciertos TEXT, descripcion TEXT);"; 
			statement.executeUpdate( sent ); 
			return true; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		} 
		
	} 
	public static void cerrarConexion() {
		try {
			conn.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Concierto> getConciertos(){
		ArrayList<Concierto> result = new ArrayList<>();
		try(Statement stmt = conn.createStatement()){
			ResultSet rs = stmt.executeQuery("SELECT * FROM Conciertos");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(rs.next()) {
				Concierto c = new Concierto(rs.getInt("id"), rs.getString("nombre"), Genero.valueOf(rs.getString("genero")), sdf.parse(rs.getString("fecha")), rs.getString("artista"), rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("aforo"));
				result.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<Festival> getFestivales(){
        ArrayList<Festival> result = new ArrayList<>();
        ArrayList<Concierto> con = getConciertos();
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM Festivales");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while(rs.next()) {
                ArrayList<Integer> ids = new ArrayList<>();

                String c = rs.getString("conciertos");
                String[] s = c.split(";");
                for(int i = 0; i<s.length; i++) {
                    ids.add(Integer.parseInt(s[i]));
                }

                Festival f = new Festival(rs.getInt("id"), rs.getString("nombre"), sdf.parse(rs.getString("fechaInicio")), sdf.parse(rs.getString("fechaFin")),  rs.getString("descripcion"), ids);
                result.add(f);
                f.actualizar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public static void eliminarConcierto(String nombre) {
		try(PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Conciertos WHERE nombre = ?")){
			pstmt.setString(0, nombre);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	public static void eliminarFestival(String nombre) {
		try(PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Festivales WHERE nombre = ?")){
			pstmt.setString(0, nombre);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	public static void main(String[] args) {
		abrirConexion();
		ArrayList<Festival> con = getFestivales();
		
		System.out.println(con);
		cerrarConexion();
	
	}
} 
