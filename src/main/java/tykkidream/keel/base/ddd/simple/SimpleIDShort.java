package tykkidream.keel.base.ddd.simple;

public class SimpleIDShort extends SimpleID<Short> {
	private static final long serialVersionUID = -8449291043378243844L;
	
	public static final SimpleIDShort NULL = new  SimpleNullShortID();
	
	public SimpleIDShort() {
	}
	
	public SimpleIDShort(Short value){
		this.id = value;
	}

	@Override
	public Short getId() {
		return this.id;
	}

	@Override
	public void setId(Short id) {
		if (null == this.id) {
			this.id = id;
		}
	}

	private static class SimpleNullShortID extends SimpleIDShort {
		private static final long serialVersionUID = -3863926568451378589L;

		@Override
		public Short getId() {
			return null;
		}

		@Override
		public void setId(Short id) {
		}
	}
}