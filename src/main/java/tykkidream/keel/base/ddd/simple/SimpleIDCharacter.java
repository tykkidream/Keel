package tykkidream.keel.base.ddd.simple;


public class SimpleIDCharacter extends SimpleID<Character>{
	
	private static final long serialVersionUID = 6313856733870785497L;
	
	public static final SimpleIDCharacter NULL = new SimpleNullCharacterID();
	
	public SimpleIDCharacter() {
	}
	
	public SimpleIDCharacter(Character value){
		this.id = value;
	}

	@Override
	public Character getId() {
		return this.id;
	}

	@Override
	public void setId(Character id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
	 private static class SimpleNullCharacterID extends SimpleIDCharacter{
			private static final long serialVersionUID = -2624256819470501340L;
			@Override
			public Character getId() {return null;}
			@Override
			public void setId(Character id) {}
	    }
}
