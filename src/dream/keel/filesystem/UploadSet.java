package dream.keel.filesystem;

public class UploadSet {
	//上传文件、文件类型、文件名称的集合
	private UploadRequest[] request = null;
	//上传文件是否成功
	private boolean success = false;
	//上传文件的总数
	private int successTotal = 0;
	
	public UploadRequest[] getRequest() {
		return request;
	}
	public void setRequest(UploadRequest[] request) {
		this.request = request;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getSuccessTotal() {
		return successTotal;
	}
	public void setSuccessTotal(int successTotal) {
		this.successTotal = successTotal;
	}
	
	
}
