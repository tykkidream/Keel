package tykkidream.keel.base.ddd.simple;


public class SimpleIDDouble extends SimpleID<Double>{
	
	private static final long serialVersionUID = -2127561293958746695L;
	
	public static final SimpleIDDouble NULL = new SimpleNullDoubleID();
	
	public SimpleIDDouble() {
	}
	
	public SimpleIDDouble(Double value){
		this.id = value;
	}

	@Override
	public Double getId() {
		return this.id;
	}

	@Override
	public void setId(Double id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
	 private static class SimpleNullDoubleID extends SimpleIDDouble{
			private static final long serialVersionUID = -7760882619888424264L;

			@Override
			public Double getId() {return null;}
			@Override
			public void setId(Double value) {}
	    }
}
