package tykkidream.keel.base.domain;

public class SimpleID implements BaseID {
	private Long id;

	public SimpleID(Long id) {
		setId(id);
	}

	@Override
	public Long longID() {
		return getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
