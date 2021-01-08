package java_net.socket.aiosocket;

import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

public class AioServer {
	
	
	public static void main(String[] args) throws Exception {
	}
	
	/**
	 * AIO 通信模型1
	 */
	@Test
	public void model1() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Charset utf = StandardCharsets.UTF_8;
		Configurator.initialize("log4j2.xml",
				System.getProperty("user.dir") + File.separator + "log4j2.xml");
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel
				.open().bind(new InetSocketAddress(9797));
		Future<AsynchronousSocketChannel> future = serverChannel.accept();
		AsynchronousSocketChannel channel = future.get();
		buffer.clear();
		while (true) {
			channel.read(buffer).get();
			buffer.flip();
			String content = utf.decode(buffer).toString();
			buffer.clear();
			System.out.println("客户端信息："+content);
			channel.write(ByteBuffer.wrap(("hello client :" + System.currentTimeMillis()).getBytes(utf))).get();
			Thread.sleep(2000);
		}
	}
	
	/**
	 * AIO 通信模型2
	 */
	@Test
	public void model2() throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.clear();
		Charset utf = StandardCharsets.UTF_8;
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel
				.open().bind(new InetSocketAddress(9898));
		serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			@Override
			public void completed(AsynchronousSocketChannel channel, Object attachment) {
				serverChannel.accept(null, this);
				try {
					buffer.flip();
					channel.read(buffer).get();
					String content = utf.decode(buffer).toString();
					buffer.clear();
					System.out.println("客户端信息："+content);
					channel.write(ByteBuffer.wrap(("hello client :" + System.currentTimeMillis()).getBytes(utf))).get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void failed(Throwable exc, Object attachment) {
				exc.printStackTrace();
			}
		});
		Thread.sleep(10000);
	}
}
