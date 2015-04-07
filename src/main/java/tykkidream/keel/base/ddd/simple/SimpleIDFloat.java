package tykkidream.keel.base.ddd.simple;


public class SimpleIDFloat extends SimpleID<Float>{
	
	private static final long serialVersionUID = -2825882468050364699L;
	
	public static final SimpleIDFloat NULL = new SimpleNullFloatID();
	
	protected Float value = null;
	
	public SimpleIDFloat() {
	}
	
	public SimpleIDFloat(Float value){
		this.value = value;
	}

	@Override
	public Float getValue() {
		return this.value;
	}

	@Override
	public void setValue(Float value) {
		if (null == this.value) {
			this.value = value;
		}
	}
	
    private static class SimpleNullFloatID extends SimpleIDFloat{
		private static final long serialVersionUID = -3299651332004901163L;
		@Override
		public Float getValue() {return null;}
		@Override
		public void setValue(Float id) {}
    }
}
