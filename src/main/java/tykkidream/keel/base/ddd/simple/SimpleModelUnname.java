package tykkidream.keel.base.ddd.simple;

import java.io.Serializable;

import tykkidream.keel.base.tta.BaseModel;

public class SimpleModelUnname<E extends SimpleModelUnname<?, I>, I  extends Serializable> implements BaseModel<E, I>{

	private static final long serialVersionUID = -7225934699073832603L;
	
	protected E This = null;
	
	protected I id = null;

	@SuppressWarnings("unchecked")
	public SimpleModelUnname(){
		This = (E) this;
	}
	
	public SimpleModelUnname(I id){
		this();
		setId(id);
	}

	@Override
	public void setId(I id) {
		This.id = id;
	}

	@Override
	public I getId() {
		return This.id;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
	}

	@Override
	@SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
    	if (obj instanceof BaseModel) {
			return equals((SimpleModelUnname<E,I>) obj);
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
