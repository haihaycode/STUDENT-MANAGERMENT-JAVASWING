package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=FPL_DaoTao;encrypt=true;trustServerCertificate=true;";
			connection = DriverManager.getConnection(url, "sa", "123");
			System.out.println("Kết nối thành công đến cơ sở dữ liệu!");
		} catch (SQLException var2) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu: " + var2.getMessage());
		}

		return connection;
	}
}