package views;

import Helper.RoundedUtils;
import models.UserDao;
import models.UserProfileUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class hoSoQuanLiFormuUser extends JPanel {
	private JTextField txtUserNameThayThe;
	private JPasswordField txtpassword;
	private JPasswordField txtConfirmPassWord;
	private JTextField txtRoleUser;
	private JPasswordField txtCurentPassWord;
	private JLabel txtUserName;
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

	public hoSoQuanLiFormuUser() {
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
		panel_4.setBounds(21, 10, 818, 412);
		panel_3.add(panel_4);
		panel_4.setLayout((LayoutManager) null);
		JLabel lblNewLabel_1_1 = new JLabel("Username ");
		lblNewLabel_1_1.setBounds(222, 114, 203, 29);
		panel_4.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", 1, 17));
		this.txtUserNameThayThe = new JTextField();
		this.txtUserNameThayThe.setFont(new Font("Times New Roman", 0, 13));
		this.txtUserNameThayThe.setForeground(new Color(0, 0, 255));
		this.txtUserNameThayThe.setBounds(222, 139, 203, 19);
		panel_4.add(this.txtUserNameThayThe);
		this.txtUserNameThayThe.setColumns(10);
		JLabel lblNewLabel_1_1_1 = new JLabel("Password ");
		lblNewLabel_1_1_1.setBounds(437, 114, 203, 29);
		panel_4.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", 1, 17));
		JLabel lblNewLabel_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_2.setBounds(605, 114, 203, 29);
		panel_4.add(lblNewLabel_1_1_2);
		this.txtpassword = new JPasswordField();
		this.txtpassword.setFont(new Font("Times New Roman", 0, 13));
		this.txtpassword.setForeground(new Color(0, 0, 255));
		this.txtpassword.setBounds(435, 139, 158, 19);
		panel_4.add(this.txtpassword);
		this.txtConfirmPassWord = new JPasswordField();
		this.txtConfirmPassWord.setFont(new Font("Times New Roman", 0, 13));
		this.txtConfirmPassWord.setForeground(new Color(0, 0, 255));
		this.txtConfirmPassWord.setBounds(603, 139, 158, 19);
		panel_4.add(this.txtConfirmPassWord);
		JLabel lblNewLabel_1_1_3 = new JLabel("Role ");
		lblNewLabel_1_1_3.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_3.setBounds(222, 168, 203, 29);
		panel_4.add(lblNewLabel_1_1_3);
		this.txtRoleUser = new JTextField();
		this.txtRoleUser.setEditable(false);
		RoundedUtils.setRounded(this.txtRoleUser, 1, Color.black);
		this.txtRoleUser.setColumns(10);
		this.txtRoleUser.setBounds(222, 193, 203, 19);
		panel_4.add(this.txtRoleUser);
		this.txtCurentPassWord = new JPasswordField();
		this.txtCurentPassWord.setFont(new Font("Times New Roman", 0, 13));
		this.txtCurentPassWord.setForeground(new Color(0, 0, 255));
		this.txtCurentPassWord.setBounds(435, 192, 158, 19);
		panel_4.add(this.txtCurentPassWord);
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Current Password (*)");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_1_1.setBounds(437, 168, 176, 29);
		panel_4.add(lblNewLabel_1_1_1_1);
		this.txtUserName = new JLabel("UserName : ");
		this.txtUserName.setForeground(new Color(255, 0, 0));
		this.txtUserName.setFont(new Font("Times New Roman", 1, 17));
		this.txtUserName.setBounds(336, 75, 203, 29);
		panel_4.add(this.txtUserName);
		JLabel lblNewLabel_1_1_4_1 = new JLabel("");
		lblNewLabel_1_1_4_1.setHorizontalAlignment(0);
		lblNewLabel_1_1_4_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\bubble-gum-avatar-icon.png"));
		lblNewLabel_1_1_4_1.setForeground(Color.RED);
		lblNewLabel_1_1_4_1.setFont(new Font("Times New Roman", 1, 17));
		lblNewLabel_1_1_4_1.setBounds(30, 46, 158, 168);
		panel_4.add(lblNewLabel_1_1_4_1);
		JButton btnNewButton = new JButton("Cập Nhật ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hoSoQuanLiFormuUser.this.CapNhatUser();
				} catch (SQLException | ClassNotFoundException var3) {
					JOptionPane.showMessageDialog((Component) null, "Lỗi Tk này đã tồn tại");
					System.out.println(var3.getLocalizedMessage());
				}

			}
		});
		btnNewButton.setBounds(652, 356, 156, 46);
		panel_4.add(btnNewButton);
		RoundedUtils.setRounded(btnNewButton, 10, Color.blue);
		JLabel lblUsername = new JLabel("UserName : ");
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Times New Roman", 1, 17));
		lblUsername.setBounds(222, 75, 92, 29);
		panel_4.add(lblUsername);
		this.hoSoCaNhan();
	}
}