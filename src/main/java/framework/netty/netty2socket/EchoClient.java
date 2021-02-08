package framework.netty.netty2socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class EchoClient {
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);
	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 7777;
		try {
			new EchoClient(host, port).start();
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	public void start() throws  Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.remoteAddress(new InetSocketAddress(host,port))
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) {
							socketChannel.pipeline()
									.addLast(new StringEncoder()) // 解码器StringEncoder 和非netty通信时使用
									//心跳超时设置 读超时 写超时 所有超时
									.addLast(new IdleStateHandler(10, 0 ,0, TimeUnit.SECONDS))
									// 心跳处理
									.addLast(new HeartBeatServerHandler())
									.addLast(new LenDecoder(9, 1024))
									.addLast(new ClientHandler())
									
							;
						}
					});
			ChannelFuture future = b.connect().sync();
			future.channel().closeFuture().sync();
		}catch (Exception e) {
			logger.error("", e);
		}finally {
			group.shutdownGracefully().sync();
		}
	}
}
