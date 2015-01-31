package tykkidream.keel.base.ddd.simple;

public class SimpleIDString extends SimpleID<String> {
	
	private static final long serialVersionUID = -3292854939554936605L;
	
	public static final SimpleIDString NULL = new SimpleNullStringID();

	public SimpleIDString() {
	}

	public SimpleIDString(String value) {
		this.id = value;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		if (null == this.id) {
			this.id = id;
		}
	}

	private static class SimpleNullStringID extends SimpleIDString {
		private static final long serialVersionUID = 6374867016965795814L;

		@Override
		public String getId() {
			return null;
		}

		@Override
		public void setId(String id) {
		}
	}
}
