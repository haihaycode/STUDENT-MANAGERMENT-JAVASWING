package views;

import Helper.DatabaseHelper;
import Helper.RoundedUtils;
import models.Notification;
import models.UserDao;
import models.UserProfileUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class hoSoQuanLiFormAdmin extends JPanel {
	private JTextField txtUserNameThayThe;
	private JPasswordField txtpassword;
	private JPasswordField txtConfirmPassWord;
	private JTextField txtRoleUser;
	private JPasswordField txtCurentPassWord;
	private JLabel txtUserName;
	private JLabel txtusercu;
	private JTextField txtTkUser;
	private JPasswordField txtPassUser;
	private JTable table;
	private DefaultTableModel model;
	private Color color = new Color(72, 126, 176);
	private JComboBox comboBox;
	
	private String QuyenChung =UserDao.getRoleQuyen(frame1.returnTenTk());

	private void hoSoCaNhan() {
		String[] mang = UserDao.traTenRole(frame1.returnTenTk());
		this.txtUserName.setText(mang[0]);
		this.txtUserNameThayThe.setText(mang[0]);
		this.txtpassword.setText(mang[1]);
		this.txtConfirmPassWord.setText(mang[1]);
		this.txtRoleUser.setText(mang[2]);
	}

	private void CapNhatUser() throws ClassNotFoundException, SQLException {
		if (!this.txtCurentPassWord.getText().equals("") && !this.txtConfirmPassWord.getText().equals("")
				&& !this.txtUserNameThayThe.getText().equals("") && !this.txtpassword.getText().equals("")) {
			if (!this.txtConfirmPassWord.getText().equals(this.txtpassword.getText())) {
				JOptionPane.showMessageDialog((Component) null, "vui long xac nhan lai mat khau !");
			} else if (!UserDao.checkCapNhat(this.txtUserName.getText(), this.txtCurentPassWord.getText())) {
				JOptionPane.showMessageDialog((Component) null, "Sai Mat Khau cu !");
			} else if (UserProfileUpdate.checkUser(this.txtUserNameThayThe.getText())
					&& !this.txtUserNameThayThe.getText().equals(this.txtUserName.getText())) {
				JOptionPane.showMessageDialog((Component) null, "Tai Khoan Nay da Ton tai !");
			} else {
				int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thay đổi Mật Khẩu không ?",
						"Xác Nhận Cập Nhât ", 0);
				if (hoi != 0) {
					System.out.println("Dừng Cập Nhật");
					return;
				}

				String trangThai = UserProfileUpdate.getUserProfileUpdate(this.txtUserNameThayThe.getText(),
						this.txtConfirmPassWord.getText(), this.txtUserName.getText());
				JOptionPane.showMessageDialog(this, trangThai);
				frame1.DangXuatDungChung();
			}
		} else {
			JOptionPane.showMessageDialog((Component) null, "Khong duoc bo trong truong nao !");
		}

	}

	private void CapNhatUserS() throws ClassNotFoundException, SQLException {
		if (!this.txtTkUser.getText().equals("") && !this.txtPassUser.getText().equals("")) {
			if (UserProfileUpdate.checkUser(this.txtTkUser.getText())
					&& !this.txtTkUser.getText().equals(this.txtusercu.getText())) {
				JOptionPane.showMessageDialog((Component) null, "Tai Khoan Nay da Ton tai !");
			} else {
				int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thay đổi Mật Khẩu không ?",
						"Xác Nhận Cập Nhât ", 0);
				if (hoi != 0) {
					System.out.println("Dừng Cập Nhật");
					return;
				}

				String trangThai = UserProfileUpdate.getUserProfileUpdate(this.txtTkUser.getText(),
						this.txtPassUser.getText(), this.txtusercu.getText());
				JOptionPane.showMessageDialog(this, trangThai);
				this.fillDataTotable();
			}
		} else {
			JOptionPane.showMessageDialog((Component) null, "Khong duoc bo trong truong nao !");
		}

	}

	private void innitTable() {
		this.model = (DefaultTableModel) this.table.getModel();
		this.model.setColumnIdentifiers(new String[]{"Username", "Mật Khẩu", "Quyền"});
		this.fillDataTotable();
	}

	private void fillDataTotable() {
		try {
			Connection connection = DatabaseHelper.getConnection();
			Statement stm = connection.createStatement();
			String sql = "select * from users";
			ResultSet rs = stm.executeQuery(sql);
			this.model.setRowCount(0);

			while (rs.next()) {
				if (!rs.getString(3).equals("admin")) {
					this.model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
				}
			}
		} catch (Exception var5) {
			System.out.println(var5.getMessage());
		}

		System.out.println("List User kết nối thành công và nhận được tài khoản vào list : ");
	}

	private void insertUser() throws SQLException, ClassNotFoundException {
		if (!UserProfileUpdate.checkUser(this.txtTkUser.getText())) {
			if (!this.txtTkUser.getText().equals("") && !this.txtPassUser.getText().equals("")) {
				Connection connection = DatabaseHelper.getConnection();
				String sqlInSertUser = "Insert into users values(?,?,?)";
				PreparedStatement stm = connection.prepareStatement(sqlInSertUser);
				stm.setString(1, this.txtTkUser.getText());
				stm.setString(2, this.txtPassUser.getText());
				stm.setString(3, (String) this.comboBox.getSelectedItem());
				int rs = stm.executeUpdate();
				if (rs == 0) {
					JOptionPane.showMessageDialog((Component) null, "Loi !");
				} else {
					JOptionPane.showMessageDialog((Component) null, "Them Thanh Cong");
					Notification.InsertMess(QuyenChung, "Đã Thêm 1  Quyền Mới  "+this.txtTkUser.getText());
					this.fillDataTotable();
				}
			} else {
				JOptionPane.showMessageDialog((Component) null, "Nhập đầy đủ nếu muốn thêm user mới  !");
			}
		} else {
			JOptionPane.showMessageDialog((Component) null, "Tên Username này đã tồn tại !");
		}

	}

	private void deleteUser() throws ClassNotFoundException, SQLException {
		if (this.txtusercu.getText().equals("")) {
			JOptionPane.showMessageDialog((Component) null, "Vui long Chon User trong bang de xoa !");
		} else {
			int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thay đổi Mật Khẩu không ?", "Xác Nhận Cập Nhât ",
					0);
			if (hoi != 0) {
				System.out.println("Dừng Cập Nhật");
				return;
			}

			Connection connection = DatabaseHelper.getConnection();
			String sqlInSertUser = "delete from users where username = ?";
			PreparedStatement stm = connection.prepareStatement(sqlInSertUser);
			stm.setString(1, this.txtusercu.getText());
			int rs = stm.executeUpdate();
			if (rs == 0) {
				JOptionPane.showMessageDialog((Component) null, "Loi !");
			} else {
				JOptionPane.showMessageDialog((Component) null, "Xoa Thanh Cong");
				Notification.InsertMess(QuyenChung, "Đã Xóa 1  Quyền "+this.txtTkUser.getText());
				this.fillDataTotable();
			}

			this.fillDataTotable();
		}

	}

	private void hamChon() {
		int viTriRow = this.table.getSelectedRow();
		if (viTriRow >= 0) {
			String user = (String) this.table.getValueAt(viTriRow, 0);
			String pass = (String) this.table.getValueAt(viTriRow, 1);
			String role = (String) this.table.getValueAt(viTriRow, 2);
			this.txtTkUser.setText(user);
			this.txtusercu.setText(user);
			this.txtPassUser.setText(pass);
			this.comboBox.setSelectedItem(role);
		}

	}

	private void xoaTrangThongTin() {
		this.txtusercu.setText("");
		this.txtPassUser.setText("");
		this.txtConfirmPassWord.setText("");
		this.txtCurentPassWord.setText("");
		this.txtTkUser.setText("");
		this.txtUserNameThayThe.setText("");
		this.txtRoleUser.setText("");
		this.txtpassword.setText("");
	}

	public hoSoQuanLiFormAdmin() {
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
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-1085, 39, 3000, 2000);
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\5305323.jpg"));
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(-12, 189, 849, 432);
		panel_1.add(panel_3);
		panel_3.setLayout((LayoutManager) null);
		JPanel panel_4 = new JPanel();
		RoundedUtils.setRounded(panel_4, 10, Color.black);
		panel_4.setBorder(new TitledBorder((Border) null,
				"<html><h3 style=\"color: blue;\">Thông Tin Tài Khoản</h3></html>", 4, 2, (Font) null, (Color) null));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(21, 0, 818, 183);
		panel_3.add(panel_4);
		panel_4.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1_1 = new JLabel("Username ");
		lblNewLabel_1_1.setBounds(251, 64, 203, 29);
		panel_4.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", 1, 17));
		this.txtUserNameThayThe = new JTextField();
		RoundedUtils.setRounded(this.txtUserNameThayThe, 0, this.color);
		this.txtUserNameThayThe.setFont(new Font("Times New Roman", 0, 13));
		this.txtUserNameThayThe.setForeground(new Color(0, 0, 255));
		this.txtUserNameThayThe.setBounds(251, 89, 174, 19);
		panel_4.add(this.txtUserNameThayThe);
		this.txtUserNameThayThe.setColumns(10);
		JLabel lblNewLabel_1_1_1 = new JLabel("Password ");
		lblNewLabel_1_1_1.setBounds(437, 64, 203, 29);
		panel_4.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", 1, 17));
		JLabel lblNewLabel_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_2.setBounds(605, 64, 203, 29);
		panel_4.add(lblNewLabel_1_1_2);
		this.txtpassword = new JPasswordField();
		RoundedUtils.setRounded(this.txtpassword, 0, this.color);
		this.txtpassword.setFont(new Font("Times New Roman", 0, 13));
		this.txtpassword.setForeground(new Color(0, 0, 255));
		this.txtpassword.setBounds(435, 89, 158, 19);
		panel_4.add(this.txtpassword);
		this.txtConfirmPassWord = new JPasswordField();
		RoundedUtils.setRounded(this.txtConfirmPassWord, 0, this.color);
		this.txtConfirmPassWord.setFont(new Font("Times New Roman", 0, 13));
		this.txtConfirmPassWord.setForeground(new Color(0, 0, 255));
		this.txtConfirmPassWord.setBounds(603, 89, 158, 19);
		panel_4.add(this.txtConfirmPassWord);
		JLabel lblNewLabel_1_1_3 = new JLabel("Role ");
		lblNewLabel_1_1_3.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_3.setBounds(251, 113, 203, 19);
		panel_4.add(lblNewLabel_1_1_3);
		this.txtRoleUser = new JTextField();
		RoundedUtils.setRounded(this.txtRoleUser, 0, this.color);
		this.txtRoleUser.setEditable(false);
		RoundedUtils.setRounded(this.txtRoleUser, 1, Color.black);
		this.txtRoleUser.setColumns(10);
		this.txtRoleUser.setBounds(251, 138, 174, 19);
		panel_4.add(this.txtRoleUser);
		this.txtCurentPassWord = new JPasswordField();
		RoundedUtils.setRounded(this.txtCurentPassWord, 0, this.color);
		this.txtCurentPassWord.setFont(new Font("Times New Roman", 0, 13));
		this.txtCurentPassWord.setForeground(new Color(0, 0, 255));
		this.txtCurentPassWord.setBounds(435, 137, 158, 19);
		panel_4.add(this.txtCurentPassWord);
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Current Password (*)");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_1_1.setBounds(437, 113, 176, 19);
		panel_4.add(lblNewLabel_1_1_1_1);
		this.txtUserName = new JLabel("UserName : ");
		this.txtUserName.setForeground(new Color(255, 0, 0));
		this.txtUserName.setFont(new Font("Times New Roman", 1, 17));
		this.txtUserName.setBounds(368, 25, 203, 29);
		panel_4.add(this.txtUserName);
		JButton btnNewButton = new JButton("Cập Nhật ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hoSoQuanLiFormAdmin.this.CapNhatUser();
				} catch (SQLException | ClassNotFoundException var3) {
					JOptionPane.showMessageDialog((Component) null, "Lỗi Tk này đã tồn tại");
					System.out.println(var3.getLocalizedMessage());
				}

			}
		});
		btnNewButton.setBounds(605, 128, 156, 29);
		panel_4.add(btnNewButton);
		RoundedUtils.setRounded(btnNewButton, 10, this.color);
		JLabel lblUsername = new JLabel("UserName : ");
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Times New Roman", 1, 17));
		lblUsername.setBounds(251, 25, 92, 29);
		panel_4.add(lblUsername);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\icons8-admin-100.png"));
		lblNewLabel_1.setBounds(25, 50, 124, 107);
		panel_4.add(lblNewLabel_1);
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(1, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Quản Lí Tài Khoản Users", 4, 2, (Font) null, new Color(0, 0, 255)));
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(21, 182, 818, 240);
		panel_3.add(panel_5);
		panel_5.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1_1_4 = new JLabel("Username ");
		lblNewLabel_1_1_4.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_4.setBounds(177, 38, 142, 29);
		panel_5.add(lblNewLabel_1_1_4);
		this.txtTkUser = new JTextField();
		RoundedUtils.setRounded(this.txtTkUser, 0, this.color);
		this.txtTkUser.setText((String) null);
		this.txtTkUser.setForeground(Color.BLUE);
		this.txtTkUser.setFont(new Font("Times New Roman", 0, 13));
		this.txtTkUser.setColumns(10);
		this.txtTkUser.setBounds(177, 63, 142, 19);
		panel_5.add(this.txtTkUser);
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Password ");
		lblNewLabel_1_1_1_2.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_1_2.setBounds(24, 75, 143, 29);
		panel_5.add(lblNewLabel_1_1_1_2);
		this.txtPassUser = new JPasswordField();
		RoundedUtils.setRounded(this.txtPassUser, 0, this.color);
		this.txtPassUser.setText((String) null);
		this.txtPassUser.setForeground(Color.BLUE);
		this.txtPassUser.setFont(new Font("Times New Roman", 0, 13));
		this.txtPassUser.setBounds(24, 103, 143, 19);
		panel_5.add(this.txtPassUser);
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(342, 10, 466, 230);
		panel_5.add(panel_6);
		panel_6.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane, "cell 0 0,grow");
		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				hoSoQuanLiFormAdmin.this.hamChon();
			}
		});
		this.table.setFillsViewportHeight(true);
		this.table.getTableHeader().setBackground(new Color(-11106608));
		this.table.getTableHeader().setForeground(Color.white);
		this.table.getTableHeader().setOpaque(false);
		this.table.setRowHeight(15);
		this.table.getTableHeader().setFont(new Font("Tahoma", 0, 11));
		this.table.setSelectionBackground(new Color(189, 195, 199));
		scrollPane.setViewportView(this.table);
		JButton btnNewButton_1 = new JButton("Thêm Tài Khoản");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hoSoQuanLiFormAdmin.this.insertUser();
				} catch (SQLException | ClassNotFoundException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				}

			}
		});
		RoundedUtils.setRounded(btnNewButton_1, 10, this.color);
		btnNewButton_1.setBounds(23, 142, 143, 29);
		panel_5.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("Xóa Tài Khoản");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hoSoQuanLiFormAdmin.this.deleteUser();
				} catch (SQLException | ClassNotFoundException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				}

			}
		});
		RoundedUtils.setRounded(btnNewButton_2, 10, this.color);
		btnNewButton_2.setBounds(176, 142, 143, 29);
		panel_5.add(btnNewButton_2);
		JButton btnNewButton_2_1 = new JButton("Cập Nhật Tài Khoản");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hoSoQuanLiFormAdmin.this.CapNhatUserS();
				} catch (SQLException | ClassNotFoundException var3) {
					JOptionPane.showMessageDialog((Component) null, var3.getMessage());
				}

			}
		});
		RoundedUtils.setRounded(btnNewButton_2_1, 10, this.color);
		btnNewButton_2_1.setBounds(23, 184, 143, 29);
		panel_5.add(btnNewButton_2_1);
		JButton btnNewButton_2_2 = new JButton("Xóa Trắng Form");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hoSoQuanLiFormAdmin.this.xoaTrangThongTin();
			}
		});
		RoundedUtils.setRounded(btnNewButton_2_2, 10, this.color);
		btnNewButton_2_2.setBounds(176, 184, 143, 29);
		panel_5.add(btnNewButton_2_2);
		JLabel lblNewLabel_1_1_4_1 = new JLabel("Role ");
		lblNewLabel_1_1_4_1.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_4_1.setBounds(178, 76, 141, 29);
		panel_5.add(lblNewLabel_1_1_4_1);
		this.comboBox = new JComboBox();
		this.comboBox.setModel(new DefaultComboBoxModel(new String[]{"teacher", "cadres"}));
		this.comboBox.setBounds(178, 103, 141, 21);
		panel_5.add(this.comboBox);
		JLabel lblNewLabel_1_1_4_2 = new JLabel("Name (*)");
		lblNewLabel_1_1_4_2.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_4_2.setBounds(24, 38, 189, 29);
		panel_5.add(lblNewLabel_1_1_4_2);
		this.txtusercu = new JLabel("");
		this.txtusercu.setFont(new Font("Times New Roman", 2, 15));
		this.txtusercu.setBounds(24, 58, 189, 19);
		panel_5.add(this.txtusercu);
		this.hoSoCaNhan();
		this.innitTable();
	}
}