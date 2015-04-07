package tykkidream.keel.base.ddd.simple;

public class SimpleIDInteger extends SimpleID<Integer>{

	private static final long serialVersionUID = -6501856583001680598L;
	
	public static final SimpleIDInteger NULL = new SimpleNullIntegerID();
	
	protected Integer value = null;
	
	public SimpleIDInteger() {
	}
	
	public SimpleIDInteger(Integer value){
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public void setValue(Integer value) {
		if (null == this.value) {
			this.value = value;
		}
	}

	private static class SimpleNullIntegerID extends SimpleIDInteger{
			private static final long serialVersionUID = -4346991621220110288L;
			@Override
			public Integer getValue() {return null;}
			@Override
			public void setValue(Integer id) {}
	    }
}
