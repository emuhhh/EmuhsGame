import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	public MouseHandler() {
		super();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		if (e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON1)
			GameFrame.player1.saveClickedPoint(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
