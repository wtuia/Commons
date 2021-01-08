package java_net.socket.aiosocket;

import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class AioClient {

	
	public void model1() throws Exception{
		AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
		socketChannel.connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9797)).get();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		Charset utf = StandardCharsets.UTF_8;
		while (true) {
			socketChannel.write(ByteBuffer.wrap("hello server".getBytes(utf))).get();
			socketChannel.read(buffer).get();
			buffer.flip();
			String content = utf.decode(buffer).toString();
			buffer.clear();
			System.out.println("服务器信息："+content);
		}
	}
	
	@Test
	public void model2() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		Charset utf = StandardCharsets.UTF_8;
		AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
		socketChannel.connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 9898), null,
				new CompletionHandler<Void, Void>() {
					@Override
					public void completed(Void result, Void attachment) {
						try {
							socketChannel.write(ByteBuffer.wrap("hello server".getBytes(utf))).get();
							socketChannel.read(buffer).get();
							buffer.flip();
							String content = utf.decode(buffer).toString();
							buffer.clear();
							System.out.println("服务器信息："+content);
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					}
					
					@Override
					public void failed(Throwable exc, Void attachment) {
						exc.printStackTrace();
					}
				});
		Thread.sleep(10000);
	}
}

