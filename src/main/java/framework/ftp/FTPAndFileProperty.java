package framework.ftp;

import java.io.File;

public class FTPAndFileProperty {

	private final String server;
	private final String user;
	private final String pass;
	private final int port;
	private final String ftpPath;
	private String localPath;
	private String encoding = "UTF-8";
	private String fileName;
	private String localPathAndName;

	public FTPAndFileProperty(String server, String user, String pass, String port, String ftpPath, String localPath) {
		this.server = server;
		this.user = user;
		this.pass = pass;
		this.ftpPath = ftpPath;
		this.localPath = localPath;
		if (port == null || port.equals("")) {
			this.port = 21;
		}else {
			this.port = Integer.parseInt(port);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public String getLocalPathAndName() {
		return localPathAndName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		if (!this.localPath.endsWith("/") && !this.localPath.endsWith("\\")) {
			this.localPath += File.separator;
		}
		this.localPathAndName = this.localPath + fileName;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getServer() {
		return server;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public int getPort() {
		return port;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public String getLocalPath() {
		return localPath;
	}

	@Override
	public String toString() {
		return "FTPProperty{" +
				"server='" + server + '\'' +
				", user='" + user + '\'' +
				", pass='" + pass + '\'' +
				", port=" + port +
				", ftpPath='" + ftpPath + '\'' +
				", localPath='" + localPath + '\'' +
				'}';
	}
}
