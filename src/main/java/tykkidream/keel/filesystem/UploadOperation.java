package tykkidream.keel.filesystem;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import tykkidream.keel.filesystem.UploadResponse;

public class UploadOperation {
	private String savePath = "upload/";

	public UploadOperation() {

	}

	public UploadOperation(String savePath) {
		this.savePath = savePath;
	}

	public UploadSet upload(File[] file, String[] fileContentType, String[] fileName) {
		if (file == null) {
			return null;
		}
		
		UploadRequest[] request = new UploadRequest[file.length];
		for (int i = 0; i < request.length; i++) {
			request[i].setFile(file[i]);
			request[i].setFileContentType(fileContentType[i]);
			request[i].setFileFileName(fileName[i]);
		}

		return upload(request);
	}

	public UploadSet upload(UploadRequest[] request) {
		if (request == null || request.length <= 0) {
			return null;
		}

		UploadSet set = new UploadSet();
		set.setSuccess(true);
		int total = 0;
		for (int i = 0; i < request.length; i++) {
			UploadResponse response = upload(request[i]);
			if (response.getSuccess()) {
				total++;
			} else {
				response.setSuccess(false);
			}
		}
		set.setSuccessTotal(total);
		set.setRequest(request);

		return set;
	}

	public UploadResponse upload(UploadRequest request) {
		UploadResponse response = new UploadResponse();

		try {
			// 保存的文件名
			String saveName = UUID.randomUUID().toString();
			// 保存的路径
			String saveDirectory = ServletActionContext.getServletContext().getRealPath(savePath);
			// 设置扩展名
			String fileName = request.getFileName();
			if (fileName == null) {
				fileName = request.getFile().getName();
			}
			int i = fileName.lastIndexOf(".");
			if (i > 0) {
				String extension = fileName.substring(i);
				if (!"".equals(extension)) {
					saveName = saveName + extension;
				}
			}

			File save = new File(saveDirectory, saveName);

			// 执行保存文件
			FileUtils.copyFile(request.getFile(), save);
			
			response.setRequest(request);
			response.setFile(save);
			response.setSuccess(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
}
