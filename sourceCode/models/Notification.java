package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import Helper.DatabaseHelper;

public class Notification {
	static ResultSet rs;

	public static ResultSet getNotification(String role) throws ClassNotFoundException, SQLException {
		Connection con = DatabaseHelper.getConnection();
		if (role.equals("admin")) {
			Statement stmadmin = con.createStatement();
			String sqladmin = "Select id,role,mess,ngaygio from thongbao";
			rs = stmadmin.executeQuery(sqladmin);
		} else {
			String sqlQuyenKhac = "Select id,role,mess,ngaygio from thongbao where role = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sqlQuyenKhac);
			preparedStatement.setString(1, role);
			rs = preparedStatement.executeQuery();
		}
		return rs;

	}

	public static void InsertMess(String role, String mess) throws ClassNotFoundException, SQLException {
		Connection con = DatabaseHelper.getConnection();
		String sqlInsertMess = "insert into thongbao ( role, mess, ngayGio) values (?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sqlInsertMess);
		preparedStatement.setString(1, role);
		preparedStatement.setString(2, mess);

		Timestamp currentDatetime = new Timestamp(System.currentTimeMillis());
		preparedStatement.setTimestamp(3, currentDatetime);

		preparedStatement.executeUpdate();
	}
}
