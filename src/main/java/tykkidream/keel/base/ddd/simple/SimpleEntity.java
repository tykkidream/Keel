package tykkidream.keel.base.ddd.simple;

import java.io.Serializable;

import tykkidream.keel.base.ddd.BaseEntity;

public class SimpleEntity<ID extends Serializable> implements BaseEntity<ID> {
	protected ID id;

	@Override
	public ID id() {
		return this.id;
	}

	@Override
	public void id(ID id) {
		this.id = id;
	}

}
