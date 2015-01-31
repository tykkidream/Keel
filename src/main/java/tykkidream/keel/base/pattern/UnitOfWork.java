package tykkidream.keel.base.pattern;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
	private List<Object> newObjects = new ArrayList<>();
	private List<Object> dirtyObjects = new ArrayList<>();
	private List<Object> removeObjects = new ArrayList<>();
	
	public void registerNew(Object obj){
		if (null == obj)
			return;
		
		if (!newObjects.contains(obj) && !dirtyObjects.contains(obj)  && !removeObjects.contains(obj) ) {
			newObjects.add(obj);
		}
	}
	
	public void registerDirty(Object obj){
		if (null == obj)
			return;
		
		if (!newObjects.contains(obj) && !dirtyObjects.contains(obj)  && !removeObjects.contains(obj) ) {
			dirtyObjects.add(obj);
		}
	}
	
	public void registerRemoved(Object obj){
		if (null == obj)
			return;
		
		if (newObjects.remove(obj))
			return;
		
		dirtyObjects.remove(obj);
		
		if (!removeObjects.contains(obj)) {
			removeObjects.add(obj);
		}
	}
	
	public void commit(){
		
	}
}
