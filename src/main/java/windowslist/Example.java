package windowslist;

import java.awt.Rectangle;
import java.util.Calendar;

public class Example {

	public static void main(String[] args) {

		long startTime = Calendar.getInstance().getTimeInMillis();
		System.out.println(">> Initializing the dll");
		Windows.getUser32Instance();
		System.out.println("dll initialized in " + (Calendar.getInstance().getTimeInMillis() - startTime) + "ms");

		System.out.println("\n>> All windows ordered by Z order:");
		for (Integer hWnd : Windows.getWindowsHWnd(false)) {
			boolean isVisible = Windows.isWindowVisible(hWnd);
			String title = Windows.getWindowTitle(hWnd);
			Rectangle bound = Windows.getWindowBound(hWnd);
			System.out.println((isVisible ? "Visible - " : "Hidden - ") + hWnd + " - " + title + " - [x=" + bound.x + ", y=" + bound.y + ", w=" + bound.width + ", h=" + bound.height + "]");
		}

		System.out.println("\n>> All visible windows ordered by Z order:");
		for (Window window : Windows.getWindows(true)) {
			System.out.println(window);
		}

	}

}
