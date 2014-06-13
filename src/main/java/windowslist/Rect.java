package windowslist;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Structure;

public class Rect extends Structure {

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
