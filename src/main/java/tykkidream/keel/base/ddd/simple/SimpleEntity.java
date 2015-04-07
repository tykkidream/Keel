package tykkidream.keel.base.ddd.simple;

import java.io.Serializable;

import tykkidream.keel.base.ddd.BaseEntity;
import tykkidream.keel.util.Tools;

public abstract class SimpleEntity<ID extends Serializable> implements BaseEntity<ID>, Serializable {

	private static final long serialVersionUID = -4805022512833963622L;
	
	protected ID id;

	@Override
	public ID id() {
		return this.id;
	}

	@Override
	public void id(ID id) {
		if(Tools.toolIsNull(this.id))
			this.id = id;
	}
	
	protected ID getId() {
		return this.id;
	}

	protected void setId(ID id) {
		this.id = id;
	}
	

}
