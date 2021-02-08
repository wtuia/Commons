package file1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1 {
	private static final Logger logger = LoggerFactory.getLogger(Demo1.class);
	
	public static void print() {
		logger.error("Demo1");
		logger.info("Demo1");
		logger.debug("Demo1");
	}
}
