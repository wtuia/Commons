package java_nio;

import java.nio.CharBuffer;

public class BufferTest {

	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(8);
		System.out.println("capacity:"  + buffer.capacity());
		System.out.println("limit:" + buffer.limit());
		System.out.println("position:" + buffer.position());
		buffer.put("a");
		buffer.put("b");
		buffer.put("c");
		System.out.println("position:" + buffer.position());
		buffer.flip();
		System.out.println("flip limit:" + buffer.limit());
		System.out.println("flip position:" + buffer.position());
		System.out.println("get:" + buffer.get());
		System.out.println("position:" + buffer.position());
		buffer.clear();
		System.out.println("clear limit:" + buffer.limit());
		System.out.println("clear position:" + buffer.position());
		System.out.println("get:" + buffer.get(2));
		System.out.println("position:" + buffer.position());
	}


}
