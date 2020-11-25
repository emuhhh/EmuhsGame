import java.awt.*;

public class PlayerFigure {
	public double x = GameFrame.window.getWidth() / 2f;
	public double y = GameFrame.window.getHeight() / 2f;
	public Integer clickedX;
	public Integer clickedY;
	private int width = 30;
	private int height = 30;
	public Boolean alive = null;

	public static double speed = 3.1;

	public void draw(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
		if (clickedX != null && clickedY != null)
			g.drawOval(clickedX, clickedY, 1, 1);
	}


	public void update() {
		for (int i = 0; i < GameFrame.bullets.size(); i++) {
			double diffX = Math.abs(x - GameFrame.bullets.get(i).x);
			double diffY = Math.abs(y - GameFrame.bullets.get(i).y);
			double distance = Math.sqrt((diffX * diffX) + (diffY * diffY));

			if (distance < width / 2d + GameFrame.bullets.get(i).width / 2d) {
				alive = false;
				GameFrame.bullets.clear();
				clickedX = null;
				clickedY = null;
			}
		}

		if (clickedX != null && clickedY != null) {
			double offX = clickedX - x;
			double offY = clickedY - y;

			if (Math.abs(offX) > 1 || Math.abs(offY) > 1) {
				float distance = (float) Math.sqrt(offX * offX + offY * offY);
				double velX = offX / distance;
				double velY = offY / distance;
				velX *= speed;
				velY *= speed;
				x += velX;
				y += velY;
			}
		}
	}

	public void saveClickedPoint(int x, int y) {
		clickedX = x;
		clickedY = y;
	}
}





