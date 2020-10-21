package java_nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class ChannelTest {

	/**
	 * Channel 只能与Buffer交互
	 * Channel 不能直接实例化 通过InputStream获取
	 */
	@Test
	public void channelMapTest() throws Exception {
		File file = new File("pom.xml");
		FileChannel inChannel = new FileInputStream(file).getChannel();
		FileChannel outChannel = new FileOutputStream("back.txt").getChannel();
		MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0 , file.length());
		Charset charset = StandardCharsets.UTF_8;
		outChannel.write(byteBuffer);
		byteBuffer.clear();
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer buffer = decoder.decode(byteBuffer);
		System.out.println(buffer);

	}

	/**
	 * 文件读取 与传统的inputStream byte[] 读取方式一致
	 * 避免使用map读取大文件时占用过多内存
	 */
	@Test
	public void channelReadTest(){
		File file = new File("application.properties");
		// 分开实例化 保证调用其close方法
		try (FileInputStream fis = new FileInputStream(file);
			 FileChannel fileChannel = fis.getChannel()){
			ByteBuffer byteBuffer = ByteBuffer.allocate(256);
			while (fileChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
				System.out.print(charBuffer);
				byteBuffer.clear();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
