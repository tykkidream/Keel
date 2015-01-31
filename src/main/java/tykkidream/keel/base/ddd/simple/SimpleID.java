package tykkidream.keel.base.ddd.simple;

import tykkidream.keel.base.ddd.BaseID;

public abstract class SimpleID<I> implements BaseID<I> {
	
	private static final long serialVersionUID = 21110648196894154L;

	/*public static final SimpleShortID NULL_SHORT = new SimpleShortID();
	public static final SimpleIntegerID NULL_INTEGER = new SimpleIntegerID();
	public static final SimpleLongID NULL_LONG = new SimpleLongID();
	public static final SimpleFloatID NULL_FLOAT = new SimpleFloatID();
	public static final SimpleDoubleID NULL_DOUBLE = new SimpleDoubleID();
	public static final SimpleStringID NULL_STRING = new SimpleStringID();
	public static final SimpleCharacterID NULL_CHARACTER = new SimpleCharacterID();*/
	
	public static final SimpleIDShort NULL_SHORT = SimpleIDShort.NULL;
	public static final SimpleIDInteger NULL_INTEGER = SimpleIDInteger.NULL;
	public static final SimpleIDLong NULL_LONG = SimpleIDLong.NULL;
	public static final SimpleIDFloat NULL_FLOAT = SimpleIDFloat.NULL;
	public static final SimpleIDDouble NULL_DOUBLE = SimpleIDDouble.NULL;
	public static final SimpleIDString NULL_STRING = SimpleIDString.NULL;
	public static final SimpleIDCharacter NULL_CHARACTER = SimpleIDCharacter.NULL;
	
	protected I id = null;
	
	public SimpleID() {
		
	}

	public SimpleID(I id) {
		setId(id);
	}

	public I value() {
		return getId();
	}

	public I getId() {
		return id;
	}

	public void setId(I id) {
		if (null == this.id) {
			this.id = id;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	 public boolean equals(Object obj) {
		if (obj instanceof SimpleID) {
			return equals((SimpleID<I>)obj);
		}
		return false;
	};
	 
    public boolean equals(SimpleID<I> obj) {
    	if (null != obj && this.getId().equals(obj.getId())) {
			return true;
		}
    	return false;
    }
    
   /* private static class SimpleShortID extends SimpleID<Short>{
		private static final long serialVersionUID = -8449291043378243844L;
		@Override
		public Short getId() {return null;}
		@Override
		public void setId(Short id) {}
    }
    
    private static class SimpleIntegerID extends SimpleID<Integer>{
		private static final long serialVersionUID = -4346991621220110288L;
		@Override
		public Integer getId() {return null;}
		@Override
		public void setId(Integer id) {}
    }
    
    private static class SimpleLongID extends SimpleID<Long>{
		private static final long serialVersionUID = -8631104908514165521L;
		@Override
		public Long getId() {return null;}
		@Override
		public void setId(Long id) {}
    }
    
    private static class SimpleFloatID extends SimpleID<Float>{
		private static final long serialVersionUID = -3299651332004901163L;
		@Override
		public Float getId() {return null;}
		@Override
		public void setId(Float id) {}
    }
    
    private static class SimpleDoubleID extends SimpleID<Double>{
		private static final long serialVersionUID = -7760882619888424264L;

		@Override
		public Double getId() {return null;}
		@Override
		public void setId(Double value) {}
    }
    
    private static class SimpleStringID extends SimpleID<String>{
		private static final long serialVersionUID = 6374867016965795814L;
		@Override
		public String getId() {return null;}
		@Override
		public void setId(String id) {}
    }
    
    private static class SimpleCharacterID extends SimpleID<Character>{
		private static final long serialVersionUID = -2624256819470501340L;
		@Override
		public Character getId() {return null;}
		@Override
		public void setId(Character id) {}
    }*/
}
