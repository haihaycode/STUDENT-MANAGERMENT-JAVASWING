package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Helper.DatabaseHelper;
import views.frame1;

public class UserProfileUpdate {
	private static String QuyenChung =UserDao.getRoleQuyen(frame1.returnTenTk());
	public static String getUserProfileUpdate(String userNew, String pass, String UserCurent)
			throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseHelper.getConnection();
		String sqlUpdateUser = "Update users set username = ? , password =? where username=?";
		PreparedStatement updateStm = conn.prepareStatement(sqlUpdateUser);
		updateStm.setString(1, userNew);
		updateStm.setString(2, pass);
		updateStm.setString(3, UserCurent);
		int row = updateStm.executeUpdate();
		String TrangThai = "";
		if (row == 0) {
			TrangThai = "Loi";
		} else {
			TrangThai = "Cap Nhat Thanh Cong !";
			Notification.InsertMess(QuyenChung, "Đã cập Nhật Quyền  "+UserCurent+"-> "+userNew);
		}

		return TrangThai;
	}

	public static boolean checkUser(String User) {
		boolean check = false;

		try {
			Connection connection = DatabaseHelper.getConnection();
			String sql = "SELECT username FROM users WHERE username = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, User.trim());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (Exception var6) {
			System.out.println(var6.getMessage());
		}

		return check;
	}
}