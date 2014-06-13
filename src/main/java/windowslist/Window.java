package windowslist;

import java.awt.Rectangle;

public class Window {

	private int hwnd;
	private String title;
	private Rectangle bound;

	public Window(int hwnd, String title, Rectangle bound) {
		super();
		this.hwnd = hwnd;
		this.title = title;
		this.bound = bound;
	}

	public int getHwnd() {
		return hwnd;
	}

	public String getTitle() {
		return title;
	}

	public Rectangle getBound() {
		return bound;
	}

	public String toString() {
		return "[" + hwnd + "] - " + title + " - " + bound;
	}

}
