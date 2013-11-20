package robotkittenearthguardians.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import robotkittenearthguardians.MainGame;
import robotkittenearthguardians.graphics.Camera;

public class Mouse implements MouseListener, MouseMotionListener{

	private static double mouseX = -1;
	private static double mouseY = -1;
	private static int mouseB = -1;
	private static double mouseRadAngle = 0;
	private static double mouseDistance;
	
	public static double getMouseX() {
		return mouseX;
	}
	
	public static double getMouseY()  {
		return mouseY;
	}
	
	public static int getMouseB() {
		return mouseB;
	}
	
	public static double mouseDistance() {
		mouseDistance = Math.abs(Math.sqrt((((mouseX / MainGame.getScreenScale()) - (Camera.getPlayerXCoord() - Camera.getCameraXCoord())) * 
				((mouseX / MainGame.getScreenScale()) - (Camera.getPlayerXCoord() - Camera.getCameraXCoord()))) + 
				(((mouseY / MainGame.getScreenScale()) - (Camera.getPlayerYCoord() - Camera.getCameraYCoord())) * 
				((mouseY / MainGame.getScreenScale()) - (Camera.getPlayerYCoord() - Camera.getCameraYCoord())))));
		return mouseDistance;
	}
	
	/**
	 * Returns the angle of the mouse in radians from the player to the
	 * mouse pointer position.
	 * @return double mouseRadAngle
	 */
	public static double mouseRadAngle() {
		/*mouseRadAngle = Math.atan2((mouseY - ((MainGame.getScreenHeight() * MainGame.getScreenScale()) / 2) - 32),
				(mouseX - ((MainGame.getScreenWidth() * MainGame.getScreenScale()) / 2) - 32));*/
		/*mouseRadAngle = Math.atan2((mouseY - (Camera.getPlayerYCoord() - Camera.getYScroll()) * (MainGame.getScreenScale()) - 16 * (MainGame.getScreenScale())),
				(mouseX - (Camera.getPlayerXCoord() - Camera.getXScroll()) * (MainGame.getScreenScale()) - 16 * (MainGame.getScreenScale())));*/
		mouseRadAngle = Math.atan2(((mouseY / MainGame.getScreenScale()) - (Camera.getPlayerYCoord() - Camera.getCameraYCoord()) - 16),
				((mouseX / MainGame.getScreenScale()) - (Camera.getPlayerXCoord() - Camera.getCameraXCoord()) - 16));
		return mouseRadAngle;
	}
	
	public static double mouseRadToDeg() {
		return mouseRadAngle * (180/Math.PI);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
		
	}

}
