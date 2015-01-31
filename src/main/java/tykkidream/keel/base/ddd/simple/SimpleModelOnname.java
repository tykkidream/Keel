package tykkidream.keel.base.ddd.simple;

import java.io.Serializable;

import tykkidream.keel.base.tta.BaseModel;

public class SimpleModelOnname<E extends SimpleModelOnname<?, I>, I  extends Serializable>
	implements BaseModel<E, I>{

	private static final long serialVersionUID = -7225934699073832603L;
	
	protected I id = null;
	
	protected String name = null;
	
	public SimpleModelOnname(){}
	
	public SimpleModelOnname(I id){
		setId(id);
	}

	@Override
	public void setId(I id) {
		this.id = id;
	}

	@Override
	public I getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	@SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
    	if (obj instanceof BaseModel) {
			return equals((BaseModel<E,I>) obj);
		}
    	return false;
    }
    
    public boolean equals(BaseModel<E,I> obj) {
    	if (null != obj && null != this.getId() && this.getId().equals(obj.getId())){
    		return true;
		}
		return false;
    }
}
