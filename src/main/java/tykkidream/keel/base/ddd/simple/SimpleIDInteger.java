package tykkidream.keel.base.ddd.simple;

public class SimpleIDInteger extends SimpleID<Integer>{

	private static final long serialVersionUID = -6501856583001680598L;
	
	public static final SimpleIDInteger NULL = new SimpleNullIntegerID();
	
	public SimpleIDInteger() {
	}
	
	public SimpleIDInteger(Integer value){
		this.id = value;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		if (null == this.id) {
			this.id = id;
		}
	}

	private static class SimpleNullIntegerID extends SimpleIDInteger{
			private static final long serialVersionUID = -4346991621220110288L;
			@Override
			public Integer getId() {return null;}
			@Override
			public void setId(Integer id) {}
	    }
}
