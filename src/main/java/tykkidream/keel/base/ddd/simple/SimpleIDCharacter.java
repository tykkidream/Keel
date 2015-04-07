package tykkidream.keel.base.ddd.simple;


public class SimpleIDCharacter extends SimpleID<Character>{
	
	private static final long serialVersionUID = 6313856733870785497L;
	
	public static final SimpleIDCharacter NULL = new SimpleNullCharacterID();
	
	protected Character value = null;
	
	public SimpleIDCharacter() {
	}
	
	public SimpleIDCharacter(Character value){
		this.value = value;
	}

	@Override
	public Character getValue() {
		return this.value;
	}

	@Override
	public void setValue(Character id) {
		if (null == this.value) {
			this.value = id;
		}
	}
	
	 private static class SimpleNullCharacterID extends SimpleIDCharacter{
			private static final long serialVersionUID = -2624256819470501340L;
			@Override
			public Character getValue() {return null;}
			@Override
			public void setValue(Character id) {}
	    }
}
