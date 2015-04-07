package tykkidream.keel.base.ddd.simple;

import tykkidream.keel.base.pattern.NullObject;

public class SimpleIDLong  extends SimpleID<Long>{
	
	private static final long serialVersionUID = 5602747457953676403L;

	public static final SimpleIDLong NULL = new SimpleNullLongID();
	
	protected Long value = null;
	
	public SimpleIDLong() {
	}
	
	public SimpleIDLong(Long value){
		this.value = value;
	}

	@Override
	public Long getValue() {
		return this.value;
	}

	@Override
	public void setValue(Long value) {
		if (null == this.value) {
			this.value = value;
		}
	}
	
	private static class SimpleNullLongID extends SimpleIDLong implements NullObject{
		private static final long serialVersionUID = -8631104908514165521L;
		@Override
		public Long getValue() {return null;}
		@Override
		public void setValue(Long id) {}
    }
}
