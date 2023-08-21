package models;

import Helper.DatabaseHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDao {
	static List<User> listUser = new ArrayList();
	private String tongSoSv = "SELECT COUNT(*) FROM students";
	private String loaiSinhiengioi = "select count(*) from grade where (tienganh + tinhoc + gdtc) / 3 >= 8";
	private String loaiSinhienkha = "select count(*) from grade where (tienganh + tinhoc + gdtc) / 3 >= 6.5 and (tienganh + tinhoc + gdtc) / 3 < 8";
	private String loaiSinhienTrungBinh = "select count(*) from grade where (tienganh + tinhoc + gdtc) / 3 < 6.5";
	private String chuacodiemMonNao = "select count(*) from students where masv not in(select masv from grade)";

	public int[] tinhSoLuongHocSinh() throws SQLException, ClassNotFoundException {
		int[] soLuong = new int[5];
		Connection connection = DatabaseHelper.getConnection();
		Statement stm = connection.createStatement();
		ResultSet rs = stm.executeQuery(this.tongSoSv);
		if (rs.next()) {
			soLuong[0] = rs.getInt(1);
		}

		ResultSet rsGioi = stm.executeQuery(this.loaiSinhiengioi);
		if (rsGioi.next()) {
			soLuong[1] = rsGioi.getInt(1);
		}

		ResultSet rsKha = stm.executeQuery(this.loaiSinhienkha);
		if (rsKha.next()) {
			soLuong[2] = rsKha.getInt(1);
		}

		ResultSet rsTrungBinh = stm.executeQuery(this.loaiSinhienTrungBinh);
		if (rsTrungBinh.next()) {
			soLuong[3] = rsTrungBinh.getInt(1);
		}

		ResultSet chuacodiemMonNaos = stm.executeQuery(this.chuacodiemMonNao);
		if (chuacodiemMonNaos.next()) {
			soLuong[4] = chuacodiemMonNaos.getInt(1);
		}

		rs.close();
		rsGioi.close();
		rsKha.close();
		rsTrungBinh.close();
		stm.close();
		connection.close();
		return soLuong;
	}

	public UserDao() {
		listUser.removeAll(listUser);
		if (listUser.isEmpty()) {
			try {
				Connection connection = DatabaseHelper.getConnection();
				Statement stm = connection.createStatement();
				String sql = "select * from users";
				ResultSet rs = stm.executeQuery(sql);

				while (rs.next()) {
					listUser.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
				}
			} catch (Exception var5) {
				System.out.println(var5.getMessage());
			}

			System.out.println("List User kết nối thành công và nhận được tài khoản vào list : ");
			Iterator var7 = listUser.iterator();

			while (var7.hasNext()) {
				User user = (User) var7.next();
				System.out.println(user.getUser());
			}
		}

	}

	public static String[] traTenRole(String tenTk) {
		String[] thongTin = new String[3];
		Iterator var3 = listUser.iterator();

		while (var3.hasNext()) {
			User user = (User) var3.next();
			if (tenTk.equals(user.getUser())) {
				thongTin[0] = user.getUser();
				thongTin[1] = user.getPass();
				thongTin[2] = user.getRole();
			}
		}

		return thongTin;
	}

	public static boolean checkCapNhat(String user, String Pass) {
		Iterator var3 = listUser.iterator();

		User user2;
		do {
			if (!var3.hasNext()) {
				return false;
			}

			user2 = (User) var3.next();
		} while (!user.trim().equals(user2.getUser()) || !Pass.trim().equals(user2.getPass()));

		return true;
	}

	public static String getRoleQuyen(String User) {
		String role = "";
		Iterator var4 = listUser.iterator();

		while (var4.hasNext()) {
			User user = (User) var4.next();
			if (user.getUser().equals(User)) {
				role = user.getRole();
			}
		}

		return role;
	}

	public boolean checkLogin(String user, String Pass) {
		Iterator var4 = listUser.iterator();

		User user2;
		do {
			if (!var4.hasNext()) {
				return false;
			}

			user2 = (User) var4.next();
		} while (!user.trim().equals(user2.getUser()) || !Pass.trim().equals(user2.getPass()));

		return true;
	}
}