package tykkidream.keel.struts2;

import org.apache.struts2.json.annotations.JSON;

import tykkidream.keel.base.BaseModelCasecade;

@SuppressWarnings("serial")
public abstract class BaseModel<T extends BaseModel<?>> implements BaseModelCasecade<T>{

	public boolean getLeaf() {
	     boolean leaf = true;
	     if (this.getChildren() != null && this.getChildren().size() > 0) {
	         leaf = false;
	     }
	     return leaf;
	 }

	
	public boolean getLoaded(){
	     boolean loaded = true;
	     if (getChildren() != null && this.getChildren().size() > 0) {
	         loaded = false;
	     }
	     return loaded;
	 }

	
	@JSON(deserialize=true,serialize = false)
	public String getPathCode() {
		if (this.getPath() != null) {
			return this.getPath().toString();
		}
		return null;
	}
	
	public void setPathCode(String pathCode) {
		if (pathCode != null) {
			this.setPath(pathCode.split(","));
		}
	}
}
