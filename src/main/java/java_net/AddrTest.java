package java_net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class AddrTest {
	@Test
	public void initAddr() throws UnknownHostException {
		InetAddress iad= InetAddress.getByName("0.0.0.0");
		System.out.println(Arrays.toString(iad.getAddress()));
	}
}