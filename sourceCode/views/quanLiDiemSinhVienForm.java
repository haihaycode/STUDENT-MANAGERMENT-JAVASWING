package views;

import Helper.DatabaseHelper;
import Helper.RoundedButton;
import models.Notification;
import models.Student;
import models.UserDao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class quanLiDiemSinhVienForm extends JPanel {
	private JTextField txtMaSv;
	private JTextField txtDiemTiengAnh;
	private JTextField txtDiemTinHoc;
	private JTextField txtDiemGDTC;
	private JTable table;
	private JLabel txtDiemTB;
	private JLabel txtHoTen;
	private DefaultTableModel model;
	private JTextField txtSearch;
	private List<Student> listStudentsDiem = new ArrayList();
	private int soDem = 0;
	
	private String QuyenChung =UserDao.getRoleQuyen(frame1.returnTenTk());

	private void hamChayDauChuongtrinh() {
		try {
			if (this.checkList()) {
				this.initTable();

				try {
					this.fillDataToList();
				} catch (ClassNotFoundException var2) {
					JOptionPane.showMessageDialog((Component) null, var2.getMessage());
				}

				this.fillListTop3ToTable();
				this.FillListToForm(this.soDem);
			}
		} catch (SQLException var3) {
			System.out.println(var3.getMessage());
		}

	}

	public boolean checkList() {
		try {
			this.fillDataToList();
		} catch (SQLException var4) {
			System.out.println(var4.getMessage());
		} catch (ClassNotFoundException var5) {
			JOptionPane.showMessageDialog((Component) null, var5.getMessage());
		}

		boolean check = true;
		Iterator var3 = this.listStudentsDiem.iterator();

		while (var3.hasNext()) {
			Student sinhVien = (Student) var3.next();
			System.out.println(sinhVien.getID());
		}

		if (this.listStudentsDiem.isEmpty()) {
			check = false;
			System.out.println("List Rong !");
		}

		return check;
	}

	public void initTable() {
		this.model = (DefaultTableModel) this.table.getModel();
		this.model.setColumnIdentifiers(new String[]{"Mã Sinh Viên", "Họ Tên", "Điểm Tiếng Anh", "Điểm Tin Học",
				"Điểm GDTC", "Điểm Trung Bình"});
	}

	private void fillDataToList() throws SQLException, ClassNotFoundException {
		this.listStudentsDiem.removeAll(this.listStudentsDiem);
		Connection con = DatabaseHelper.getConnection();
		Statement stmGetDataToList = con.createStatement();
		String sql = "Select id,students.masv,hoten,tienganh,tinhoc,gdtc from grade full join students on grade.masv = students.masv";
		ResultSet rs = stmGetDataToList.executeQuery(sql);

		while (rs.next()){
			this.listStudentsDiem.add(new Student(Integer.toString(rs.getInt(1)), rs.getString(2).trim(),
					rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6)));
		}

	}

	private void fillListTop3ToTable() {
		this.model.setRowCount(0);
		Collections.sort(this.listStudentsDiem, new Comparator<Student>() {
			public int compare(Student sinhVienTam1, Student sinhVienTam2) {
				float diemTrungBinh1 = (sinhVienTam1.getDiemTiengAnh() + sinhVienTam1.getDiemTinHoc()
						+ sinhVienTam1.getDiemGiaoDucTheChat()) / 3.0F;
				float diemTrungBinh2 = (sinhVienTam2.getDiemTiengAnh() + sinhVienTam2.getDiemTinHoc()
						+ sinhVienTam2.getDiemGiaoDucTheChat()) / 3.0F;
				return Float.compare(diemTrungBinh2, diemTrungBinh1);
			}
		});
		int bienDem = 0;

		for (Iterator var3 = this.listStudentsDiem.iterator(); var3.hasNext(); ++bienDem) {
			Student sinhVien = (Student) var3.next();
			if (bienDem >= 3) {
				break;
			}

			float diemTrungBinh = (sinhVien.getDiemTiengAnh() + sinhVien.getDiemTinHoc()
					+ sinhVien.getDiemGiaoDucTheChat()) / 3.0F;
			this.model.addRow(new Object[]{sinhVien.getMaSV(), sinhVien.getHoTen(), sinhVien.getDiemTiengAnh(),
					sinhVien.getDiemTinHoc(), sinhVien.getDiemGiaoDucTheChat(), diemTrungBinh});
		}

		this.table.setModel(this.model);
	}

	private void FillListToForm(int soDem) {
		Student st = (Student) this.listStudentsDiem.get(soDem);
		this.txtMaSv.setText(st.getMaSV());
		this.txtHoTen.setText(st.getHoTen());
		this.txtDiemTiengAnh.setText(Float.toString(st.getDiemTiengAnh()));
		this.txtDiemTinHoc.setText(Float.toString(st.getDiemTinHoc()));
		this.txtDiemGDTC.setText(Float.toString(st.getDiemGiaoDucTheChat()));
		float diemTrungBinh = (st.getDiemTiengAnh() + st.getDiemTinHoc() + st.getDiemGiaoDucTheChat()) / 3.0F;
		String formattedDiemTrungBinh = String.format("%.2f", diemTrungBinh);
		this.txtDiemTB.setText(formattedDiemTrungBinh);
	}

	private void search() {
		String masv = this.txtSearch.getText();

		for (int i = 0; i < this.listStudentsDiem.size(); ++i) {
			if (((Student) this.listStudentsDiem.get(i)).getMaSV().equals(masv)) {
				this.FillListToForm(i);
			}
		}

		this.txtSearch.setText("Nhập mã Sinh viên ");
	}

	private void deleteSinhVienCoDiem() throws SQLException, ClassNotFoundException {
		String maSinhVien = this.txtMaSv.getText().trim();
		if (maSinhVien.equals("")) {
			JOptionPane.showMessageDialog(this, "Chọn sinh viên trong bảng hoặc nhập mã sinh viên để xóa");
		} else {
			int xacNhanXoa = JOptionPane.showConfirmDialog(this,
					"Bạn có muốn xóa đi sinh viên " + maSinhVien + " không ?", "xóa hay không ?", 0);
			if (xacNhanXoa != 0) {
				return;
			}

			Connection con = DatabaseHelper.getConnection();
			String check = "";
			String sqltontai = "select masv From grade WHERE masv = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sqltontai);
			preparedStatement.setString(1, maSinhVien);

			for (ResultSet rs = preparedStatement.executeQuery(); rs.next(); check = rs.getString(1)) {
				;
			}

			if (check.equals("")) {
				JOptionPane.showMessageDialog(this, "sinh viên " + maSinhVien + " chưa có điểm để xóa");
				return;
			}

			String sql = "delete FROM grade WHERE masv = ?";
			PreparedStatement preparedStatements = con.prepareStatement(sql);
			preparedStatements.setString(1, maSinhVien);
			int rss = preparedStatements.executeUpdate();
			if (rss <= 0) {
				JOptionPane.showMessageDialog(this, "Lỗi không xóa được sinh viên");
			} else {
				JOptionPane.showMessageDialog(this, "Đã xóa sinh viên " + maSinhVien + "\n" + "row = " + rss);
				Notification.InsertMess(QuyenChung, "Đã Xóa 1  Sinh Vien   "+maSinhVien);
				this.hamChayDauChuongtrinh();
			}
		}

	}

	private void updateSinhVien() throws SQLException, ClassNotFoundException {
		String maSinhVien = this.txtMaSv.getText().trim();
		if (maSinhVien.equals("")) {
			JOptionPane.showMessageDialog(this, "Chọn sinh viên trong bảng hoặc nhập mã sinh viên để xóa");
		} else {
			int xacNhanXoa = JOptionPane.showConfirmDialog(this,
					"Bạn có muốn cập nhật đi sinh viên " + maSinhVien + " không ?", "cập nhật hay không ?", 0);
			if (xacNhanXoa != 0) {
				System.out.println("Hủy bỏ tác vụ update ...");
				return;
			}

			Connection con = DatabaseHelper.getConnection();
			String check = "";
			String sqltontai = "select masv From grade WHERE masv = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sqltontai);
			preparedStatement.setString(1, maSinhVien);

			for (ResultSet rs = preparedStatement.executeQuery(); rs.next(); check = rs.getString(1)) {
				;
			}

			if (!check.equals("")) {
				boolean kiemTraTinhHopLe = true;
				if (this.txtMaSv.getText().length() > 7 || this.txtMaSv.getText().length() < 7) {
					kiemTraTinhHopLe = false;
					JOptionPane.showMessageDialog(this, "Mã sinh viên bao gồm 7 kí tự");
				}

				try {
					float diemTa = Float.parseFloat(this.txtDiemTiengAnh.getText());
					float diemTin = Float.parseFloat(this.txtDiemTinHoc.getText());
					float diemGDTC = Float.parseFloat(this.txtDiemGDTC.getText());
					if (diemGDTC > 10.0F || diemTa > 10.0F || diemTin > 10.0F || diemGDTC < 0.0F || diemTa < 0.0F
							|| diemTin < 0.0F) {
						kiemTraTinhHopLe = false;
						JOptionPane.showMessageDialog(this, "Điểm Không quá 10 và khong nhỏ hơn 0");
					}
				} catch (Exception var12) {
					kiemTraTinhHopLe = false;
					JOptionPane.showMessageDialog(this, "Điểm sinh viên phải là số");
				}

				if (kiemTraTinhHopLe) {
					System.out.println("Tất cả field đã hợp lệ đang đợi update");
					String updateDiemSinhVien = "Update grade set tienganh = ? ,tinhoc = ?,gdtc = ? where masv = ?";
					PreparedStatement updateStm = con.prepareStatement(updateDiemSinhVien);
					updateStm.setFloat(1, Float.parseFloat(this.txtDiemTiengAnh.getText()));
					updateStm.setFloat(2, Float.parseFloat(this.txtDiemTinHoc.getText()));
					updateStm.setFloat(3, Float.parseFloat(this.txtDiemGDTC.getText()));
					updateStm.setString(4, maSinhVien);
					int row = updateStm.executeUpdate();
					if (row <= 0) {
						JOptionPane.showMessageDialog(this, "Lỗi không Update được sinh viên");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật được " + row + " sinh viên");
						Notification.InsertMess(QuyenChung, "Đã Cập nhật 1  Sinh Vien   "+maSinhVien);
						this.hamChayDauChuongtrinh();
					}
				}
			} else {
				System.out.println("Sinh viên chưa tồn tại ...");
				int hoi = JOptionPane.showConfirmDialog(this,
						"Sinh viên  " + maSinhVien + " chưa tồn tại điểm nào bạn có muốn chèn điểm vào không ?",
						"thêm điểm hay không ?", 0);
				if (hoi != 0) {
					System.out.println("Hủy bỏ tác vụ thêm sinh viên ...");
					return;
				}

				this.themSinhVienMoi();
			}
		}

	}

	private void themSinhVienMoi() throws SQLException, ClassNotFoundException {
		String maSinhVien = this.txtMaSv.getText().trim();
		if (maSinhVien.equals("")) {
			JOptionPane.showMessageDialog(this, "Chọn sinh viên trong bảng hoặc nhập mã sinh viên để xóa");
		} else {
			Connection con = DatabaseHelper.getConnection();
			String check = "";
			String sqltontai = "select masv From grade WHERE masv = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sqltontai);
			preparedStatement.setString(1, maSinhVien);

			for (ResultSet rs = preparedStatement.executeQuery(); rs.next(); check = rs.getString(1)) {
				;
			}

			if (check.equals("")) {
				boolean kiemTraTinhHopLe = true;
				if (this.txtMaSv.getText().length() > 7 || this.txtMaSv.getText().length() < 7) {
					kiemTraTinhHopLe = false;
					JOptionPane.showMessageDialog(this, "Mã sinh viên bao gồm 7 kí tự");
				}

				try {
					float diemTa = Float.parseFloat(this.txtDiemTiengAnh.getText());
					float diemTin = Float.parseFloat(this.txtDiemTinHoc.getText());
					float diemGDTC = Float.parseFloat(this.txtDiemGDTC.getText());
					if (diemGDTC > 10.0F || diemTa > 10.0F || diemTin > 10.0F || diemGDTC < 0.0F || diemTa < 0.0F
							|| diemTin < 0.0F) {
						kiemTraTinhHopLe = false;
						JOptionPane.showMessageDialog(this, "Điểm Không quá 10 và khong nhỏ hơn 0");
					}
				} catch (Exception var11) {
					kiemTraTinhHopLe = false;
					JOptionPane.showMessageDialog(this, "Điểm sinh viên phải là số");
				}

				if (kiemTraTinhHopLe) {
					System.out.println("Tất cả field đã hợp lệ đang đợi update");
					String themDiemSinhVien = "insert into grade values (?,?,?,?)";
					PreparedStatement themdiem = con.prepareStatement(themDiemSinhVien);
					themdiem.setFloat(2, Float.parseFloat(this.txtDiemTiengAnh.getText()));
					themdiem.setFloat(3, Float.parseFloat(this.txtDiemTinHoc.getText()));
					themdiem.setFloat(4, Float.parseFloat(this.txtDiemGDTC.getText()));
					themdiem.setString(1, maSinhVien);
					int row = themdiem.executeUpdate();
					if (row <= 0) {
						JOptionPane.showMessageDialog(this, "Lỗi không Update được sinh viên");
					} else {
						JOptionPane.showMessageDialog(this, "thêm được " + row + " sinh viên");
						Notification.InsertMess(QuyenChung, "Đã Thêm 1 Sinh Vien   "+maSinhVien);
						this.hamChayDauChuongtrinh();
					}
				}
			} else {
				this.updateSinhVien();
			}
		}

	}

	private void next() {
		if (this.soDem == this.listStudentsDiem.size() - 1) {
			this.soDem = 0;
		} else {
			++this.soDem;
		}

		this.FillListToForm(this.soDem);
	}

	private void prev() {
		if (this.soDem == 0) {
			this.soDem = this.listStudentsDiem.size() - 1;
		} else {
			--this.soDem;
		}

		this.FillListToForm(this.soDem);
	}

	private void first() {
		this.soDem = 0;
		this.FillListToForm(this.soDem);
	}

	private void last() {
		this.soDem = this.listStudentsDiem.size() - 1;
		this.FillListToForm(this.soDem);
	}

	private void xoaTrangNutSearch() {
		this.txtSearch.setText("");
	}

	private void xoaTrang() {
		this.txtSearch.setText("Nhập mã Sinh viên ");
		this.txtDiemGDTC.setText("");
		this.txtDiemTB.setText("...");
		this.txtHoTen.setText("");
		this.txtDiemTinHoc.setText("");
		this.txtDiemTiengAnh.setText("");
		this.txtMaSv.setText("");
	}

	public quanLiDiemSinhVienForm() {
		this.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.add(panel);
		panel.setLayout((LayoutManager) null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 807, 45);
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		JLabel lblStudentScoreManagement = new JLabel("Quản lí điểm sinh viên ");
		lblStudentScoreManagement.setForeground(new Color(255, 128, 0));
		lblStudentScoreManagement.setFont(new Font("Tahoma", 1, 25));
		panel_1.add(lblStudentScoreManagement);
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(45, 61, 742, 53);
		panel_2.setBorder(new EmptyBorder(10, 70, 10, 50));
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2);
		panel_2.setLayout((LayoutManager) null);
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(70, 10, 622, 33);
		panel_9.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_9);
		panel_9.setLayout((LayoutManager) null);
		this.txtSearch = new JTextField();
		this.txtSearch.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					quanLiDiemSinhVienForm.this.search();
				}

			}
		});
		this.txtSearch.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quanLiDiemSinhVienForm.this.xoaTrangNutSearch();
			}
		});
		this.txtSearch.setText("Nhập mã sinh viên ");
		this.txtSearch.setBounds(133, 0, 263, 33);
		panel_9.add(this.txtSearch);
		this.txtSearch.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm ");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quanLiDiemSinhVienForm.this.search();
			}
		});
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_2.setFont(new Font("Tahoma", 0, 14));
		lblNewLabel_2.setHorizontalAlignment(0);
		lblNewLabel_2.setBounds(395, 0, 74, 33);
		panel_9.add(lblNewLabel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(50, 128, 754, 324);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.add(panel_3);
		panel_3.setLayout((LayoutManager) null);
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(23, 10, 513, 225);
		panel_4.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_4);
		panel_4.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1 = new JLabel("Họ và Tên : ");
		lblNewLabel_1.setBounds(51, 10, 89, 20);
		lblNewLabel_1.setFont(new Font("Times New Roman", 0, 16));
		panel_4.add(lblNewLabel_1);
		JLabel lblNewLabel_1_1 = new JLabel("Mã Sinh Viên : ");
		lblNewLabel_1_1.setBounds(29, 57, 107, 20);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", 0, 16));
		panel_4.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_2 = new JLabel("Điểm Tiếng Anh : ");
		lblNewLabel_1_2.setBounds(29, 83, 130, 20);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", 0, 16));
		panel_4.add(lblNewLabel_1_2);
		JLabel lblNewLabel_1_3 = new JLabel("Điểm Tin Học : ");
		lblNewLabel_1_3.setBounds(29, 109, 112, 22);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", 0, 16));
		panel_4.add(lblNewLabel_1_3);
		JLabel lblNewLabel_1_4 = new JLabel("Điểm GDTC :");
		lblNewLabel_1_4.setBounds(29, 137, 94, 20);
		lblNewLabel_1_4.setFont(new Font("Times New Roman", 0, 16));
		panel_4.add(lblNewLabel_1_4);
		this.txtHoTen = new JLabel("");
		this.txtHoTen.setBounds(146, -2, 357, 40);
		this.txtHoTen.setForeground(new Color(255, 0, 0));
		this.txtHoTen.setHorizontalAlignment(0);
		this.txtHoTen.setFont(new Font("Times New Roman", 3, 24));
		panel_4.add(this.txtHoTen);
		this.txtMaSv = new JTextField();
		this.txtMaSv.setEditable(false);
		this.txtMaSv.setHorizontalAlignment(2);
		this.txtMaSv.setFont(new Font("Times New Roman", 0, 15));
		this.txtMaSv.setBounds(183, 58, 298, 19);
		panel_4.add(this.txtMaSv);
		this.txtMaSv.setColumns(10);
		this.txtMaSv.setBackground(Color.WHITE);
		this.txtDiemTiengAnh = new JTextField();
		this.txtDiemTiengAnh.setFont(new Font("Times New Roman", 0, 13));
		this.txtDiemTiengAnh.setBounds(183, 83, 298, 19);
		this.txtDiemTiengAnh.setColumns(10);
		panel_4.add(this.txtDiemTiengAnh);
		this.txtDiemTinHoc = new JTextField();
		this.txtDiemTinHoc.setFont(new Font("Times New Roman", 0, 13));
		this.txtDiemTinHoc.setBounds(183, 113, 298, 19);
		this.txtDiemTinHoc.setColumns(10);
		panel_4.add(this.txtDiemTinHoc);
		this.txtDiemGDTC = new JTextField();
		this.txtDiemGDTC.setFont(new Font("Times New Roman", 0, 13));
		this.txtDiemGDTC.setBounds(183, 140, 298, 19);
		this.txtDiemGDTC.setColumns(10);
		panel_4.add(this.txtDiemGDTC);
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(60, 458, 727, 157);
		panel_7.setBackground(new Color(255, 255, 255));
		panel.add(panel_7);
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(546, 0, 164, 235);
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lblNewLabel_1_5_1 = new JLabel("Điểm Trung Bình : ");
		lblNewLabel_1_5_1.setBounds(183, 187, 135, 38);
		lblNewLabel_1_5_1.setFont(new Font("Times New Roman", 2, 17));
		panel_4.add(lblNewLabel_1_5_1);
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(365, 187, 116, 38);
		panel_8.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_8);
		this.txtDiemTB = new JLabel("...");
		this.txtDiemTB.setForeground(new Color(255, 0, 0));
		this.txtDiemTB.setFont(new Font("Times New Roman", 0, 27));
		panel_8.add(this.txtDiemTB);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(4, 1, 0, 10));
		JButton btnNewButton_4 = new RoundedButton("New", 10, 15587481);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLiDiemSinhVienForm.this.xoaTrang();
			}
		});
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		panel_5.add(btnNewButton_4);
		JButton btnNewButton_6 = new RoundedButton("Save ", 10, 15587481);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					quanLiDiemSinhVienForm.this.themSinhVienMoi();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnNewButton_6.setBackground(new Color(255, 255, 255));
		panel_5.add(btnNewButton_6);
		JButton btnNewButton_5 = new RoundedButton("Delete ", 10, 15587481);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					quanLiDiemSinhVienForm.this.deleteSinhVienCoDiem();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnNewButton_5.setBackground(new Color(255, 255, 255));
		panel_5.add(btnNewButton_5);
		JButton btnUpdate = new RoundedButton("Update ", 10, 15587481);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					quanLiDiemSinhVienForm.this.updateSinhVien();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnUpdate.setBackground(new Color(255, 255, 255));
		panel_5.add(btnUpdate);
		JLabel lblNewLabel_3 = new JLabel("Top 3 sinh viên có điểm cao nhất");
		lblNewLabel_3.setBounds(10, 295, 734, 29);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(0);
		lblNewLabel_3.setForeground(new Color(-11106608));
		lblNewLabel_3.setFont(new Font("Segoe UI Semilight", 2, 20));
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(92, 258, 444, 40);
		panel_3.add(panel_6);
		panel_6.setBorder(new EmptyBorder(5, 10, 5, 10));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setLayout(new GridLayout(1, 4, 10, 5));
		JButton btnNewButton_3 = new RoundedButton("First", 20, 15587481);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLiDiemSinhVienForm.this.first();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", 2, 12));
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		panel_6.add(btnNewButton_3);
		JButton btnNewButton_2 = new RoundedButton("Prev", 20, 15587481);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLiDiemSinhVienForm.this.prev();
			}
		});
		btnNewButton_2.setText("Prev ");
		btnNewButton_2.setFont(new Font("Tahoma", 2, 12));
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		panel_6.add(btnNewButton_2);
		JButton btnNewButton_1 = new RoundedButton("next", 20, 15587481);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLiDiemSinhVienForm.this.next();
			}
		});
		btnNewButton_1.setText("Next ");
		btnNewButton_1.setFont(new Font("Tahoma", 2, 12));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		panel_6.add(btnNewButton_1);
		JButton btnNewButton = new RoundedButton("last", 20, 15587481);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLiDiemSinhVienForm.this.last();
			}
		});
		btnNewButton.setText("Last ");
		btnNewButton.setFont(new Font("Tahoma", 2, 12));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		panel_6.add(btnNewButton);
		panel_7.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		panel_7.add(scrollPane, "cell 0 0,grow");
		this.table = new JTable();
		this.table.getTableHeader().setBackground(new Color(-11106608));
		this.table.getTableHeader().setForeground(Color.white);
		this.table.getTableHeader().setOpaque(false);
		this.table.setRowHeight(40);
		this.table.getTableHeader().setFont(new Font("Tahoma", 0, 11));
		this.table.setSelectionBackground(new Color(189, 195, 199));
		scrollPane.setViewportView(this.table);
		this.hamChayDauChuongtrinh();
	}
}
