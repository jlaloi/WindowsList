package windowslist;

import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public interface WindowsUser32 extends StdCallLibrary {

	boolean IsWindowVisible(int hWnd);

	int GetWindow(int hWnd, int flag);

	int GetWindowRect(int hWnd, Rect r);

	void GetWindowTextA(int hWnd, byte[] buffer, int buflen);

	boolean ShowWindow(int hWnd, int nCmdShow);

	int GetTopWindow(Pointer wnd);

}