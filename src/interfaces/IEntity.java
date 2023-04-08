package interfaces;

import java.util.HashMap;
import java.util.List;

public abstract class IEntity {
	public abstract List<Object> columnsValue();
	public abstract HashMap<Integer, String> getMap();
}
