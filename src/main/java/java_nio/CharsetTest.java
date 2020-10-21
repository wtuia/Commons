package java_nio;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;

public class CharsetTest {

	@Test
	public void allCharset() {
		System.out.println(System.getProperty("file.encoding"));
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		for (Map.Entry<String, Charset> entry : charsets.entrySet()) {
			System.out.println("alias:" + entry.getKey() + ", charset:" + entry.getValue());
		}

	}
}
