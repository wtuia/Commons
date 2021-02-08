package file2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 {
	private static final Logger logger = LoggerFactory.getLogger(Demo2.class);
	
	public static void print() {
		logger.error("Demo2");
		logger.info("Demo2");
		logger.debug("Demo2");
	}
}
