package dream.keel.filesystem;

import java.io.File;

public class UploadRequest {
	/**
	 * 实际上传文件
	 */
	private File file;
	/**
	 * 文件的内容类型
	 */
	private String fileContentType;
	/**
	 * 上传文件名称
	 */
	private String fileName;
	
	public UploadRequest(){
		
	}
	
	public UploadRequest(File file,String fileContentType,String fileName){
		this.file = file;
		this.fileContentType = fileContentType;
		this.fileName = fileName;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileFileName(String fileName) {
		this.fileName = fileName;
	}
}
