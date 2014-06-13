package windowslist;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public class WindowsList {

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

	// Ordered by Z index
	public List<Window> getWindowsList(boolean shouldBeVisible) {
		List<Window> result = new ArrayList<Window>();
		int hWnd = User32.instance.GetTopWindow(null);
		while (hWnd != 0) {
			hWnd = User32.instance.GetWindow(hWnd, 2);
			Window window = getWindow(hWnd);
			if ((!shouldBeVisible || User32.instance.IsWindowVisible(hWnd))) {
				if (window.getTitle() != null && window.getTitle().trim().length() > 0) {
					result.add(window);
				}
			}
		}
		return result;
	}

	public Window getWindow(int hWnd) {
		// Get Title
		byte[] buffer = new byte[1024];
		User32.instance.GetWindowTextA(hWnd, buffer, buffer.length);
		String title = Native.toString(buffer);

		// Get Bounds
		RECT rect = new RECT();
		User32.instance.GetWindowRect(hWnd, rect);
		Rectangle bound = new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);

		Window result = new Window(hWnd, title, bound);
		return result;
	}

	public boolean isWindowVisible(Window window) {
		return User32.instance.IsWindowVisible(window.getHwnd());
	}

	public boolean showWindow(Window window, int nCmdShow) {
		return User32.instance.ShowWindow(window.getHwnd(), nCmdShow);
	}

	protected static interface User32 extends StdCallLibrary {
		final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

		boolean IsWindowVisible(int hWnd);

		int GetWindow(int hWnd, int flag);

		int GetWindowRect(int hWnd, RECT r);

		void GetWindowTextA(int hWnd, byte[] buffer, int buflen);

		boolean ShowWindow(int hWnd, int nCmdShow);

		int GetTopWindow(Pointer wnd);
	}

	protected static class RECT extends Structure {
		public int left, top, right, bottom;

		protected List<String> getFieldOrder() {
			List<String> list = new ArrayList<String>();
			list.add("left");
			list.add("top");
			list.add("right");
			list.add("bottom");
			return list;
		}
	}

}
