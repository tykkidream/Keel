package tykkidream.keel.base.domain;

public class SimpleID implements BaseID {
	private Long id;

	public SimpleID(Long id) {
		this.id = id;
	}

	@Override
	public Long longID() {
		return this.id;
	}

}
