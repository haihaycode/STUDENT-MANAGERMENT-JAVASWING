package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

public class KhongDuQuyenHan extends JPanel {
	public KhongDuQuyenHan() {
		this.setBorder(new EtchedBorder(1, (Color) null, (Color) null));
		this.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.add(panel, "Center");
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		sl_panel.putConstraint("North", panel_1, 10, "North", panel);
		sl_panel.putConstraint("West", panel_1, 0, "West", panel);
		sl_panel.putConstraint("South", panel_1, 268, "North", panel);
		sl_panel.putConstraint("East", panel_1, -6, "East", panel);
		panel.add(panel_1);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\attention.png"));
		panel_1.add(lblNewLabel);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		sl_panel.putConstraint("North", panel_2, 6, "South", panel_1);
		sl_panel.putConstraint("South", panel_2, -295, "South", panel);
		sl_panel.putConstraint("East", panel_2, 0, "East", panel_1);
		sl_panel.putConstraint("West", panel_2, 0, "West", panel);
		panel.add(panel_2);
		JLabel lblNewLabel_1 = new JLabel("Cảnh Báo ! Bạn Không Đủ Quyền Hạn    ");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", 2, 22));
		panel_2.add(lblNewLabel_1);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		sl_panel.putConstraint("North", panel_3, 6, "South", panel_2);
		sl_panel.putConstraint("West", panel_3, 0, "West", panel_1);
		sl_panel.putConstraint("South", panel_3, 43, "South", panel_2);
		sl_panel.putConstraint("East", panel_3, -10, "East", panel);
		panel.add(panel_3);
		JLabel lblNewLabel_1_1 = new JLabel("Quay Lại Trang Chủ ");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\back.png"));
		lblNewLabel_1_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", 2, 22));
		panel_3.add(lblNewLabel_1_1);
	}
}