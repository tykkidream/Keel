package tykkidream.keel.base.ddd.simple;

public class SimpleIDShort extends SimpleID<Short> {
	private static final long serialVersionUID = -8449291043378243844L;
	
	public static final SimpleIDShort NULL = new  SimpleNullShortID();
	
	protected Short value = null;
	
	public SimpleIDShort() {
	}
	
	public SimpleIDShort(Short value){
		this.value = value;
	}

	@Override
	public Short getValue() {
		return this.value;
	}

	@Override
	public void setValue(Short value) {
		if (null == this.value) {
			this.value = value;
		}
	}

	private static class SimpleNullShortID extends SimpleIDShort {
		private static final long serialVersionUID = -3863926568451378589L;

		@Override
		public Short getValue() {
			return null;
		}

		@Override
		public void setValue(Short id) {
		}
	}
}