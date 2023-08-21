package views;

import Helper.RoundedUtils;
import models.PointLookup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class traCuuDiemSinhVien extends JPanel {
	private JTextField txtMaSinhVien;
	private JTextField txtSoDienThoai;
	private JLabel txtDiemTienganh;
	private JLabel txtDiemTin;
	private JLabel txtDiemGDTC;
	private JLabel txtDiemTrungBinh;

	private void point_lookup() throws NumberFormatException, ClassNotFoundException, SQLException {
		PointLookup lookup = new PointLookup();
		Float[] mangdiem = lookup.traCuuDiem(this.txtMaSinhVien.getText(),
				Integer.parseInt(this.txtSoDienThoai.getText()));
		this.txtDiemTienganh.setText("Điểm Tiếng Anh : " + mangdiem[0] + "\n");
		this.txtDiemTin.setText("Điểm Tin Học : " + mangdiem[1] + "\n");
		this.txtDiemGDTC.setText("Điểm Giáo Dục Thể Chất " + mangdiem[2] + "\n");
		this.txtDiemTrungBinh.setText("Điểm Trung Bình Các Môn : " + mangdiem[3]);
	}

	public traCuuDiemSinhVien() {
		this.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.add(panel, "Center");
		panel.setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		panel_1.setLayout((LayoutManager) null);
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, -35, 837, 225);
		panel_1.add(panel_2);
		panel_2.setLayout((LayoutManager) null);
		JLabel label = new JLabel("New label");
		label.setBounds(396, 5, 45, 13);
		panel_2.add(label);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-1085, 39, 3000, 2000);
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\5305323.jpg"));
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(0, 149, 837, 331);
		panel_1.add(panel_3);
		panel_3.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1_1 = new JLabel("Tra Cứu Điểm Sinh Viên ");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", 1, 16));
		lblNewLabel_1_1.setBounds(38, 56, 259, 32);
		panel_3.add(lblNewLabel_1_1);
		this.txtMaSinhVien = new JTextField();
		this.txtMaSinhVien.setBounds(38, 113, 259, 32);
		panel_3.add(this.txtMaSinhVien);
		this.txtMaSinhVien.setColumns(10);
		this.txtSoDienThoai = new JTextField();
		this.txtSoDienThoai.setColumns(10);
		this.txtSoDienThoai.setBounds(307, 113, 259, 32);
		panel_3.add(this.txtSoDienThoai);
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhập Mã Số Sinh Viên ");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", 0, 14));
		lblNewLabel_1_1_1.setBounds(38, 83, 259, 32);
		panel_3.add(lblNewLabel_1_1_1);
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nhập Số Điện Thoại");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", 0, 14));
		lblNewLabel_1_1_1_1.setBounds(307, 83, 259, 32);
		panel_3.add(lblNewLabel_1_1_1_1);
		JPanel btnTraCuu = new JPanel();
		RoundedUtils.setRounded(btnTraCuu, 10, Color.red);
		btnTraCuu.setBackground(new Color(255, 0, 0));
		btnTraCuu.setBounds(602, 113, 100, 32);
		panel_3.add(btnTraCuu);
		btnTraCuu.setLayout((LayoutManager) null);
		JLabel lblNewLabel_2 = new JLabel("TRA CỨU");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					traCuuDiemSinhVien.this.point_lookup();
				} catch (ClassNotFoundException | SQLException | NumberFormatException var3) {
					System.out.println(var3.getMessage());
				}

			}
		});
		lblNewLabel_2.setBounds(0, 0, 100, 32);
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", 1, 15));
		lblNewLabel_2.setHorizontalAlignment(0);
		btnTraCuu.add(lblNewLabel_2);
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(307, 173, 386, 148);
		panel_3.add(panel_5);
		panel_5.setLayout((LayoutManager) null);
		this.txtDiemTienganh = new JLabel("");
		this.txtDiemTienganh.setFont(new Font("Times New Roman", 0, 15));
		this.txtDiemTienganh.setHorizontalAlignment(2);
		this.txtDiemTienganh.setBounds(10, 10, 366, 13);
		panel_5.add(this.txtDiemTienganh);
		this.txtDiemTin = new JLabel("");
		this.txtDiemTin.setFont(new Font("Times New Roman", 0, 15));
		this.txtDiemTin.setHorizontalAlignment(2);
		this.txtDiemTin.setBounds(10, 48, 366, 13);
		panel_5.add(this.txtDiemTin);
		this.txtDiemGDTC = new JLabel("");
		this.txtDiemGDTC.setFont(new Font("Times New Roman", 0, 15));
		this.txtDiemGDTC.setHorizontalAlignment(2);
		this.txtDiemGDTC.setBounds(10, 81, 366, 13);
		panel_5.add(this.txtDiemGDTC);
		this.txtDiemTrungBinh = new JLabel("");
		this.txtDiemTrungBinh.setFont(new Font("Times New Roman", 0, 15));
		this.txtDiemTrungBinh.setHorizontalAlignment(2);
		this.txtDiemTrungBinh.setBounds(10, 125, 366, 13);
		panel_5.add(this.txtDiemTrungBinh);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(24, 173, 273, 148);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(
				new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\3d-flame-teacher-in-empty-school-classroom.png"));
	}
}