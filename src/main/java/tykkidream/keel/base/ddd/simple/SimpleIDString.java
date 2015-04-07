package tykkidream.keel.base.ddd.simple;

public class SimpleIDString extends SimpleID<String> {
	
	private static final long serialVersionUID = -3292854939554936605L;
	
	public static final SimpleIDString NULL = new SimpleNullStringID();

	protected String value = null;
	
	public SimpleIDString() {
	}

	public SimpleIDString(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public void setValue(String value) {
		if (null == this.value) {
			this.value = value;
		}
	}

	private static class SimpleNullStringID extends SimpleIDString {
		private static final long serialVersionUID = 6374867016965795814L;

		@Override
		public String getValue() {
			return null;
		}

		@Override
		public void setValue(String id) {
		}
	}
}
