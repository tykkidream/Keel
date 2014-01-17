package dream.keel.filesystem;

import java.io.File;

public class UploadResponse {
	//上传文件、文件类型、文件名称的集合
	private UploadRequest request = null;
	//上传文件是否成功
	private boolean success = false;
	//上传的文件
	private File file = null;
	
	public UploadRequest getRequest() {
		return request;
	}
	public void setRequest(UploadRequest request) {
		this.request = request;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
