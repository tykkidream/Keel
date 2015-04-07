package tykkidream.keel.base.ddd.simple;


public class SimpleIDDouble extends SimpleID<Double>{
	
	private static final long serialVersionUID = -2127561293958746695L;
	
	public static final SimpleIDDouble NULL = new SimpleNullDoubleID();
	
	protected Double value = null;
	
	public SimpleIDDouble() {
	}
	
	public SimpleIDDouble(Double value){
		this.value = value;
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void setValue(Double value) {
		if (null == this.value) {
			this.value = value;
		}
	}
	
	 private static class SimpleNullDoubleID extends SimpleIDDouble{
			private static final long serialVersionUID = -7760882619888424264L;

			@Override
			public Double getValue() {return null;}
			@Override
			public void setValue(Double value) {}
	    }
}
