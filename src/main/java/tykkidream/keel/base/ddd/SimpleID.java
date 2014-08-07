package tykkidream.keel.base.ddd;

public class SimpleID implements BaseID {
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
