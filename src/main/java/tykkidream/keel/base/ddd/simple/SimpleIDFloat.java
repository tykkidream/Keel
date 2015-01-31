package tykkidream.keel.base.ddd.simple;


public class SimpleIDFloat extends SimpleID<Float>{
	
	private static final long serialVersionUID = -2825882468050364699L;
	
	public static final SimpleIDFloat NULL = new SimpleNullFloatID();
	
	public SimpleIDFloat() {
	}
	
	public SimpleIDFloat(Float value){
		this.id = value;
	}

	@Override
	public Float getId() {
		return this.id;
	}

	@Override
	public void setId(Float id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
    private static class SimpleNullFloatID extends SimpleIDFloat{
		private static final long serialVersionUID = -3299651332004901163L;
		@Override
		public Float getId() {return null;}
		@Override
		public void setId(Float id) {}
    }
}
