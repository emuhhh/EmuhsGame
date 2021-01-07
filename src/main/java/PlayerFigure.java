import java.awt.*;

/**
 * Class of my user's player
 */
public class PlayerFigure {
	/**
	 * Variable X defines start-xPos of user's figure
	 */
	public double x = GameFrame.window.getWidth() / 2f;
	/**
	 * Variable Y defines start-yPos of user's figure
	 */
	public double y = GameFrame.window.getHeight() / 2f;
	/**
	 * saves clickedX as Integer
	 */
	public Integer clickedX;
	/**
	 * saves clickedY as Integer
	 */
	public Integer clickedY;
	/**
	 * sets user's figure's width
	 */
	private int width = 30;
	/**
	 * sets user's figure's height
	 */
	private int height = 30;
	/**
	 * sets user's figure's speed
	 */
	public static double speed = 1.5;
	/**
	 * run variable
	 */
	public Boolean alive = null;

	/**
	 * draws shape and color of users player
	 * @param g Instance of class Graphics2D
	 */
	public void draw(Graphics2D g) {
		g.setColor(new Color(255, 0, 0));
		g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
		if (clickedX != null && clickedY != null)
			g.drawOval(clickedX, clickedY, 1, 1);
	}

	/**
	 * collision detection + player movement
	 */
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

	/**
	 * saves the clicked point from Key-event of Mouse handler
	 * @param x saved xPos of clickedX
	 * @param y saved yPos of clickedY
	 */
	public void saveClickedPoint(int x, int y) {
		clickedX = x;
		clickedY = y;
	}
}





