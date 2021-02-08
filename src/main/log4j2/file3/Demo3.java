package file3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 {
	private static final Logger logger = LoggerFactory.getLogger(Demo3.class);
	
	public static void print() {
		logger.error("Demo3");
		logger.info("Demo3");
		logger.debug("Demo3");
	}
}
