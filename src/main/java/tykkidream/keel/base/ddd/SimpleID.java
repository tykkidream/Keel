package tykkidream.keel.base.ddd;

public class SimpleID implements BaseID {
	
	private static final long serialVersionUID = 21110648196894154L;
	
	private Long id;

	public SimpleID(Long id) {
		setId(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long value() {
		return getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (null == this.id) {
			this.id = id;
		}
	}
}
