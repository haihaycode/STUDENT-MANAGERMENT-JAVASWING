package models;

import Helper.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointLookup {
	public Float[] traCuuDiem(String masv, int soDienThoai) throws ClassNotFoundException, SQLException {
		Float[] soLuong = new Float[4];
		String sql = "SELECT tienganh, tinhoc, gdtc FROM grade FULL JOIN students ON grade.masv = students.masv WHERE grade.masv = ? AND sdt = ?";
		Connection conn = DatabaseHelper.getConnection();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, masv);
		stm.setInt(2, soDienThoai);

		for (ResultSet rs = stm.executeQuery(); rs.next(); soLuong[3] = (soLuong[0] + soLuong[1] + soLuong[2]) / 3.0F) {
			soLuong[0] = rs.getFloat(1);
			soLuong[1] = rs.getFloat(2);
			soLuong[2] = rs.getFloat(3);
		}

		return soLuong;
	}
}