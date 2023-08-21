package views;

import Helper.RoundedUtils;
import models.UserDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class frame1 extends JFrame {
	private static JPanel mainFrame;
	private JPanel contentPane;
	private boolean isMenuVisible = true;
	private JTextField textField;
	private JLabel txtNgayVaGio;
	private static JLabel txtDangDang_DangXuat;
	private JLabel txtVeChungToi;
	private JLabel txtQuanKLiDiem;
	private JLabel txtQuanLiSinhVien;
	private JLabel txtTraCuuDiem;
	private JLabel txtTrangChu;
	private JLabel txtTenTaiKhoan;
	private JLabel txtTenRole;
	private Color color = new Color(189, 195, 199);
	private Color khungbogoc1 = new Color(189, 195, 199);
	private String tenTk;
	private static String role;
	private static homePage homePage;
	private aboutus aboutus;
	private formNotification formNotification;
	private traCuuDiemSinhVien traCuuDiemSinhVien;
	private hoSoQuanLiFormuUser hoSoQuanLiFormuUser;
	private hoSoQuanLiFormAdmin hosoAdmin;
	private KhongDuQuyenHan khongDuQuyenHan;
	private formquanlithongtinsv formquanlithongtinsv;
	private quanLiDiemSinhVienForm diemSinhVienForm;
	private static loginForm loginForm = new loginForm();

	

	public void hamChayDauChuongTrinh() {
		this.threadNgayVaGio();
		this.fillDataListUser();
		loginForm.setVisible(true);
		loginForm.rememberMe();
		this.trangChu();
	}

	private void fillDataListUser() {
		new UserDao();
	}

	public static String returnTenTk() {
		return role;
	}

	private void setTenTk() {
		this.tenTk = loginForm.saveTk();
		role = loginForm.tenUser();
		this.txtTenTaiKhoan
				.setText("<html><font color='white' style='font-size: null;'>Hi !</font> " + role + "</html>");
		this.txtTenRole.setText(this.tenTk);
		if (this.tenTk.equals("khách")) {
			txtDangDang_DangXuat.setText("Đăng Nhập");
		} else {
			txtDangDang_DangXuat.setText("Đăng Xuất");
		}

	}

	public void hamDangNhap() {
		loginForm.setVisible(true);
		loginForm.rememberMe();
		this.trangChu();
		txtDangDang_DangXuat.setText("Đăng Xuất");
	}

	public void hamDangXuat() {
		loginForm.tk = "khách";
		loginForm.role = "bạn";
		this.hamDangNhap();
		this.trangChu();
		txtDangDang_DangXuat.setText("Đăng Nhập");
	}

	public static void DangXuatDungChung() {
		loginForm.tk = "khách";
		loginForm.role = "bạn";
		loginForm.setVisible(true);
		loginForm.rememberMe();
		mainFrame.removeAll();
		homePage = new homePage();
		mainFrame.add(homePage, "Center");
		mainFrame.validate();
		homePage.setPreferredSize(mainFrame.getSize());
		txtDangDang_DangXuat.setText("Đăng Nhập");
	}

	public void threadNgayVaGio() {
		Thread thread = new Thread(() -> {
			while (true) {
				LocalDateTime currentTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				String formattedDateTime = currentTime.format(formatter);
				this.txtNgayVaGio.setText(formattedDateTime);

				try {
					Thread.sleep(1000L);
				} catch (InterruptedException var5) {
					System.out.println(var5.getMessage());
				}
			}
		});
		thread.start();
	}

	private void mouseEnteredVao(JLabel tenChuyenDong) {
		if (tenChuyenDong.getText().equals("Đăng Nhập")) {
			tenChuyenDong.setForeground(Color.red);
			tenChuyenDong.setFont(new Font("Times New Roman", 0, 20));
		} else if (tenChuyenDong.getText().equals("Đăng Xuất")) {
			tenChuyenDong.setForeground(Color.red);
			tenChuyenDong.setFont(new Font("Times New Roman", 0, 20));
		} else {
			tenChuyenDong.setForeground(new Color(84, 160, 255));
			tenChuyenDong.setFont(new Font("Times New Roman", 0, 20));
		}

	}

	private void mouseExitedRa(JLabel tenChuyenDong) {
		tenChuyenDong.setForeground(new Color(200, 214, 229));
		tenChuyenDong.setFont(new Font("Times New Roman", 0, 15));
	}

	public void trangChu() {
		mainFrame.removeAll();
		homePage = new homePage();
		mainFrame.add(homePage, "Center");
		mainFrame.validate();
		homePage.setPreferredSize(mainFrame.getSize());
		this.txtTrangChu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.color));
		txtDangDang_DangXuat.setBorder((Border) null);
		this.txtQuanKLiDiem.setBorder((Border) null);
		this.txtQuanLiSinhVien.setBorder((Border) null);
		this.txtVeChungToi.setBorder((Border) null);
		this.txtTraCuuDiem.setBorder((Border) null);
	}

	private void aboutUs() {
		mainFrame.removeAll();
		this.aboutus = new aboutus();
		mainFrame.add(this.aboutus, "Center");
		mainFrame.validate();
		homePage.setPreferredSize(mainFrame.getSize());
		this.txtTrangChu.setBorder((Border) null);
		txtDangDang_DangXuat.setBorder((Border) null);
		this.txtQuanKLiDiem.setBorder((Border) null);
		this.txtQuanLiSinhVien.setBorder((Border) null);
		this.txtVeChungToi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.color));
		this.txtTraCuuDiem.setBorder((Border) null);
	}

	private void thongTinSinhVien() {
		this.txtTrangChu.setBorder((Border) null);
		txtDangDang_DangXuat.setBorder((Border) null);
		this.txtQuanKLiDiem.setBorder((Border) null);
		this.txtQuanLiSinhVien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.color));
		this.txtVeChungToi.setBorder((Border) null);
		this.txtTraCuuDiem.setBorder((Border) null);
		if (this.tenTk.equals("admin")) {
			mainFrame.removeAll();
			this.formquanlithongtinsv = new formquanlithongtinsv();
			mainFrame.add(this.formquanlithongtinsv, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else if (this.tenTk.equals("teacher")) {
			mainFrame.removeAll();
			this.khongDuQuyenHan = new KhongDuQuyenHan();
			mainFrame.add(this.khongDuQuyenHan, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else if (this.tenTk.equals("cadres")) {
			mainFrame.removeAll();
			this.formquanlithongtinsv = new formquanlithongtinsv();
			mainFrame.add(this.formquanlithongtinsv, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else {
			mainFrame.removeAll();
			this.khongDuQuyenHan = new KhongDuQuyenHan();
			mainFrame.add(this.khongDuQuyenHan, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		}

	}

	private void quanLiDiemSinhVienForm() {
		this.txtTrangChu.setBorder((Border) null);
		txtDangDang_DangXuat.setBorder((Border) null);
		this.txtQuanKLiDiem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.color));
		this.txtQuanLiSinhVien.setBorder((Border) null);
		this.txtVeChungToi.setBorder((Border) null);
		this.txtTraCuuDiem.setBorder((Border) null);
		if (this.tenTk.equals("admin")) {
			mainFrame.removeAll();
			this.diemSinhVienForm = new quanLiDiemSinhVienForm();
			mainFrame.add(this.diemSinhVienForm, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else if (this.tenTk.equals("teacher")) {
			mainFrame.removeAll();
			this.diemSinhVienForm = new quanLiDiemSinhVienForm();
			mainFrame.add(this.diemSinhVienForm, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else if (this.tenTk.equals("cadres")) {
			mainFrame.removeAll();
			this.khongDuQuyenHan = new KhongDuQuyenHan();
			mainFrame.add(this.khongDuQuyenHan, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else {
			mainFrame.removeAll();
			this.khongDuQuyenHan = new KhongDuQuyenHan();
			mainFrame.add(this.khongDuQuyenHan, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		}

	}

	private void traCuuDiemSinhVien() {
		mainFrame.removeAll();
		this.traCuuDiemSinhVien = new traCuuDiemSinhVien();
		mainFrame.add(this.traCuuDiemSinhVien, "Center");
		mainFrame.validate();
		homePage.setPreferredSize(mainFrame.getSize());
		this.txtTrangChu.setBorder((Border) null);
		txtDangDang_DangXuat.setBorder((Border) null);
		this.txtQuanKLiDiem.setBorder((Border) null);
		this.txtQuanLiSinhVien.setBorder((Border) null);
		this.txtVeChungToi.setBorder((Border) null);
		this.txtTraCuuDiem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, this.color));
	}

	private void hosoTaiKhoan() {
		mainFrame.removeAll();
		if (this.tenTk.equals("admin")) {
			this.hosoAdmin = new hoSoQuanLiFormAdmin();
			mainFrame.add(this.hosoAdmin, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		} else {
			this.hoSoQuanLiFormuUser = new hoSoQuanLiFormuUser();
			mainFrame.add(this.hoSoQuanLiFormuUser, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		}

	}
	private void notification() {
		mainFrame.removeAll();
		if (this.tenTk.equals("admin")) {
			this.formNotification = new formNotification();
			mainFrame.add(this.formNotification, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
			
		} else {
			this.formNotification = new formNotification();
			mainFrame.add(this.formNotification, "Center");
			mainFrame.validate();
			homePage.setPreferredSize(mainFrame.getSize());
		}

	}

	public frame1() {
		this.setTitle("Phần Mềm Quản Lí Sinh Viên - PD07906");
		ImageIcon icon = new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\team-management.png");
		this.setIconImage(icon.getImage());
		this.setBackground(new Color(255, 255, 255));
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setBounds(100, 100, 1096, 711);
		this.setLocationRelativeTo((Component) null);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 255, 255));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		this.contentPane.add(panel, "Center");
		panel.setLayout((LayoutManager) null);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(44, 62, 80));
		panel_1.setBounds(0, 0, 236, 664);
		panel.add(panel_1);
		panel_1.setLayout((LayoutManager) null);
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		panel_3.setBackground(new Color(-12355384));
		panel_3.setBounds(0, 0, 236, 41);
		panel_1.add(panel_3);
		JLabel lblNewLabel = new JLabel("Danh Mục");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Source Sans Pro Black", 2, 24));
		panel_3.add(lblNewLabel);
		JPanel panel_4 = new JPanel();
		RoundedUtils.setRounded(panel_4, 20, this.khungbogoc1);
		panel_4.setBackground(new Color(44, 62, 80));
		panel_4.setBounds(10, 51, 216, 63);
		panel_1.add(panel_4);
		panel_4.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\user.png"));
		lblNewLabel_1.setBounds(23, 0, 72, 63);
		panel_4.add(lblNewLabel_1);
		this.txtTenTaiKhoan = new JLabel("xin chào bạn (user)");
		this.txtTenTaiKhoan.setFont(new Font("Times New Roman", 1, 18));
		this.txtTenTaiKhoan.setForeground(new Color(84, 160, 255));
		this.txtTenTaiKhoan.setBounds(65, 0, 151, 43);
		panel_4.add(this.txtTenTaiKhoan);
		JLabel lblNewLabel_2_1 = new JLabel("online ");
		lblNewLabel_2_1.setForeground(new Color(84, 160, 255));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", 0, 18));
		lblNewLabel_2_1.setBounds(65, 20, 151, 43);
		panel_4.add(lblNewLabel_2_1);
		JPanel panel_5 = new JPanel();
		RoundedUtils.setRounded(panel_5, 25, this.khungbogoc1);
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(10, 121, 216, 31);
		panel_1.add(panel_5);
		panel_5.setLayout((LayoutManager) null);
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(0);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("search");
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\Ampeross-Qetto-2-Search.24.png"));
		lblNewLabel_3.setBounds(184, 0, 32, 29);
		panel_5.add(lblNewLabel_3);
		this.textField = new JTextField();
		this.textField.setBounds(10, 4, 164, 25);
		panel_5.add(this.textField);
		this.textField.setForeground(new Color(255, 255, 255));
		this.textField.setBackground(new Color(44, 62, 80));
		this.textField.setBorder((Border) null);
		this.textField.setColumns(10);
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_4_1.setBackground(new Color(44, 62, 80));
		panel_4_1.setBounds(10, 162, 216, 347);
		panel_1.add(panel_4_1);
		panel_4_1.setLayout((LayoutManager) null);
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(2);
		lblNewLabel_4.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\working.png"));
		lblNewLabel_4.setBounds(10, 111, 32, 32);
		panel_4_1.add(lblNewLabel_4);
		this.txtQuanLiSinhVien = new JLabel("Quản Lí Sinh Viên");
		this.txtQuanLiSinhVien.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.this.txtQuanLiSinhVien);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.this.txtQuanLiSinhVien);
			}

			public void mouseClicked(MouseEvent e) {
				frame1.this.thongTinSinhVien();
			}
		});
		this.txtQuanLiSinhVien.setHorizontalAlignment(2);
		this.txtQuanLiSinhVien.setForeground(new Color(200, 214, 229));
		this.txtQuanLiSinhVien.setFont(new Font("Times New Roman", 0, 17));
		this.txtQuanLiSinhVien.setBounds(55, 111, 151, 32);
		panel_4_1.add(this.txtQuanLiSinhVien);
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setHorizontalAlignment(2);
		lblNewLabel_4_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\student-grades.png"));
		lblNewLabel_4_1.setBounds(10, 153, 45, 32);
		panel_4_1.add(lblNewLabel_4_1);
		this.txtQuanKLiDiem = new JLabel("Quản Lí Điểm ");
		this.txtQuanKLiDiem.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.this.txtQuanKLiDiem);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.this.txtQuanKLiDiem);
			}

			public void mouseClicked(MouseEvent e) {
				frame1.this.quanLiDiemSinhVienForm();
			}
		});
		this.txtQuanKLiDiem.setHorizontalAlignment(2);
		this.txtQuanKLiDiem.setForeground(new Color(200, 214, 229));
		this.txtQuanKLiDiem.setFont(new Font("Times New Roman", 0, 17));
		this.txtQuanKLiDiem.setBounds(55, 153, 127, 32);
		panel_4_1.add(this.txtQuanKLiDiem);
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setHorizontalAlignment(2);
		lblNewLabel_4_2.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\home.png"));
		lblNewLabel_4_2.setBounds(10, 69, 32, 32);
		panel_4_1.add(lblNewLabel_4_2);
		this.txtTrangChu = new JLabel("Trang Chủ");
		this.txtTrangChu.setHorizontalAlignment(2);
		this.txtTrangChu.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.this.txtTrangChu);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.this.txtTrangChu);
			}

			public void mouseClicked(MouseEvent e) {
				frame1.this.trangChu();
			}
		});
		this.txtTrangChu.setForeground(new Color(200, 214, 229));
		this.txtTrangChu.setFont(new Font("Times New Roman", 0, 17));
		this.txtTrangChu.setBounds(55, 78, 127, 23);
		panel_4_1.add(this.txtTrangChu);
		JLabel lblNewLabel_4_1_1 = new JLabel("");
		lblNewLabel_4_1_1.setHorizontalAlignment(2);
		lblNewLabel_4_1_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\teamwork.png"));
		lblNewLabel_4_1_1.setBounds(10, 195, 32, 32);
		panel_4_1.add(lblNewLabel_4_1_1);
		this.txtVeChungToi = new JLabel("Về Chúng Tôi ");
		this.txtVeChungToi.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.this.txtVeChungToi);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.this.txtVeChungToi);
			}

			public void mouseClicked(MouseEvent e) {
				frame1.this.aboutUs();
			}
		});
		this.txtVeChungToi.setHorizontalAlignment(2);
		this.txtVeChungToi.setForeground(new Color(200, 214, 229));
		this.txtVeChungToi.setFont(new Font("Times New Roman", 0, 17));
		this.txtVeChungToi.setBounds(55, 204, 127, 23);
		panel_4_1.add(this.txtVeChungToi);
		JLabel lblNewLabel_4_1_1_1 = new JLabel("");
		lblNewLabel_4_1_1_1.setHorizontalAlignment(2);
		lblNewLabel_4_1_1_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\enter.png"));
		lblNewLabel_4_1_1_1.setBounds(10, 279, 32, 32);
		panel_4_1.add(lblNewLabel_4_1_1_1);
		txtDangDang_DangXuat = new JLabel("Đăng Nhập ");
		txtDangDang_DangXuat.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.txtDangDang_DangXuat);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.txtDangDang_DangXuat);
			}

			public void mouseClicked(MouseEvent e) {
				if (frame1.txtDangDang_DangXuat.getText().equalsIgnoreCase("Đăng Xuất")) {
					frame1.this.hamDangXuat();
				} else {
					frame1.this.hamDangNhap();
				}

			}
		});
		txtDangDang_DangXuat.setHorizontalAlignment(2);
		txtDangDang_DangXuat.setForeground(new Color(200, 214, 229));
		txtDangDang_DangXuat.setFont(new Font("Times New Roman", 0, 17));
		txtDangDang_DangXuat.setBounds(55, 288, 127, 23);
		panel_4_1.add(txtDangDang_DangXuat);
		JLabel lblNewLabel_4_1_1_2 = new JLabel("");
		lblNewLabel_4_1_1_2.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\icons8-search-property-32.png"));
		lblNewLabel_4_1_1_2.setHorizontalAlignment(2);
		lblNewLabel_4_1_1_2.setBounds(10, 237, 32, 32);
		panel_4_1.add(lblNewLabel_4_1_1_2);
		this.txtTraCuuDiem = new JLabel("Tra cứu Điểm ");
		this.txtTraCuuDiem.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame1.this.mouseEnteredVao(frame1.this.txtTraCuuDiem);
			}

			public void mouseExited(MouseEvent e) {
				frame1.this.mouseExitedRa(frame1.this.txtTraCuuDiem);
			}

			public void mouseClicked(MouseEvent e) {
				frame1.this.traCuuDiemSinhVien();
			}
		});
		this.txtTraCuuDiem.setForeground(new Color(200, 214, 229));
		this.txtTraCuuDiem.setFont(new Font("Times New Roman", 0, 17));
		this.txtTraCuuDiem.setBounds(55, 246, 127, 23);
		panel_4_1.add(this.txtTraCuuDiem);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(-11106608));
		panel_2.setBounds(0, 0, 1072, 41);
		panel.add(panel_2);
		panel_2.setLayout((LayoutManager) null);
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setVgap(10);
		panel_7.setBounds(558, 0, 182, 41);
		panel_7.setBackground(new Color(-11106608));
		panel_2.add(panel_7);
		this.txtNgayVaGio = new JLabel("22:52 19/07/2004");
		this.txtNgayVaGio.setForeground(new Color(255, 255, 255));
		this.txtNgayVaGio.setFont(new Font("Tahoma", 0, 16));
		panel_7.add(this.txtNgayVaGio);
		final JLabel txtTenRole_1 = new JLabel("");
		txtTenRole_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\Blackvariant-Button-Ui-System-Folders-Alt-Users.32.png"));
		txtTenRole_1.setForeground(new Color(206, 214, 224));
		txtTenRole_1.setFont(new Font("Times New Roman", 1, 18));
		txtTenRole_1.setBounds(1022, 0, 40, 41);
		panel_2.add(txtTenRole_1);
		this.txtTenRole = new JLabel("quyền");
		this.txtTenRole.setHorizontalAlignment(4);
		this.txtTenRole.setFont(new Font("Times New Roman", 1, 15));
		this.txtTenRole.setBounds(883, 0, 129, 41);
		this.txtTenRole.setForeground(new Color(255, 255, 255));
		panel_2.add(this.txtTenRole);
		mainFrame = new JPanel();
		mainFrame.setBackground(new Color(255, 255, 255));
		mainFrame.setForeground(new Color(255, 255, 255));
		mainFrame.setBounds(236, 40, 846, 624);
		panel.add(mainFrame);
		mainFrame.setLayout(new BorderLayout(0, 0));
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBackground(Color.white);
		RoundedUtils.setRounded(popupMenu, 10, this.color);
		JMenuItem menuItemCaiDatADmin = new JMenuItem("Hồ Sơ Tài Khoản");
		menuItemCaiDatADmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.this.hosoTaiKhoan();
			}
		});
		JMenuItem menuItemThoatChuongTRinh = new JMenuItem("Thoát Chương Trình");
		menuItemThoatChuongTRinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog((Component) null,
						"Bạn có chắc chắn muốn thoát chương trình?", "Xác nhận thoát", 0);
				if (choice == 0) {
					JDialog loadingDialog = new JDialog();
					loadingDialog.setTitle("Loading...");
					loadingDialog.setDefaultCloseOperation(0);
					final JProgressBar progressBar = new JProgressBar(0, 100);
					progressBar.setStringPainted(true);
					Thread timerThread = new Thread(new Runnable() {
						int count = 0;

						public void run() {
							while (this.count < 100) {
								++this.count;
								progressBar.setValue(this.count);

								try {
									Thread.sleep(10L);
								} catch (InterruptedException var2) {
									var2.printStackTrace();
								}
							}

							System.exit(0);
						}
					});
					timerThread.start();
					loadingDialog.getContentPane().add(progressBar);
					loadingDialog.pack();
					loadingDialog.setLocationRelativeTo((Component) null);
					loadingDialog.setVisible(true);
				}

			}
		});
		JMenuItem menuItemThongBao = new JMenuItem("Nhật Ký Hoạt Động ");
		menuItemThongBao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.this.notification();
			}
		});
		RoundedUtils.setRounded(menuItemThongBao, 10, this.color);
		RoundedUtils.setRounded(menuItemCaiDatADmin, 10, this.color);
		RoundedUtils.setRounded(menuItemThoatChuongTRinh, 10, this.color);
		popupMenu.add(menuItemThoatChuongTRinh);
		txtTenRole_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && e.getSource() == txtTenRole_1) {
					popupMenu.show(txtTenRole_1, e.getX() - 130, e.getY() + 20);
				}

			}
		});
		(new Thread(() -> {
			while (true) {
				this.setTenTk();
				if (!this.tenTk.equals("khách")) {
					popupMenu.add(menuItemCaiDatADmin);
					popupMenu.add(menuItemThongBao);
				} else {
					popupMenu.remove(menuItemCaiDatADmin);
					popupMenu.remove(menuItemThongBao);
				}

				try {
					Thread.sleep(10L);
				} catch (InterruptedException var5) {
					var5.printStackTrace();
				}
			}
		})).start();
	}
}