package views;

import Helper.Converter;
import Helper.DatabaseHelper;
import Helper.RoundedButton;
import Helper.RoundedUtils;
import models.Notification;
import models.Student;
import models.UserDao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class formquanlithongtinsv extends JPanel {
	private Color colorKhungTextField = new Color(127, 143, 166);
	private Color colorKhungBtn = new Color(25, 42, 86);
	private JFileChooser jfc;
	private Image image;
	private JTextField txtMaSv;
	private JTextField txtFullName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private final ButtonGroup btnGroupgioiTinh = new ButtonGroup();
	private JTable table;
	private JPanel panelHinhAnh;
	private JLabel txtHinhAnh;
	private JTextArea txtDiaChi;
	private DefaultTableModel model;
	private File filehinhAnh;
	public String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	public String fullNameRegex = "^[\\p{L}]+([\\p{Zs}\\p{L}]+)*$";
	List<Student> listStudents = new ArrayList();
	
	//
	private String QuyenChung =UserDao.getRoleQuyen(frame1.returnTenTk());

	public void hamChayDauChuongtrinh() {
		try {
			try {
				this.fillDataToList();
			} catch (ClassNotFoundException var2) {
				System.out.println(var2.getMessage());
			}

			this.initTable();
			this.fillListToTable();
		} catch (SQLException var3) {
			System.out.println(var3.getMessage());
		}

	}

	private void fillDataToList() throws SQLException, ClassNotFoundException {
		this.listStudents.removeAll(this.listStudents);
		Connection con = DatabaseHelper.getConnection();
		Statement stmGetDataToList = con.createStatement();
		String sql = "Select * from Students";
		ResultSet rs = stmGetDataToList.executeQuery(sql);

		while (rs.next()) {
			this.listStudents.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
					rs.getString(5), rs.getString(6), rs.getString(7)));
		}

	}

	private void fillListToTable() {
		Iterator var2 = this.listStudents.iterator();

		while (var2.hasNext()) {
			Student student = (Student) var2.next();
			this.model.addRow(new Object[] { student.getMaSV(), student.getHoTen(), student.getEmail(),
					student.getSdt(), student.getGioiTinh(), student.getDiaChi(), student.getHinhAnh() });
		}

		this.table.setModel(this.model);
	}

	public void initTable() {
		this.model = (DefaultTableModel) this.table.getModel();
		this.model.setColumnIdentifiers(new String[] { "Mã sinh viên", "Họ Tên", "Email", "Số điện thoại", "Giới Tính",
				"Địa chỉ", "Hình ảnh" });
		this.model.setRowCount(0);
	}

	public boolean checkVal() {
		return !this.txtMaSv.getText().equals("") && !this.txtFullName.getText().equals("")
				&& !this.txtEmail.getText().equals("") && !this.txtPhone.getText().equals("");
	}

	public void addImg() {
		try {
			this.jfc = new JFileChooser("D:\\JAVA\\ASSQLNHANVIEN\\src\\hinhanh");
			this.jfc.showOpenDialog((Component) null);
			File file = this.jfc.getSelectedFile();
			this.image = ImageIO.read(file);
			this.txtHinhAnh.setText("");
			int width = this.panelHinhAnh.getWidth();
			int height = this.panelHinhAnh.getHeight();
			this.txtHinhAnh.setIcon(new ImageIcon(this.image.getScaledInstance(width - 6, height - 6, 0)));
			System.out.println(file);
			this.filehinhAnh = file;
		} catch (Exception var4) {
			this.txtHinhAnh.setText("no img ...");
			this.txtHinhAnh.setIcon((Icon) null);
			this.filehinhAnh = null;
			System.out.println("cancel ! ");
		}

	}

	public void hamChon() {
		int viTriHang = this.table.getSelectedRow();
		Student st = (Student) this.listStudents.get(viTriHang);
		this.txtMaSv.setText(st.getMaSV());
		String gioiTinh = st.getGioiTinh();
		if (gioiTinh.equals("Nam")) {
			this.rdbtnNam.setSelected(true);
		} else {
			this.rdbtnNu.setSelected(true);
		}

		this.txtDiaChi.setText(st.getDiaChi());
		this.txtFullName.setText(st.getHoTen());
		this.txtPhone.setText(Integer.toString(st.getSdt()));
		this.txtEmail.setText(st.getEmail());
		File files = new File(st.getHinhAnh());

		try {
			this.txtHinhAnh.setText("");
			this.filehinhAnh = files;
			this.image = ImageIO.read(files);
			int width = this.panelHinhAnh.getWidth();
			int height = this.panelHinhAnh.getHeight();
			this.txtHinhAnh.setIcon(new ImageIcon(this.image.getScaledInstance(width - 6, height - 6, 0)));
		} catch (IOException var7) {
			this.txtHinhAnh.setIcon((Icon) null);
			this.txtHinhAnh.setText("no img...");
		}

	}

	private void newSinhVien() {
		this.txtHinhAnh.setText("no img...");
		this.txtHinhAnh.setIcon((Icon) null);
		this.txtFullName.setText("");
		this.txtDiaChi.setText("");
		this.txtPhone.setText("");
		this.txtMaSv.setText("");
		this.rdbtnNam.setSelected(true);
		this.txtEmail.setText("");
	}

	private void addSinhvien() throws SQLException, ClassNotFoundException {
		if (!this.checkVal()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ");
		} else {
			boolean check = true;

			try {
				int sdt = Integer.parseInt(this.txtPhone.getText());
				if (this.txtPhone.getText().length() > 11) {
					JOptionPane.showMessageDialog(this, "Độ dài tối đa của số điện thoại không quá 11 số !");
					check = false;
				} else if (!this.txtEmail.getText().matches(this.emailRegex)) {
					JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ!");
					check = false;
				} else if (!this.txtFullName.getText().matches(this.fullNameRegex)) {
					JOptionPane.showMessageDialog(this, "Định dạng họ tên không hợp lệ!");
					check = false;
				} else if (this.txtMaSv.getText().length() <= 7 && this.txtMaSv.getText().length() >= 7) {
					Connection con = DatabaseHelper.getConnection();
					String laymasinhvien = "Select masv from students";
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery(laymasinhvien);

					while (rs.next()) {
						if (rs.getString(1).equals(this.txtMaSv.getText())) {
							JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại!");
							check = false;
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Mã sinh viên bắt buộc 7 kí tự!");
					check = false;
				}
			} catch (Exception var8) {
				JOptionPane.showMessageDialog(this, "Lỗi định dạng số điện thoại");
				check = false;
			}

			if (check) {
				Connection con = DatabaseHelper.getConnection();
				String sqlInsertStudent = "INSERT INTO students VALUES (?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = con.prepareStatement(sqlInsertStudent);
				preparedStatement.setString(1, Converter.removeDiacritics(this.txtMaSv.getText()));
				preparedStatement.setString(2, this.txtFullName.getText());
				preparedStatement.setString(3, this.txtEmail.getText());
				preparedStatement.setInt(4, Integer.parseInt(this.txtPhone.getText()));
				String GioiTinh = "";
				if (this.rdbtnNam.isSelected()) {
					GioiTinh = "Nam";
				} else {
					GioiTinh = "Nữ";
				}

				preparedStatement.setString(5, GioiTinh);
				preparedStatement.setString(6, this.txtDiaChi.getText());
				String hinhanh = "";
				if (this.filehinhAnh == null) {
					hinhanh = "no img";
				} else {
					hinhanh = this.filehinhAnh.getPath();
				}

				preparedStatement.setString(7, hinhanh);
				int rs = preparedStatement.executeUpdate();
				if (rs <= 0) {
					JOptionPane.showMessageDialog(this, "Lỗi không thêm được sinh viên");
				} else {
					JOptionPane.showMessageDialog(this, "Thêm được " + rs + " sinh viên");
					Notification.InsertMess(QuyenChung, "Đã thêm 1 Sinh Viên có mã "+Converter.removeDiacritics(this.txtMaSv.getText()));
					this.hamChayDauChuongtrinh();
				}
			}
		}

	}

	private void deleteSinhVien() throws SQLException, ClassNotFoundException {
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
			String sql = "delete FROM students WHERE masv = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maSinhVien);
			int rs = preparedStatement.executeUpdate();
			if (rs <= 0) {
				JOptionPane.showMessageDialog(this, "Lỗi không xóa được sinh viên");
			} else {
				JOptionPane.showMessageDialog(this, "Đã xóa sinh viên " + maSinhVien + "\n" + "row = " + rs);
				Notification.InsertMess(QuyenChung, "Đã xóa 1 Sinh Viên có mã "+maSinhVien);
				this.hamChayDauChuongtrinh();
			}
		}

	}

	private void updateSinhVien() throws SQLException, ClassNotFoundException {
		String maSinhVien = this.txtMaSv.getText().trim();
		if (maSinhVien.equals("")) {
			JOptionPane.showMessageDialog(this, "Chọn sinh viên trong bảng hoặc nhập mã sinh viên để update");
		} else if (!this.checkVal()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ");
		} else {
			boolean check = true;
			boolean checkTonTai = false;

			int xacNhanXoa;
			Connection con;
			String laymasinhvien;
			try {
				xacNhanXoa = Integer.parseInt(this.txtPhone.getText());
				if (this.txtPhone.getText().length() > 11) {
					JOptionPane.showMessageDialog(this, "Độ dài tối đa của số điện thoại không quá 11 số !");
					check = false;
				} else if (!this.txtEmail.getText().matches(this.emailRegex)) {
					JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ!");
					check = false;
				} else if (!this.txtFullName.getText().matches(this.fullNameRegex)) {
					JOptionPane.showMessageDialog(this, "Định dạng họ tên không hợp lệ!");
					check = false;
				} else if (this.txtMaSv.getText().length() <= 7 && this.txtMaSv.getText().length() >= 7) {
					con = DatabaseHelper.getConnection();
					laymasinhvien = "Select masv from students";
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery(laymasinhvien);

					while (rs.next()) {
						if (rs.getString(1).equals(this.txtMaSv.getText())) {
							checkTonTai = true;
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Mã sinh viên bắt buộc 7 kí tự!");
					check = false;
				}
			} catch (Exception var11) {
				JOptionPane.showMessageDialog(this, "Lỗi định dạng số điện thoại");
				check = false;
			}

			if (check && checkTonTai) {
				xacNhanXoa = JOptionPane.showConfirmDialog(this,
						"Bạn có muốn Cập nhật  sinh viên " + maSinhVien + " không ?", "Cập Nhật hay không ?", 0);
				if (xacNhanXoa != 0) {
					return;
				}

				con = DatabaseHelper.getConnection();
				laymasinhvien = "Update students set hoten = ? , email  = ? , sdt = ? , gioitinh = ? , diachi = ?  , hinhanh = ? where masv = ?";
				PreparedStatement preparedStatement = con.prepareStatement(laymasinhvien);
				preparedStatement.setString(7, maSinhVien);
				preparedStatement.setString(1, this.txtFullName.getText());
				preparedStatement.setString(2, this.txtEmail.getText());
				preparedStatement.setInt(3, Integer.parseInt(this.txtPhone.getText()));
				String GioiTinh = "";
				if (this.rdbtnNam.isSelected()) {
					GioiTinh = "Nam";
				} else {
					GioiTinh = "Nữ";
				}

				preparedStatement.setString(4, GioiTinh);
				preparedStatement.setString(5, this.txtDiaChi.getText());
				String hinhanh = "";
				if (this.filehinhAnh == null) {
					hinhanh = "no img";
				} else {
					hinhanh = this.filehinhAnh.getPath();
				}

				preparedStatement.setString(6, hinhanh);
				int rs = preparedStatement.executeUpdate();
				if (rs <= 0) {
					JOptionPane.showMessageDialog(this, "Lỗi không thêm được sinh viên");
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật được " + rs + " sinh viên");
					Notification.InsertMess(QuyenChung, "Đã cập Nhật 1 Sinh Viên có mã "+maSinhVien);
					this.hamChayDauChuongtrinh();
				}
			} else if (check && !checkTonTai) {
				xacNhanXoa = JOptionPane.showConfirmDialog(this,
						"Mã sinh viên " + maSinhVien + " chưa tồn tại để cập nhật Bạn có muốn tạo mới không ?",
						" Tạo mới hay không ?", 0);
				if (xacNhanXoa != 0) {
					return;
				}

				this.addSinhvien();
			}
		}

	}

	public formquanlithongtinsv() {
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.add(panel, "Center");
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint("North", panel_1, 10, "North", panel);
		sl_panel.putConstraint("West", panel_1, 10, "West", panel);
		sl_panel.putConstraint("East", panel_1, 880, "West", panel);
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		JLabel lblNewLabel = new JLabel("Quản lí thông tin sinh viên ");
		lblNewLabel.setForeground(new Color(255, 128, 0));
		lblNewLabel.setFont(new Font("Tahoma", 1, 25));
		panel_1.add(lblNewLabel);
		JPanel panel_3 = new JPanel();
		sl_panel.putConstraint("North", panel_3, 57, "North", panel);
		sl_panel.putConstraint("West", panel_3, 10, "West", panel);
		sl_panel.putConstraint("South", panel_3, 451, "North", panel);
		sl_panel.putConstraint("East", panel_3, 924, "West", panel);
		panel_3.setBackground(new Color(255, 255, 255));
		panel.add(panel_3);
		SpringLayout sl_panel_3 = new SpringLayout();
		panel_3.setLayout(sl_panel_3);
		JPanel panel_2 = new JPanel();
		sl_panel_3.putConstraint("South", panel_2, 394, "North", panel_3);
		sl_panel_3.putConstraint("East", panel_2, 519, "West", panel_3);
		panel_2.setBackground(new Color(255, 255, 255));
		sl_panel_3.putConstraint("North", panel_2, 10, "North", panel_3);
		sl_panel_3.putConstraint("West", panel_2, 10, "West", panel_3);
		panel_3.add(panel_2);
		panel_2.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1 = new JLabel("MÃ SINH VIÊN ");
		lblNewLabel_1.setBounds(10, 10, 104, 19);
		lblNewLabel_1.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1);
		JLabel lblNewLabel_1_1 = new JLabel("HỌ TÊN ");
		lblNewLabel_1_1.setBounds(10, 52, 58, 19);
		lblNewLabel_1_1.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_2 = new JLabel("EMAIL");
		lblNewLabel_1_2.setBounds(10, 100, 42, 19);
		lblNewLabel_1_2.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1_2);
		JLabel lblNewLabel_1_3 = new JLabel("SỐ ĐIỆN THOẠI");
		lblNewLabel_1_3.setBounds(10, 147, 108, 19);
		lblNewLabel_1_3.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1_3);
		JLabel lblNewLabel_1_4 = new JLabel("GIỚI TÍNH ");
		lblNewLabel_1_4.setBounds(10, 196, 78, 19);
		lblNewLabel_1_4.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1_4);
		JLabel lblNewLabel_1_6 = new JLabel("ĐỊA CHỈ ");
		lblNewLabel_1_6.setBounds(10, 242, 59, 19);
		lblNewLabel_1_6.setFont(new Font("Tahoma", 0, 15));
		panel_2.add(lblNewLabel_1_6);
		this.txtMaSv = new JTextField();
		this.txtMaSv.setBounds(168, 12, 309, 19);
		RoundedUtils.setRounded(this.txtMaSv, 4, this.colorKhungTextField);
		panel_2.add(this.txtMaSv);
		this.txtMaSv.setColumns(10);
		this.txtFullName = new JTextField();
		this.txtFullName.setBounds(168, 52, 309, 19);
		RoundedUtils.setRounded(this.txtFullName, 4, this.colorKhungTextField);
		this.txtFullName.setColumns(10);
		panel_2.add(this.txtFullName);
		this.txtEmail = new JTextField();
		this.txtEmail.setBounds(168, 100, 309, 19);
		RoundedUtils.setRounded(this.txtEmail, 4, this.colorKhungTextField);
		this.txtEmail.setColumns(10);
		panel_2.add(this.txtEmail);
		this.txtPhone = new JTextField();
		this.txtPhone.setBounds(168, 147, 309, 19);
		RoundedUtils.setRounded(this.txtPhone, 4, this.colorKhungTextField);
		this.txtPhone.setColumns(10);
		panel_2.add(this.txtPhone);
		this.rdbtnNam = new JRadioButton("NAM");
		this.rdbtnNam.setBounds(171, 196, 53, 25);
		this.rdbtnNam.setSelected(true);
		this.btnGroupgioiTinh.add(this.rdbtnNam);
		this.rdbtnNam.setBackground(new Color(255, 255, 255));
		this.rdbtnNam.setFont(new Font("Tahoma", 0, 13));
		panel_2.add(this.rdbtnNam);
		this.rdbtnNu = new JRadioButton("NỮ");
		this.rdbtnNu.setBounds(230, 196, 43, 25);
		this.btnGroupgioiTinh.add(this.rdbtnNu);
		this.rdbtnNu.setBackground(new Color(255, 255, 255));
		this.rdbtnNu.setFont(new Font("Tahoma", 0, 13));
		panel_2.add(this.rdbtnNu);
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(168, 242, 309, 132);
		panel_4.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.white);
		panel_4.add(scrollPane, "cell 0 0,grow");
		this.txtDiaChi = new JTextArea();
		this.txtDiaChi.setWrapStyleWord(true);
		this.txtDiaChi.setBackground(Color.white);
		this.txtDiaChi.setLineWrap(true);
		scrollPane.setViewportView(this.txtDiaChi);
		JPanel panel_5 = new JPanel();
		sl_panel_3.putConstraint("North", panel_5, 10, "North", panel_3);
		sl_panel_3.putConstraint("West", panel_5, 6, "East", panel_2);
		sl_panel_3.putConstraint("South", panel_5, 0, "South", panel_3);
		sl_panel_3.putConstraint("East", panel_5, -50, "East", panel_3);
		panel_5.setBackground(new Color(255, 255, 255));
		panel_3.add(panel_5);
		this.panelHinhAnh = new JPanel();
		this.panelHinhAnh.setBounds(10, 10, 271, 240);
		RoundedUtils.setRounded(this.panelHinhAnh, 30, this.colorKhungTextField);
		this.panelHinhAnh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				formquanlithongtinsv.this.addImg();
			}
		});
		panel_5.setLayout((LayoutManager) null);
		this.panelHinhAnh.setBorder(new EtchedBorder(1, (Color) null, (Color) null));
		this.panelHinhAnh.setBackground(new Color(255, 255, 255));
		panel_5.add(this.panelHinhAnh);
		SpringLayout sl_panelHinhAnh = new SpringLayout();
		this.panelHinhAnh.setLayout(sl_panelHinhAnh);
		this.txtHinhAnh = new JLabel("no image  ");
		sl_panelHinhAnh.putConstraint("North", this.txtHinhAnh, 0, "North", this.panelHinhAnh);
		sl_panelHinhAnh.putConstraint("East", this.txtHinhAnh, 0, "East", this.panelHinhAnh);
		this.txtHinhAnh.setFont(new Font("Tahoma", 2, 12));
		this.panelHinhAnh.add(this.txtHinhAnh);
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 248, 287, 131);
		panel_7.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_7.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 2, 5, 10));
		JButton btnNewButton_1 = new RoundedButton("New ", 10, 15587481);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formquanlithongtinsv.this.newSinhVien();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", 0, 15));
		panel_7.add(btnNewButton_1);
		JButton btnNewButton_2 = new RoundedButton("Save ", 10, 15587481);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					formquanlithongtinsv.this.addSinhvien();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", 0, 15));
		panel_7.add(btnNewButton_2);
		JButton btnNewButton = new RoundedButton("Delete ", 10, 15587481);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					formquanlithongtinsv.this.deleteSinhVien();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", 0, 15));
		panel_7.add(btnNewButton);
		JButton btnNewButton_3 = new RoundedButton("Update ", 10, 15587481);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					formquanlithongtinsv.this.updateSinhVien();
				} catch (SQLException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				} catch (ClassNotFoundException var4) {
					JOptionPane.showMessageDialog((Component) null, var4.getMessage());
				}

			}
		});
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", 0, 15));
		panel_7.add(btnNewButton_3);
		JPanel panel_8 = new JPanel();
		sl_panel.putConstraint("North", panel_8, 6, "South", panel_3);
		sl_panel.putConstraint("West", panel_8, 20, "West", panel);
		sl_panel.putConstraint("South", panel_8, 626, "North", panel);
		sl_panel.putConstraint("East", panel_8, -10, "East", panel);
		panel_8.setBackground(Color.white);
		panel.add(panel_8);
		panel_8.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.white);
		panel_8.add(scrollPane_1, "cell 0 0,grow");
		this.table = new JTable();
		this.table.getTableHeader().setBackground(new Color(-11106608));
		this.table.getTableHeader().setForeground(Color.white);
		this.table.getTableHeader().setOpaque(false);
		this.table.getTableHeader().setFont(new Font("Tahoma", 0, 11));
		this.table.setSelectionBackground(new Color(189, 195, 199));
		this.table.setEnabled(true);
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				formquanlithongtinsv.this.hamChon();
			}
		});
		this.table.setFont(new Font("Tahoma", 0, 10));
		scrollPane_1.setViewportView(this.table);
		this.hamChayDauChuongtrinh();
	}
}