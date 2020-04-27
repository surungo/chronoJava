package AWT;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import chrono.ChronoView;

public class MoveMouseListener implements MouseListener, MouseMotionListener {
	ChronoView chronoview;
	JFrame frame;
	Point start_drag;
	Point start_loc;

	public MoveMouseListener(JFrame frame_in) {
		this.frame = frame_in;
	}
	
	public MoveMouseListener(ChronoView chronoview_in) {
		this.chronoview = chronoview_in;
		this.frame = chronoview.frame;
	}

	public static JFrame getFrame(Container target) {
		if (target instanceof JFrame) {
			return (JFrame) target;
		}
		return getFrame(target.getParent());
	}

	Point getScreenLocation(MouseEvent e) {
		Point cursor = e.getPoint();
		Point target_location = this.frame.getLocationOnScreen();
		return new Point((int) (target_location.getX() + cursor.getX()),
				(int) (target_location.getY() + cursor.getY()));
	}

	public void mouseClicked(MouseEvent e) {
		//System.out.println();
		if (e.getClickCount() == 3) {
		  // System.out.println("Click at (" + e.getX() + ":" + e.getY() + ") and it's a triple click!");
		} else if (e.getClickCount() == 2) {
			//this.chronometer.reset();
			//this.chronometer.vai();
			//this.chronometer.reduzir();
			//close
			System.exit(0);
			//System.out.println("Click at (" + e.getX() + ":" + e.getY() + ") and it's a double click!");
		}else {
			this.chronoview.pause();
			//System.out.println("Click at (" + e.getX() + ":" + e.getY() + ") and it's a simple click!");
		}
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		this.start_drag = this.getScreenLocation(e);
		this.start_loc = this.frame.getLocation();
//		this.start_loc = this.getFrame(this.target).getLocation();
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		Point current = this.getScreenLocation(e);
		Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
				(int) current.getY() - (int) start_drag.getY());
		
		Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()),
				(int) (this.start_loc.getY() + offset.getY()));
		this.frame.setLocation(new_location);
	}

	public void mouseMoved(MouseEvent e) {
	}
}