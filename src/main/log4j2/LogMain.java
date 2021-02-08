import file1.Demo1;
import file2.Demo2;
import file3.Demo3;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

public class LogMain {
	
	public static void main(String[] args) {
			Configurator.initialize("log4j2.xml",
					System.getProperty("user.dir") + File.separator + "log4j2.xml");
		Demo1.print();
		Demo2.print();
		Demo3.print();
	}
}
