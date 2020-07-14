package framework.ftp;

public class FtpMain {

	public static void main(String[] args) {
		FTPAndFileProperty ftpAndFileProperty =
				new FTPAndFileProperty("139.155.83.148","yj", "yj357322", "21",
						"ftpFile", "temp");
		ftpAndFileProperty.setEncoding("GB2312");
		ftpAndFileProperty.setFileName("test.txt");
		FTPUtil.upload(ftpAndFileProperty);
	}
}
