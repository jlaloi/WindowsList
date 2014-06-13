package windowslist;

import java.awt.Rectangle;

public class Window {

	private int hWnd;

	public Window(int hWnd) {
		super();
		this.hWnd = hWnd;
	}

	public boolean isVisible() {
		return Windows.isWindowVisible(hWnd);
	}

	public String getTitle() {
		return Windows.getWindowTitle(hWnd);
	}

	public Rectangle getBound() {
		return Windows.getWindowBound(hWnd);
	}

	public void show(int nCmdShow) {
		Windows.showWindow(hWnd, nCmdShow);
	}

	public String toString() {
		Rectangle bound = getBound();
		return hWnd + " - " + getTitle() + " (" + (isVisible() ? "Visible" : "Hidden") + ") [x=" + bound.x + ", y=" + bound.y + ", w=" + bound.width + ", h=" + bound.height + "]";
	}

}
