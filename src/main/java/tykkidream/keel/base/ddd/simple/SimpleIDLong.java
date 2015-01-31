package tykkidream.keel.base.ddd.simple;

import tykkidream.keel.base.pattern.NullObject;

public class SimpleIDLong  extends SimpleID<Long>{
	
	private static final long serialVersionUID = 5602747457953676403L;

	public static final SimpleIDLong NULL = new SimpleNullLongID();
	
	public SimpleIDLong() {
	}
	
	public SimpleIDLong(Long value){
		this.id = value;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
	private static class SimpleNullLongID extends SimpleIDLong implements NullObject{
		private static final long serialVersionUID = -8631104908514165521L;
		@Override
		public Long getId() {return null;}
		@Override
		public void setId(Long id) {}
    }
}
