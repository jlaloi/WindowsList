package windowslist;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;

public class Windows {

	public static final int SW_HIDE = 0;
	public static final int SW_SHOWNORMAL = 1;
	public static final int SW_SHOWMINIMIZED = 2;
	public static final int SW_SHOWMAXIMIZED = 3;
	public static final int SW_SHOWNOACTIVATE = 4;
	public static final int SW_SHOW = 5;
	public static final int SW_MINIMIZE = 6;
	public static final int SW_SHOWMINNOACTIVE = 7;
	public static final int SW_SHOWNA = 8;
	public static final int SW_RESTORE = 9;
	public static final int SW_SHOWDEFAULT = 10;
	public static final int SW_FORCEMINIMIZE = 11;

	private static WindowsUser32 windowsListUser32;

	public static WindowsUser32 getUser32Instance() {
		if (windowsListUser32 == null) {
			windowsListUser32 = (WindowsUser32) Native.loadLibrary("user32", WindowsUser32.class);
		}
		return windowsListUser32;
	}

	// Ordered by Z index
	public static List<Integer> getWindowsHWnd(boolean shouldBeVisible) {
		List<Integer> result = new ArrayList<Integer>();
		int hWnd = getUser32Instance().GetTopWindow(null);
		while (hWnd != 0) {
			hWnd = getUser32Instance().GetWindow(hWnd, 2);
			if (!shouldBeVisible || getUser32Instance().IsWindowVisible(hWnd)) {
				result.add(hWnd);
			}
		}
		return result;
	}

	public static List<Window> getWindows(boolean shouldBeVisible) {
		List<Window> result = new ArrayList<Window>();
		for (int hWnd : getWindowsHWnd(shouldBeVisible)) {
			result.add(new Window(hWnd));
		}
		return result;
	}

	public static String getWindowTitle(int hWnd) {
		byte[] buffer = new byte[1024];
		getUser32Instance().GetWindowTextA(hWnd, buffer, buffer.length);
		String title = Native.toString(buffer);
		return title;
	}

	public static Rectangle getWindowBound(int hWnd) {
		Rect rect = new Rect();
		getUser32Instance().GetWindowRect(hWnd, rect);
		Rectangle bound = new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
		return bound;
	}

	public static boolean isWindowVisible(int hWnd) {
		return getUser32Instance().IsWindowVisible(hWnd);
	}

	public static boolean showWindow(int hWnd, int nCmdShow) {
		return getUser32Instance().ShowWindow(hWnd, nCmdShow);
	}

}
