package Helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D.Double;
import javax.swing.JButton;

public class RoundedButton extends JButton {
	private int cornerRadius;
	private int color;

	public RoundedButton(String text, int banKinh, int color) {
		super(text);
		this.cornerRadius = banKinh;
		this.setOpaque(false);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(this.getBackground());
		g2.fill(new Double(0.0D, 0.0D, (double) (this.getWidth() - 1), (double) (this.getHeight() - 1),
				(double) this.cornerRadius, (double) this.cornerRadius));
		super.paintComponent(g2);
		g2.dispose();
	}

	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(this.color));
		g2.draw(new Double(0.0D, 0.0D, (double) (this.getWidth() - 1), (double) (this.getHeight() - 1),
				(double) this.cornerRadius, (double) this.cornerRadius));
		g2.dispose();
	}
}