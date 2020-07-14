package framework.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FTPUtil {

	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

	/**
	 * 建立连接
	 */
	private static FTPClient ftpConnect(FTPAndFileProperty ftpAndFile) throws NullPointerException, IOException {
		int status;
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(ftpAndFile.getServer(), ftpAndFile.getPort());
		ftpClient.login(ftpAndFile.getUser(), ftpAndFile.getPass());
		status = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(status)) {
			throw new NullPointerException("ftp连接失败, status=" + status);
		}
		return ftpClient;
	}

	/**
	 * 断开连接
	 */
	private static void disConnect(FTPClient ftpClient) {
		if (ftpClient == null) {
			logger.error("ftp登出失败，ftp未建立连接");
			return;
		}
		try {
			ftpClient.logout();
		} catch (IOException e) {
			logger.error("ftp登出失败", e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					logger.error("ftp登出失败", e);
				}
			}
		}
	}

	/**
	 * 从FTP服务器下载文件
	 */
	public static void downloadFile(FTPAndFileProperty ftpAndFile) {
		String filePathAndName = ftpAndFile.getFtpPath() + File.separator + ftpAndFile.getFileName();
		FTPClient ftpClient = null;
		FileOutputStream fos = null;
		try {
			ftpClient = ftpConnect(ftpAndFile);
			ftpClient.setControlEncoding(ftpAndFile.getEncoding());
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件
			File file=new File(ftpAndFile.getLocalPathAndName());
			ftpClient.changeWorkingDirectory(ftpAndFile.getFtpPath());
			ftpClient.enterLocalPassiveMode();
			fos=new FileOutputStream(file);
			boolean result = ftpClient.retrieveFile(
					new String(ftpAndFile.getFileName().getBytes(StandardCharsets.UTF_8),
							StandardCharsets.ISO_8859_1),fos);
			if(result) {
				logger.info("文件{}下载成功!", filePathAndName);
			}else {
				logger.error("文件{}下载失败!", filePathAndName);
			}
		}catch (Exception e) {
			logger.error("", e);
		}finally {
			StreamUtil.closeOutPutStream(fos);
			disConnect(ftpClient);
		}
	}


	public static void upload(FTPAndFileProperty ftpAndFile) {
		try {
			FTPClient ftpClient = ftpConnect(ftpAndFile);
			upload(ftpClient, ftpAndFile);
			disConnect(ftpClient);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	// 如果上传失败，确认文件夹是否具有读写权限
	private static void upload(FTPClient ftpClient, FTPAndFileProperty ftpAndFile) {
		logger.info("上传文件:{}" , ftpAndFile.getFileName());
		InputStream in = null;
		try {
			if(ftpAndFile.getFtpPath() !=null && !ftpAndFile.getFtpPath().equals("")) {
				if (!ftpClient.changeWorkingDirectory(ftpAndFile.getFtpPath())) {
					logger.error("切换目录到{}失败",ftpAndFile.getFtpPath());
					return;
				}
			}
			ftpClient.setControlEncoding(ftpAndFile.getEncoding());
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			in = new FileInputStream(ftpAndFile.getLocalPathAndName());
			if (!ftpClient.storeFile(
					new String(ftpAndFile.getFileName().getBytes(StandardCharsets.UTF_8),
					StandardCharsets.ISO_8859_1), in)) {
				logger.error("上传文件{}失败", ftpAndFile.getFileName());
			}
		} catch (Exception e) {
			logger.error("上传文件{}失败", ftpAndFile.getFileName(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("inputStream关闭异常", e);
				}
			}
		}
	}


}
