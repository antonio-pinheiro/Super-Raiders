/*package ferramentas;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConexaoUtil {

	
	private static ConexaoUtil conexaoUtil;
	
	public static ConexaoUtil getInstance() {
		//Verifica se existe uma conexão existente, caso negativo, a conexão é realizada
		if (conexaoUtil == null) {
			conexaoUtil = new ConexaoUtil();
			
		}
		return conexaoUtil;
	}
	
		public Connection getConnection () throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			
			return (Connection) DriverManager.getConnection("jdbc:mysql://0.0.0.0:3306/superraiders","antonio","masterzx"); 
			
		}
		
		public static void main (String[]args) {
			
			try {
			System.out.println(getInstance().getConnection());
		} catch (Exception e) {
			e.printStackTrace();

		
		}
		}
}*/
