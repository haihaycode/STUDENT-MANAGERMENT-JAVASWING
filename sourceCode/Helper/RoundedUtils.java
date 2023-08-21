package Helper;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D.Double;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

public class RoundedUtils {
	public static void setRounded(JComponent component, int cornerRadius, Color color) {
		component.setOpaque(false);
		component.setBorder(BorderFactory.createEmptyBorder());
		component.setBackground(color);
		component.addPropertyChangeListener("border", (evt) -> {
			if (evt.getNewValue() instanceof RoundedUtils.RoundRectangleBorder) {
				((RoundedUtils.RoundRectangleBorder) evt.getNewValue()).setCornerRadius(cornerRadius);
				((RoundedUtils.RoundRectangleBorder) evt.getNewValue()).setColor(color);
			}

		});
		component.setBorder(new RoundedUtils.RoundRectangleBorder(cornerRadius, color));
	}

	private static class RoundRectangleBorder extends AbstractBorder {
		private int cornerRadius;
		private Color color;

		public RoundRectangleBorder(int cornerRadius, Color color) {
			this.cornerRadius = cornerRadius;
			this.color = color;
		}

		public void setCornerRadius(int cornerRadius) {
			this.cornerRadius = cornerRadius;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(this.color);
			g2d.draw(new Double((double) x, (double) y, (double) (width - 1), (double) (height - 1),
					(double) this.cornerRadius, (double) this.cornerRadius));
			g2d.dispose();
		}

		public Insets getBorderInsets(Component c) {
			int borderWidth = Math.max(2, this.cornerRadius / 8);
			return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
		}

		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.top = insets.right = insets.bottom = Math.max(2, this.cornerRadius / 8);
			return insets;
		}
	}
}