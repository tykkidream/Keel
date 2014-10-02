package tykkidream.keel.base.ddd;

public class SimpleID<I> implements BaseID<I> {
	
	private static final long serialVersionUID = 21110648196894154L;
	
	protected I id = null;
	
	public SimpleID() {
		
	}

	public SimpleID(I id) {
		setId(id);
	}

	public I value() {
		return getId();
	}

	public I getId() {
		return id;
	}

	public void setId(I id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	 public boolean equals(Object obj) {
		if (obj instanceof SimpleID) {
			return equals((SimpleID<I>)obj);
		}
		return false;
	};
	 
    public boolean equals(SimpleID<I> obj) {
    	if (null != obj && this.getId() == obj.getId()) {
			return true;
		}
    	return false;
    }
}
