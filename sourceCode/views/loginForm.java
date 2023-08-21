package views;

import models.UserDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

public class loginForm extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JRadioButton radioRememberMe;
	public String tk = "khách";
	public String role = "bạn";

	public void rememberMe() {
		try {
			FileInputStream fileIn = new FileInputStream("remember.dat");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			String[] Userpass = (String[]) objectIn.readObject();
			objectIn.close();
			fileIn.close();
			String user = Userpass[0];
			String pass = Userpass[1];
			this.txtUser.setText(user);
			this.txtPass.setText(pass);
		} catch (Exception var6) {
			System.out.println("Bỏ qua");
		}

	}

	public void hamchecklogin() {
		if (!this.txtUser.getText().equals("") && !this.txtPass.getText().equals("")) {
			String user = this.txtUser.getText();
			String pass = new String(this.txtPass.getPassword());
			UserDao userDAO = new UserDao();
			if (userDAO.checkLogin(user, pass)) {
				this.tk = "bạn";
				if (this.radioRememberMe.isSelected()) {
					String[] Userpass = new String[]{this.txtUser.getText(), new String(this.txtPass.getPassword())};

					try {
						FileOutputStream fileOut = new FileOutputStream("remember.dat");
						ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
						objectOut.writeObject(Userpass);
						objectOut.close();
						fileOut.close();
					} catch (IOException var7) {
						var7.printStackTrace();
					}
				} else {
					File file = new File("remember.dat");
					if (file.exists()) {
						file.delete();
					}
				}

				this.tk = userDAO.getRoleQuyen(user);
				this.saveTk();
				this.role = user;
				this.tenUser();
				this.dispose();
				JOptionPane.showMessageDialog((Component) null, "Đăng Nhập Thành Công !");
			} else {
				JOptionPane.showMessageDialog((Component) null, "user & pass không đúng , kiểm Tra lại");
			}
		} else {
			JOptionPane.showMessageDialog((Component) null, "Vui Lòng Điền Đầy Đủ user & pass");
		}
	}

	public String saveTk() {
		return this.tk;
	}

	public String tenUser() {
		return this.role;
	}

	public void exit() {
		System.exit(0);
		this.dispose();
	}

	public loginForm() {
		this.setResizable(false);
		this.setBounds(100, 100, 439, 267);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBackground(new Color(255, 255, 255));
		this.contentPanel.setBorder(new EtchedBorder(1, (Color) null, (Color) null));
		this.setLocationRelativeTo((Component) null);
		this.getContentPane().add(this.contentPanel, "Center");
		SpringLayout sl_contentPanel = new SpringLayout();
		this.contentPanel.setLayout(sl_contentPanel);
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setFont(new Font("Tahoma", 0, 27));
		lblNewLabel.setHorizontalAlignment(0);
		sl_contentPanel.putConstraint("North", lblNewLabel, 10, "North", this.contentPanel);
		sl_contentPanel.putConstraint("West", lblNewLabel, 10, "West", this.contentPanel);
		sl_contentPanel.putConstraint("South", lblNewLabel, 47, "North", this.contentPanel);
		sl_contentPanel.putConstraint("East", lblNewLabel, -10, "East", this.contentPanel);
		this.contentPanel.add(lblNewLabel);
		this.lblNewLabel_2 = new JLabel("Username");
		sl_contentPanel.putConstraint("North", this.lblNewLabel_2, 16, "South", lblNewLabel);
		this.lblNewLabel_2
				.setIcon(new ImageIcon("D:\\JAVA\\ASSQLNHANVIEN\\src\\hinhanh\\Places-user-identity-icon.png"));
		sl_contentPanel.putConstraint("West", this.lblNewLabel_2, 30, "West", this.contentPanel);
		sl_contentPanel.putConstraint("East", this.lblNewLabel_2, 161, "West", this.contentPanel);
		this.lblNewLabel_2.setHorizontalAlignment(0);
		this.contentPanel.add(this.lblNewLabel_2);
		this.lblNewLabel_2_1 = new JLabel("Password");
		sl_contentPanel.putConstraint("South", this.lblNewLabel_2, -6, "North", this.lblNewLabel_2_1);
		sl_contentPanel.putConstraint("East", this.lblNewLabel_2_1, 161, "West", this.contentPanel);
		sl_contentPanel.putConstraint("South", this.lblNewLabel_2_1, 170, "North", this.contentPanel);
		sl_contentPanel.putConstraint("North", this.lblNewLabel_2_1, 122, "North", this.contentPanel);
		sl_contentPanel.putConstraint("West", this.lblNewLabel_2_1, 30, "West", this.contentPanel);
		this.lblNewLabel_2_1.setIcon(new ImageIcon(
				"D:\\JAVA\\ASSQLNHANVIEN\\src\\hinhanh\\Apps-preferences-desktop-user-password-icon.png"));
		this.lblNewLabel_2_1.setHorizontalAlignment(0);
		this.contentPanel.add(this.lblNewLabel_2_1);
		this.txtUser = new JTextField();
		sl_contentPanel.putConstraint("North", this.txtUser, 17, "North", this.lblNewLabel_2);
		sl_contentPanel.putConstraint("West", this.txtUser, 6, "East", this.lblNewLabel_2);
		sl_contentPanel.putConstraint("East", this.txtUser, 174, "East", this.lblNewLabel_2);
		this.txtUser.setColumns(10);
		this.contentPanel.add(this.txtUser);
		this.txtPass = new JPasswordField();
		sl_contentPanel.putConstraint("North", this.txtPass, 33, "South", this.txtUser);
		sl_contentPanel.putConstraint("West", this.txtPass, 6, "East", this.lblNewLabel_2_1);
		sl_contentPanel.putConstraint("East", this.txtPass, 174, "East", this.lblNewLabel_2_1);
		this.contentPanel.add(this.txtPass);
		this.radioRememberMe = new JRadioButton("Remember me ?");
		this.radioRememberMe.setBackground(new Color(255, 255, 255));
		this.radioRememberMe.setSelected(true);
		sl_contentPanel.putConstraint("South", this.radioRememberMe, -10, "South", this.contentPanel);
		sl_contentPanel.putConstraint("East", this.radioRememberMe, 0, "East", this.txtUser);
		this.contentPanel.add(this.radioRememberMe);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 255, 255));
		buttonPane.setLayout(new FlowLayout(2));
		this.getContentPane().add(buttonPane, "South");
		JButton cancelButton = new JButton("Đăng Nhập");
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setBackground(new Color(0, 0, 255));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginForm.this.hamchecklogin();
			}
		});
		cancelButton.setActionCommand("OK");
		buttonPane.add(cancelButton);
		this.getRootPane().setDefaultButton(cancelButton);
		cancelButton = new JButton("Thoát");
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setBackground(new Color(255, 0, 0));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginForm.this.exit();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

}
