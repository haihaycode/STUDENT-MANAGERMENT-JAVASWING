package ChuongTrinhQuanLiSinhVien;

import java.awt.EventQueue;

import views.frame1;

public class chuongtrinh {
	public static void main(String[] args) {

		try {
			frame1 frame = new frame1();
			frame.setVisible(true);
			frame.hamChayDauChuongTrinh();
		} catch (Exception var2) {
			System.out.println(var2.getMessage());
		}

	}

}
