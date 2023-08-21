package views;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import models.Notification;
import models.UserDao;

import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class formNotification extends JPanel {
	private JTextPane textPane;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	private void notification() throws ClassNotFoundException, SQLException {
		System.out.println("Quyền Tài Khoản: " + UserDao.getRoleQuyen(frame1.returnTenTk()));
		ResultSet rs = Notification.getNotification(UserDao.getRoleQuyen(frame1.returnTenTk()));
		while (rs.next()) {
			String dulieucu = textPane.getText();
			if (dulieucu.equals("")) {
				String dateTimeString = rs.getTimestamp(4).toString(); // Lấy giá trị ngày giờ từ cột 3 của ResultSet
				textPane.setText( rs.getInt(1) + " | " + rs.getString(2) + " | "+rs.getString(3)+" | " + dateTimeString);
			} else {
				String dateTimeString = rs.getTimestamp(4).toString(); // Lấy giá trị ngày giờ từ cột 3 của ResultSet
				textPane.setText(dulieucu + "\n" + rs.getInt(1) + " | " + rs.getString(2) + " | "+rs.getString(3)+ " | " + dateTimeString);
			}
		}
	}

	public formNotification() {
		setBackground(new Color(255, 255, 255));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 58, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 637, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 828, SpringLayout.WEST, this);
		panel.setBackground(new Color(255, 255, 255));
		add(panel);
		panel.setLayout(new MigLayout("", "[2px][grow]", "[2px][grow]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		panel.add(scrollPane, "cell 1 1,grow");

		textPane = new JTextPane();
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Nhật Kí Hoạt Động");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		panel_1.add(lblNewLabel);

		try {
			notification();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
