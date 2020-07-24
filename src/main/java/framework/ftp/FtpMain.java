package framework.ftp;

import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

public class FtpMain {

	public static void main(String[] args) {
		Configurator.initialize("log4j2.xml",System.getProperty("user.dir") + File.separator + "log4j2.xml");
		FTPAndFileProperty ftpAndFileProperty =
				new FTPAndFileProperty("","", "", "21",
						"test", "temp");
		ftpAndFileProperty.setEncoding("GB2312");
		ftpAndFileProperty.setFileName("test.txt");
		FTPUtil.upload(ftpAndFileProperty);
	}
}
