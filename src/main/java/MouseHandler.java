import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse handler class
 */
public class MouseHandler implements MouseListener {
	/**
	 * not required in current project version, preset of MouseListener
	 */
	public MouseHandler() {

	}

	/**
	 * not required in current project version, preset of MouseListener
	 * @param e mouse clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * not required in current project version, preset of MouseListener
	 * @param e mouse pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		if (e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON1)
			GameFrame.player1.saveClickedPoint(e.getX(), e.getY());
	}

	/**
	 * not required in current project version, preset of MouseListener
	 * @param e mouse released
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * not required in current project version, preset of MouseListener
	 * @param e mouse entered
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * not required in current project version, preset of MouseListener
	 * @param e mouse exited
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}
}
