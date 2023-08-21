package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class aboutus extends JPanel {
	public aboutus() {
		this.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		this.add(panel, "Center");
		panel.setLayout((LayoutManager) null);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 334, 209);
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		panel_1.setLayout((LayoutManager) null);
		JLabel lblNewLabel = new JLabel("MÃ SINH VIÊN : PD07906 ");
		lblNewLabel.setBounds(10, 29, 296, 16);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", 3, 13));
		JLabel lblHTn = new JLabel("HỌ TÊN : PHẠM HẢI ");
		lblHTn.setBounds(10, 62, 208, 16);
		panel_1.add(lblHTn);
		lblHTn.setFont(new Font("Tahoma", 3, 13));
		JLabel lblLpIt = new JLabel("LỚP : IT18305 ");
		lblLpIt.setBounds(10, 103, 98, 16);
		panel_1.add(lblLpIt);
		lblLpIt.setFont(new Font("Tahoma", 3, 13));
		JLabel lblNgySinh = new JLabel("NGÀY SINH : 19/07/2004");
		lblNgySinh.setBounds(10, 145, 166, 16);
		panel_1.add(lblNgySinh);
		lblNgySinh.setFont(new Font("Tahoma", 3, 13));
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(10, 240, 649, 241);
		panel_1_1.setLayout((LayoutManager) null);
		panel_1_1.setBackground(Color.WHITE);
		panel.add(panel_1_1);
		JLabel lblyLBi = new JLabel("Đây là bài ASSIGNMENT GIAI ĐOẠN 1 MÔN JAVA3\r\n\r\n\r\n\r\n\r\n");
		lblyLBi.setBounds(10, 10, 296, 40);
		panel_1_1.add(lblyLBi);
		lblyLBi.setFont(new Font("Tahoma", 0, 13));
		JLabel lblPhnMmQun = new JLabel("PHẦN MỀM QUẢN LÍ SINH VIÊN   ");
		lblPhnMmQun.setBounds(10, 51, 198, 16);
		panel_1_1.add(lblPhnMmQun);
		lblPhnMmQun.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_2 = new JLabel("GỒM CÓ 3 ĐỐI TƯỢNG : GIÁO VIÊN - CÁN BỘ - ADMIN    ");
		lblNgySinh_2.setBounds(10, 77, 343, 32);
		panel_1_1.add(lblNgySinh_2);
		lblNgySinh_2.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_3 = new JLabel("ĐỐI VỚI ");
		lblNgySinh_3.setBounds(10, 108, 54, 16);
		panel_1_1.add(lblNgySinh_3);
		lblNgySinh_3.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_3_1 = new JLabel(
				"+ ADMIN : CÓ QUYỀN QUẢN LÍ THÔNG TIN SINH VIÊN - QUẢN LÍ ĐIỂM SINH VIÊN (XÓA , SỬA , ");
		lblNgySinh_3_1.setBounds(10, 130, 575, 16);
		panel_1_1.add(lblNgySinh_3_1);
		lblNgySinh_3_1.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_3_4_1 = new JLabel("CẬP NHẬT, TẠO MỚI )    ");
		lblNgySinh_3_4_1.setBounds(10, 152, 150, 16);
		panel_1_1.add(lblNgySinh_3_4_1);
		lblNgySinh_3_4_1.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_3_2 = new JLabel(
				"+GIÁO VIÊN : CÓ QUYỀN QUẢN LÍ ĐIỂM SINH VIÊN (XÓA , SỬA , CẬP NHẬT, TẠO MỚI )    ");
		lblNgySinh_3_2.setBounds(10, 174, 541, 16);
		panel_1_1.add(lblNgySinh_3_2);
		lblNgySinh_3_2.setFont(new Font("Tahoma", 0, 13));
		JLabel lblNgySinh_3_3 = new JLabel(
				"+ CÁN BỘ ĐÀO TẠO  : CÓ QUYỀN QUẢN LÍ THÔNG TIN SINH VIÊN (XÓA , SỬA , CẬP NHẬT, TẠO MỚI )   ");
		lblNgySinh_3_3.setBounds(10, 196, 627, 16);
		panel_1_1.add(lblNgySinh_3_3);
		lblNgySinh_3_3.setFont(new Font("Tahoma", 0, 13));
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 229, 312, 10);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new MatteBorder(1, 0, 0, 0, new Color(0, 0, 0)));
		panel.add(panel_2);
		JLabel lblNgySinh_3_3_1 = new JLabel("thank you !  ");
		lblNgySinh_3_3_1.setBounds(20, 664, 186, 29);
		lblNgySinh_3_3_1.setForeground(new Color(0, 128, 0));
		panel.add(lblNgySinh_3_3_1);
		lblNgySinh_3_3_1.setHorizontalAlignment(4);
		lblNgySinh_3_3_1.setFont(new Font("Tahoma", 2, 24));
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(399, 0, 308, 224);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(
				new ImageIcon("D:\\JAVA\\BAITHII_PD07906\\sourceCode\\imgs\\business-3d-businessman-thinking-about-coffee.png"));
	}
}